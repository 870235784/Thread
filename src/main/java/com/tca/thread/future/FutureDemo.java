package com.tca.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
public static void main(String[] args) throws Exception {
		
		Callable<Integer> call = new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				Thread.sleep(5 * 1000);
				return 1;
			}
			
		};
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		Future<Integer> result = pool.submit(call);
		
		pool.shutdown();
		
		System.out.println("开始取值:");
		
		System.out.println(result.get());
		
	}
}
