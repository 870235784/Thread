package com.tca.thread.communicate.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierDemo {
	
	private static class PriorityRunnable implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + "优先线程开始工作");
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName() + "优先线程结束工作");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//private static final CyclicBarrier barrier = new CyclicBarrier(2);
	
	/*
	 * PriorityRunnable为优先线程，在所有参与线程都执行完barrier.await()后，会优先执行优先线程的run()方法
	 */
	private static final CyclicBarrier barrier = new CyclicBarrier(2, new PriorityRunnable());
	
	public static void main(String[] args) {
		new Thread("thread-0") {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + "开始准备");
					Thread.sleep(10000);
					System.out.println(Thread.currentThread().getName() + "准备完毕");
					barrier.await();
					System.out.println(Thread.currentThread().getName() + "开始工作");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread("thread-1") {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + "开始准备");
					Thread.sleep(3000);
					System.out.println(Thread.currentThread().getName() + "准备完毕");
					barrier.await();
					System.out.println(Thread.currentThread().getName() + "开始工作");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
