package com.tca.thread.singleton;

/**
 * 使用内部类
 * @author zhoua
 *
 */
public class SingletonImpl3 {

	private SingletonImpl3(){}
	
	private static class InstanceHolder {
		
		private static final SingletonImpl3 INSTANCE = new SingletonImpl3();
	}
	
	public static SingletonImpl3 getInstance() {
		return InstanceHolder.INSTANCE;
	}
}
