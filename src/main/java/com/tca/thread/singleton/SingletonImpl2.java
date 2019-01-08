package com.tca.thread.singleton;

/**
 * 懒汉式 + volatile + double-check
 * @author zhoua
 *
 */
public class SingletonImpl2 {
	
	private SingletonImpl2 (){}
	
	private static volatile SingletonImpl2 instance = null;
	
	public static SingletonImpl2 getIntance() {
		if (instance == null) {
			synchronized (SingletonImpl2.class) {
				if (instance == null) {
					instance = new SingletonImpl2();
				}
			}
		}
		return instance;
	}
}
