package com.tca.thread.uncaughtexception;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UncaughtExceptionHandlerDemo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(
			UncaughtExceptionHandlerDemo.class);
	
	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				LOGGER.info(getName() + " is running...");
				int i = 1/0;
				System.out.println(i);
			}
		};
		
		// 设置未捕获异常处理器
		thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				new Thread() {
					public void run() {
						LOGGER.error(t.getName() + "线程运行出现了异常.");
						LOGGER.error("异常：", e);
						LOGGER.info("当前线程为" + getName());
						LOGGER.info("现在开始替补行动: gogogo");
					}
				}.start();
			}
		});
		thread.start();
	}
}
