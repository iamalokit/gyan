package com.gyan.javaconcurrency.lock.reentrantlock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class ReadWriteReentrantLockExample {
	public static void main(String[] args) {
		ShoppingCart scart = new ShoppingCart();
		scart.addProduct("iphone");
		
		ReentrantReadWriteLock rrwlock = new ReentrantReadWriteLock();
		Thread threadRead = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ReadLock r1 = rrwlock.readLock();
				try {
					r1.lock();
					System.out.println(scart.getProduct(0));
					Thread.sleep(1000);
				} catch (InterruptedException e) {

				} finally {
					r1.unlock();
				}
				
			}
		});
		
		Thread threadWrite = new Thread(new Runnable() {
			
			@Override
			public void run() {
				rrwlock.writeLock().lock();
				try {
					if(rrwlock.isWriteLockedByCurrentThread()) {
						scart.addProduct("pixel");
						System.out.println("thread write lock obtained");
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
				} finally {
					if(rrwlock.isWriteLockedByCurrentThread()) {
						rrwlock.writeLock().unlock();
					}
				}
				
			}
		});
		
		threadRead.start();
		threadWrite.start();
		
		System.out.println("main thread");
	}
}
