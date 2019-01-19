package com.tca.thread.communicate.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	
	private static final Semaphore SEMAPHORE  = new Semaphore(3);
	private static final Random RANDOM = new Random(1000);
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread("Thread-" + i) {
				public void run() {
					try {
						SEMAPHORE.acquire();
						System.out.println(getName() + " 正在获取许可证");
						System.out.println(getName() + " 开始观光");
						Thread.sleep(RANDOM.nextInt(3000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						System.out.println(getName() + " 正在释放许可证");
						SEMAPHORE.release();
					}
				};
			}.start();
		}
	}
}
