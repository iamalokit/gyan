package com.gyan.javaconcurrency.lock.reentrantlock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Message {
	final private Lock lock = new ReentrantLock();
	final private Condition producedMsg = lock.newCondition();
	final private Condition consumedMsg = lock.newCondition();
	
	private String message;
	private boolean messageState;
	private boolean endIt;
	
	public void viewMessage() {
		lock.lock();
		try {
			while(!messageState) {
				producedMsg.await();
			}
			
			System.out.println("Here is the latest message : "+ message);
			messageState = false;
			consumedMsg.signal();
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted - view Message");
		} finally {
			lock.unlock();
		}
	}
	
	public void publishMessage(String message) {
		lock.lock();
		try {
			while(messageState)
				consumedMsg.await();
			System.out.println("adding latest message");
			this.message = message;
			messageState = true;
			producedMsg.signal();
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted - publish message");
		} finally {
			lock.unlock();
		}
	}
	
	public boolean isEndIt() {
		return endIt;
	}
	
	public void setEndIt(boolean endIt) {
		this.endIt = endIt;
	}
}
