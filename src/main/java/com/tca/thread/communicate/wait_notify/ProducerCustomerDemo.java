package com.tca.thread.communicate.wait_notify;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共享资源
 * @author zhoua
 *
 */
class Resource {
	
	private volatile List<String> queue = new LinkedList<>();
	
	private static final int FULL_SIZE = 10;
	
	private static final AtomicInteger COUNT = new AtomicInteger(1);
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Resource.class);
	
	public void enqueue() throws InterruptedException {
		synchronized(queue) {
			while(queue.size() > FULL_SIZE - 1) {
				queue.wait();
			}
			String message = COUNT.getAndIncrement() + "";
			queue.add(message);
			LOGGER.debug("message : {}, enqueue successfully, the queue size is {} now.",
					message, queue.size());
			queue.notifyAll();
		}
	}
	
	public void dequeue() throws InterruptedException {
		synchronized(queue) {
			while(queue.size() == 0) {
				queue.wait();
			}
			String message = queue.remove(0);
			LOGGER.debug("message : {}, dequeue successfully, the queue size is {} now.",
					message, queue.size());
			queue.notifyAll();
		}
	}
}

class Producer implements Runnable{
	
	private static Random rand = new Random();
	
	private final Resource resource;
	
	public Producer(Resource resource) {
		this.resource = resource;
	}
	
	public void produce() throws InterruptedException {
		resource.enqueue();
	}

	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(rand.nextInt(10));
				this.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Customer implements Runnable{
	
	private static Random rand = new Random();
	
	private final Resource resource;
	
	public Customer(Resource resource) {
		this.resource = resource;
	}
	
	public void consume() throws InterruptedException {
		resource.dequeue();
	}

	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(rand.nextInt(10));
				this.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ProducerCustomerDemo {
	
	private static Resource resource = new Resource();
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			
			new Thread(new Customer(resource)).start();
			
			new Thread(new Producer(resource)).start();
			
		}
		
		
	}
}
