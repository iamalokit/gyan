package com.gyan.javaconcurrency.suspend;

public class ProperSuspend {
	
	public static void main(String[] args) throws InterruptedException{
		final Thread thread1 = new Thread("Thread -1") {
			public void run() {
				System.out.println(Thread.currentThread().getName()+ "has started.");
				synchronized (String.class) {
					System.out.println(Thread.currentThread().getName());
					System.out.println("Obtained lock on String.class and suspended");
					Thread.currentThread().suspend();
					// executed after resume is called
					System.out.println(Thread.currentThread().getName() + " has released lock on String.class");
					
				}
				System.out.println(Thread.currentThread().getName()+ "has Ended");
			}
		};
		
		thread1.start();
		Thread.sleep(2000);
		
		// resume method should be called before inorder to release lock on String.class (monitor)
		Thread thread2 = new Thread("Thread - 2") {
			public void run() {
				System.out.println(Thread.currentThread().getName()+ "has started.");
				System.out.println(Thread.currentThread().getName()+ "trying to obtain lock on String.class monitor");
				thread1.resume();
				System.out.println("Thread 1 resumed");
				synchronized (String.class) {
					System.out.println(Thread.currentThread().getName());
					System.out.println("Obtained lock on String.class and suspended");
					
				}
				System.out.println(Thread.currentThread().getName()+ "has Ended");
			}
		};
		
		thread2.start();
	}
}
