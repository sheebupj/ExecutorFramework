package com.paremal.executor;

import java.util.concurrent.Callable;

public class CallableRunner implements Callable<String> {
	static int position=1;

	double distanceCovered;
	double speed;
	String name;
	int p;

	public CallableRunner(double distanceCovered, double speed, String name) {
		super();
		this.distanceCovered = distanceCovered;
		this.speed = speed;
		this.name = name;
	}

	@Override
	public String call() {
		// TODO Auto-generated method stub
		while (distanceCovered <= 100) {
			
			
			distanceCovered = distanceCovered + speed;
			processCommand();
			//System.out.println(name + "is at " + distanceCovered);
			
//			if (distanceCovered >= 100) {
//				System.out.println(name + " finished" + position++);
//			}

		}
		synchronized (CallableRunner.class) {
			p=position++;
		}
		
		return name + " finished " + p+"th";

	}

	private void processCommand() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
