package com.tca.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class AQSLock implements Lock{
	
	private Sync sync = new Sync();
	
	private static class Sync extends AbstractQueuedSynchronizer {
		
		private static final long serialVersionUID = 7970259186122472887L;

		@Override
		protected boolean tryAcquire(int arg) {
			Thread currentThread = Thread.currentThread();//获取当前线程
			int state = getState();//共享资源 -- 用来维护当前锁是否被获取，以及被重入的次数
			
			if (state == 0 && compareAndSetState(0, arg)) {//state==0 表示当前锁对象尚未被获取，并使用自旋的方式设置state, 保证了线程的安全
				setExclusiveOwnerThread(currentThread);//将当前线程设为独占锁的线程
				return true;
			}
			
			if (getExclusiveOwnerThread() == currentThread) {//如果state!=0但是锁对象已被当前线程获取,则设置可重入,state+arg
				int update = state + arg;
				compareAndSetState(state, update);
				return true;
			}
			
			return false;
		}
		
		@Override
		protected boolean tryRelease(int arg) {
			Thread currentThread = Thread.currentThread();
			
			if (getExclusiveOwnerThread() != currentThread) {//如果当前线程没有获取锁资源,则直接抛出异常
				throw new IllegalMonitorStateException("当前线程没有获取锁资源");
			}
			
			int state = getState();
			int update = state - arg;
			
			if (update >= 0 && compareAndSetState(state, update)) {
				if (update == 0) {//锁资源已完全释放
					setExclusiveOwnerThread(null);
				}
				return true;
			}
			return false;
		}
	}

	@Override
	public void lock() {
		sync.acquire(1);
	}
	
	@Override
	public void unlock() {
		sync.release(1);
	}


	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return false;
	}

	
	@Override
	public Condition newCondition() {
		return null;
	}
}
