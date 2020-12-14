package com.gyan.javaconcurrency.waitnotify;

import java.util.Queue;

public class Consumer extends Thread {
	private final Queue<Integer> sharedQ;
	
	public Consumer(Queue<Integer> sharedQ) {
		this.sharedQ = sharedQ;
	}
	
	@Override
	public void run() {
		while (true) {
			synchronized (sharedQ) {
				while(sharedQ.size() == 0) {
					try {
						sharedQ.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int number = sharedQ.poll();
				sharedQ.notify();
				
				if(number == 3 ) {
					break;
				}
			}
			
		}
	}
}
