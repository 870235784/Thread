package com.tca.thread.singleton;

/**
 * 使用枚举的方式
 * @author zhoua
 *
 */
public class SingletonImpl4 {
	
	private SingletonImpl4(){} 
	
	private enum InstanceHolder {
		INSTANCE;
		
		private SingletonImpl4 instance;
		
		private InstanceHolder () {
			this.instance = new SingletonImpl4();
		}
		
		public SingletonImpl4 getInstance() {
			return instance;
		}
		
	}
	
	public static SingletonImpl4 getInstance () {
		return InstanceHolder.INSTANCE.getInstance();
	}
}
