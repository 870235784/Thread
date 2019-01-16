package com.tca.thread.communicate.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author zhoua
 *
 */
public class CountDownLatchDemo {
	
	private static CountDownLatch latch = new CountDownLatch(3);
	
	private static final Random RAND = new Random(10000);
	
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 3; i++) {
			new Thread(){
				public void run() {
					try {
						Thread.sleep(RAND.nextInt(10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "到达会议室!");
					latch.countDown();
				};
			}.start();
		}
		
		latch.await();
		
		System.out.println("人员全部到齐,开始开会!");
	}
}
