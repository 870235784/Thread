package com.tca.thread.error;

/**
 * 死锁
 * @author zhoua
 *
 */
public class DeadLockDemo {
	
	private static Object lockA = new Object();
	
	private static Object lockB = new Object();
		
	public static void doA() throws InterruptedException {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + "has got lockA");
			Thread.sleep(1000L);
			doB();
			System.out.println("method doA has done");
		}
	}

	private static void doB() throws InterruptedException {
		synchronized (lockB) {
			System.out.println(Thread.currentThread().getName() + "has got LockB");
			Thread.sleep(1000L);
			doA();
			System.out.println("method doB has done");
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					doA();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					doB();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
}
