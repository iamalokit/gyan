package com.gyan.javaconcurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	private final ReentrantLock lock = new ReentrantLock();
	private int count = 0;

	public int getCount() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "gets Count: " + count);
			return count++;
		} finally {
			lock.unlock();
		}
	}
	
	// implicit locking using synchronized keyword
	public synchronized int getCountTwo() {
		return count++;
	}

}
