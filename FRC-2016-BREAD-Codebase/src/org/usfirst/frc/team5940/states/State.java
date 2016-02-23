package org.usfirst.frc.team5940.states;

import edu.wpi.first.wpilibj.RobotBase;

public abstract class State implements Runnable {
	@SuppressWarnings("unused")
	protected RobotBase robot;
	//Update recall delay
	private int delay = 25;
	
	/**
	 * Constructor
	 * @param robot Saved in this class, allows states to determine information about the robot. This should be passed the subclass of RobotBase of your code.
	 */
	public State (RobotBase robot){
		//Set the robot
		this.robot = robot;
	}
	
	/**
	 * Overriden run method for Runnable superclass. Started with STATE_RUNNABLE_NAME.run(); or STATE_THREAD_NAME.start();
	 */
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
	
	/**
	 * Called once right after run() is called, even if the Thread is interrupted.
	 */
	protected abstract void init();
	
	/**
	 * Called forever with a delay of delay while the Thread is not interrupted.
	 */
	protected abstract void update();
	
}
