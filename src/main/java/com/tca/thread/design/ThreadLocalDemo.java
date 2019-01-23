package com.tca.thread.design;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal的使用场景
 * 为什么阿里巴巴禁止把SimpleDateFormat定义为static类型(可以定义为static类型，但是需要使用ThreadLocal)
 * @author zhoua
 *
 */
public class ThreadLocalDemo {
	
	/**
	 * SimpleDateFormat是线程不安全的，因此每个线程都需要存储一个SimpleDateFormat的副本
	 */
	private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		};
	};
	
	private static SimpleDateFormat sdfUnsafe = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final ExecutorService POOL = Executors.newFixedThreadPool(100);
	
	public static String format(Date date) {
		SimpleDateFormat simpleDateFormat = sdf.get();
		return simpleDateFormat.format(date);
	}
	
	private static final CountDownLatch LATCH_SAFE = new CountDownLatch(100);
	
	private static final CountDownLatch LATCH_UNSAFE = new CountDownLatch(100);
	
	public static void main(String[] args) throws InterruptedException {
		Set<String> setSafe = Collections.synchronizedSet(new HashSet<>());
		
		for (int i = 0; i < 100; i++) {
			int j = i;
			POOL.execute(new Runnable() {

				@Override
				public void run() {
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, j);
					String dateString = format(calendar.getTime());
					setSafe.add(dateString);
					sdf.remove(); // 为了防止内存泄漏，一般使用完后需要remove
					LATCH_SAFE.countDown();
				}
				
			});
		}
		
		LATCH_SAFE.await();
		System.out.println(setSafe.size());
		
		System.out.println("=====================================");
		
		
		// 此处我们使用同一个SimpleDateFormat时会出现线程安全问题，此时set的size达不到100个
		Set<String> setUnsafe = Collections.synchronizedSet(new HashSet<>());
		
		for (int i = 0; i < 100; i++) {
			int j = i;
			POOL.execute(new Runnable() {

				@Override
				public void run() {
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, j);
					String dateString = sdfUnsafe.format(calendar.getTime());
					setUnsafe.add(dateString);
					LATCH_UNSAFE.countDown();
				}
				
			});
		}
		
		POOL.shutdown();
		LATCH_UNSAFE.await();
		System.out.println(setUnsafe.size());
		
	}
}
