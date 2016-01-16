package org.usfirst.frc.team5940.states;

import edu.wpi.first.wpilibj.RobotBase;

public abstract class State implements Runnable {

	private RobotBase robot;
	
	public State (RobotBase robot){
		this.robot = robot;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.init();
		while (!Thread.interrupted()) {
			update();	
		}
	}

	protected abstract void init();
	protected abstract void update();
	
}
