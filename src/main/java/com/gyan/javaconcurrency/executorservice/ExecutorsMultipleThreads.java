package com.gyan.javaconcurrency.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsMultipleThreads {
	public static void main(String[] args) {
		System.out.println("Inside: " + Thread.currentThread().getName());

		System.out.println("Creating executor service...");
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Runnable task1 = () -> {
			System.out.println("Executing task 1 inside : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		};
		
		Runnable task2 = () -> {
			System.out.println("Executing task 2 inside : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		};
		
		
		Runnable task3 = () -> {
			System.out.println("Executing task 3 inside : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		};
		
		System.out.println("Submit the task specified by the runnable to the executor service");
		executorService.submit(task1);
		executorService.submit(task2);
		executorService.submit(task3);

		System.out.println("Shutting down the executor");
		executorService.shutdown();
	}
}
