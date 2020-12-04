package com.gyan.javaconcurrency.suspend;

public class SuspendDemo extends Thread {
	public void run() {
		for(int i = 1; i < 5; i++) {
			try {
				sleep(5);
				System.out.println("Currently running -" + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		SuspendDemo t1 = new SuspendDemo(); 
		SuspendDemo t2 = new SuspendDemo(); 
		SuspendDemo t3 = new SuspendDemo();	
		
		t1.start();
		t2.start();
		
		t2.suspend();
		
		t3.start();
		
	}
}
