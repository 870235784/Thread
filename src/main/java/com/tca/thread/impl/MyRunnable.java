package com.tca.thread.impl;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable{

	@Override
	public void run() {
		int i = 10;
		while (i > 0) {
			System.out.println(Thread.currentThread().getName() + ": is running.");
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
		Thread thread = new Thread(new MyRunnable());
		thread.start();
		thread.join();
		
		System.out.println(Thread.currentThread().getName() + ": is done!");
		
	}

}
