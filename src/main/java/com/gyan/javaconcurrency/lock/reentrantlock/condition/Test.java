package com.gyan.javaconcurrency.lock.reentrantlock.condition;

public class Test {
	public static void main(String[] args) {
		Message msg = new Message();
		Thread messageProducer = new Thread(new MessageProducer(msg));
		Thread messageViewer = new Thread(new MessageViewer(msg));
		messageProducer.start();
		messageViewer.start();
	}
}
