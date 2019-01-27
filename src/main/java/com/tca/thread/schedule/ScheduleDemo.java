package com.tca.thread.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 周期任务执行scheduleAtFixedRate方法
 * @author zhoua
 *
 */
public class ScheduleDemo {
	
	private static final ScheduledExecutorService scheduledPool = new ScheduledThreadPoolExecutor(3);
	
	private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	/**
	 * 注意
	 *	虽然我们的执行周期定的是10s，但是我们单次任务执行大约需要18s的时间，使得实际任务执行周期变成了18s
	 *	因此实际执行周期 = max(执行周期参数， 单次任务执行时间)
	 *	执行结果：
	 *		2019-01-27 17:02:36
	 *		2019-01-27 17:02:54
	 *		2019-01-27 17:03:12
	 *		2019-01-27 17:03:30
	 *		2019-01-27 17:03:48
	 *		2019-01-27 17:04:06
	 *		2019-01-27 17:04:24
	 *		2019-01-27 17:04:42
	 * @param args
	 */
	public static void main(String[] args) {
		
		scheduledPool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(18);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(sdf.get().format(new Date()));
				sdf.remove();
			}
		}, 1, 10, TimeUnit.SECONDS);
	}
}
