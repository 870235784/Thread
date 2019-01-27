package com.tca.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
	
	public static void main(String[] args) throws Exception {
		
		Callable<Integer> call = new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				Thread.sleep(5 * 1000);
				return 1;
			}
			
		};
		
		FutureTask<Integer> futureTask = new FutureTask<>(call);
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		//pool.execute(futureTask);
		pool.submit(futureTask);
		
		pool.shutdown();
		
		System.out.println("开始取值:");
		
		System.out.println(futureTask.get());
		
	}
}
