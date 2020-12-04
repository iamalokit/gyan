package com.gyan.javaconcurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;

public class Computation1 implements Runnable {
	public static int product = 0;
	public void run() {
		product = 2*3;
		try {
			Tester.newBarrier.await();
		} catch(InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
}
