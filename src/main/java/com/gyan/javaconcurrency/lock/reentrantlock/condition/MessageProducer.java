package com.gyan.javaconcurrency.lock.reentrantlock.condition;

import java.util.ArrayList;
import java.util.List;

public class MessageProducer implements Runnable {
	private Message message;
	
	public MessageProducer(Message msg) {
		this.message = msg;
	}
	@Override
	public void run() {
		publishMessages();
	}
	
	private void publishMessages() {
		List<String> msgs = new ArrayList<String>();
		msgs.add("hello");
		msgs.add("current project is complete");
		msgs.add("here is the estimation for new project");
		for(String msg :  msgs) {
			message.publishMessage(msg);
			try {
	            Thread.sleep(400);
	        } catch (InterruptedException e) {}
		}
		
		message.publishMessage("bye");
		message.setEndIt(true);
	}

}
