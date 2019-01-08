package com.tca.thread.impl;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread{
	
	@Override
	public void run() {
		int i = 10;
		while (i > 0) {
			System.out.println(getName() + ": is running.");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i --;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(Thread.currentThread().getName() + ": is start!");
		
		TimeUnit.SECONDS.sleep(1);
		MyThread myThread = new MyThread();
		myThread.start();
		/*
		 * Thread.join()
		 * 	1.只有线程启动之后才能join
		 * 	2.join表示当前线程执行完之后，父线程才能继续执行
		 */
		myThread.join();
		
		System.out.println(Thread.currentThread().getName() + ": is done!");
		
	}
}
