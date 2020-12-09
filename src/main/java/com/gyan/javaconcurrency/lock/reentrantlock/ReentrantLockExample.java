package com.gyan.javaconcurrency.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
	public static void main(String[] args) {
		ReentrantLock rLock = new ReentrantLock(true);
		
		Thread threadOne = new Thread(new Runnable() {
			
			@Override
			public void run() {
				rLock.lock();
				try {
					if(rLock.isHeldByCurrentThread()) {
						System.out.println("Thread one lock obtained");
						Thread.sleep(5000);
					}
				} catch (InterruptedException e) {
				} finally {
					if(rLock.isHeldByCurrentThread()) {
						rLock.unlock();
					}
				}
				
			}
		});
		
		Thread threadTwo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				rLock.lock();
				try {
					if(rLock.isHeldByCurrentThread()) {
						System.out.println("thread two lock obtained");
					}
				} finally {
					if(rLock.isHeldByCurrentThread()) {
						rLock.unlock();
					}
				}
				
			}
		});
		threadOne.start();
		threadTwo.start();
		
		System.out.println("main thread");
	}
}
