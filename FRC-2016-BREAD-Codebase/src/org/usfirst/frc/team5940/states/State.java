package org.usfirst.frc.team5940.states;

import edu.wpi.first.wpilibj.RobotBase;

public abstract class State implements Runnable {

	private RobotBase robot;
	private int delay = 25;
	
	public State (RobotBase robot){
		this.robot = robot;
	}
	
	@Override
	public void run() {
		this.init();
		while (!Thread.interrupted()) {
			update();
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}

	protected abstract void init();
	protected abstract void update();
	
}
