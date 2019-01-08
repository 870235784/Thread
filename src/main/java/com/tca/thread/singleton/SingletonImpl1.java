package com.tca.thread.singleton;

/**
 * 饿汉式
 * @author zhoua
 *
 */
public class SingletonImpl1 {
	
	private SingletonImpl1(){};
	
	private static final SingletonImpl1 INSTANCE = new SingletonImpl1();
	
	public SingletonImpl1 getInstance() {
		return INSTANCE;
	}
}
