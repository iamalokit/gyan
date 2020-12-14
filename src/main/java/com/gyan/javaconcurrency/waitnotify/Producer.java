package com.gyan.javaconcurrency.waitnotify;

import java.util.Queue;

public class Producer extends Thread {
	private final Queue sharedQ;
	
	public Producer(Queue sharedQ) {
		super("Producer");
		this.sharedQ = sharedQ;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 4; i++) {
			synchronized (sharedQ) {
				while(sharedQ.size() >= 1) {
					try {
						System.out.println("Queue is full, waiting");
						sharedQ.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Producing : " + i);
				sharedQ.add(i);
				sharedQ.notify();
			}
		}
	}
}
