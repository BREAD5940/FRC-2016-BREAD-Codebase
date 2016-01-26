package org.usfirst.frc.team5940.states;

import edu.wpi.first.wpilibj.RobotBase;

public abstract class State implements Runnable {
	//Unused things
	@SuppressWarnings("unused")
	private RobotBase robot;
	//Refresh delay
	private int delay = 25;
	
	public State (RobotBase robot){
		//Set the robot
		this.robot = robot;
	}
	
	@Override
	public void run() {
		//Initilize
		this.init();
		//Continue until interupdete
		while (!Thread.interrupted()) {
			//Update
			update();
			//try to sleep
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) { /*Print the error*/e.printStackTrace(); }
		}
	}
	//Abstract things
	protected abstract void init();
	protected abstract void update();
	
}
