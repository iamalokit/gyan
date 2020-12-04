package com.gyan.javaconcurrency.interrupt;

public class Interrupt {
	
	public static void runTask(Runnable task) throws InterruptedException{
		task.run();
		if(Thread.interrupted()) throw new InterruptedException();
	}
	
	Runnable[] frobnicateTasks = new Runnable[] {
			() -> { System.out.println("task1"); },
	        () -> { Thread.currentThread().interrupt(); },
	        () -> { System.out.println("task2"); }
	};
	
	public static void main(String[] args) {
		Interrupt interrupt = new Interrupt();
		int progress = interrupt.frobnicate();
		while(progress != -1) {
			System.out.println("Paused");
			progress = interrupt.resumeFrobnicate(progress);
		}
		System.out.println("Done");
	}

	private int frobnicate() {
		return resumeFrobnicate(0);
	}

	private int resumeFrobnicate(int taskPos) {
		try {
			while(taskPos < frobnicateTasks.length)
				runTask(frobnicateTasks[taskPos++]);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		if(taskPos == frobnicateTasks.length) {
			return -1;
		}
		
		return taskPos;
	}
}
