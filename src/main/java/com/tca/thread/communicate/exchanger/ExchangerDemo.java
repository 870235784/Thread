package com.tca.thread.communicate.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
	
	private static final Exchanger<String> EXCHANGER = new Exchanger<>();
	
	public static void main(String[] args) {
		new Thread("Thread-A") {
			public void run() {
				try {
					String result = EXCHANGER.exchange("hello , I am Thread-A");
					System.out.println(getName() + 
							" has got the result : " + result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread("Thread-B") {
			public void run() {
				try {
					String result = EXCHANGER.exchange("hello , I am Thread-B");
					System.out.println(getName() + 
							" has got the result : " + result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread("Thread-C") {
			public void run() {
				try {
					String result = EXCHANGER.exchange("hello , I am Thread-C");
					System.out.println(getName() + 
							" has got the result : " + result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread("Thread-D") {
			public void run() {
				try {
					String result = EXCHANGER.exchange("hello , I am Thread-D");
					Thread.sleep(5000);
					System.out.println(getName() + 
							" has got the result : " + result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
