package com.gyan.javaconcurrency.lock.cleanlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	
	private final Synchronizer synchronizer; 
	private int count;
	
	interface Operation<T> {
		T execute();
	}
	
	static class Synchronizer{
		private final Lock lock;
		Synchronizer() {
			lock = new ReentrantLock();
		}
		
		private int execute(Operation<Integer> operation) {
			lock.lock();
			try {
				return operation.execute();
			} finally {
				lock.unlock();
			}
		}
	}
	Counter(){
		synchronizer = new Synchronizer();
	}
	
	int next() {
		return synchronizer.execute(() -> {return count ++;});
	}
}
