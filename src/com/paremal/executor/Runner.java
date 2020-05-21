package com.paremal.executor;

public class Runner implements Runnable {
	static int position=1;

	double distanceCovered;
	double speed;
	String name;

	public Runner(double distanceCovered, double speed, String name) {
		super();
		this.distanceCovered = distanceCovered;
		this.speed = speed;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (distanceCovered <= 100) {
			
			System.out.println("\n\n\n" + name + "is at " + distanceCovered);
			distanceCovered = distanceCovered + speed;
			processCommand();
			System.out.println(name + "is at " + distanceCovered);
			
			if (distanceCovered >= 100) {
				System.out.println(name + " finished" + position++);
			}

		}

	}

	private void processCommand() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
