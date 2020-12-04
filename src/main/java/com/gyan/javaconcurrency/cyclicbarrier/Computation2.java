package com.gyan.javaconcurrency.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Computation2 implements Runnable {
	
	public static int sum = 0;
	
	public void run() {
		System.out.println("Is barrier broken? - " + Tester.newBarrier.isBroken());
		sum = 10 +20;
		
		try {
			Tester.newBarrier.await(3000, TimeUnit.MILLISECONDS);
			System.out.println("Number of parties waiting at this point = " + Tester.newBarrier.getNumberWaiting());
			
		}catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		} catch(TimeoutException e) {
			e.printStackTrace();
		}
	}

}
