
package org.usfirst.frc.team5940.management;


import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	
	public Thread state;

	public Robot() {
		super();
	}
	
	@Override
	public void robotInit() {
	/*
	*Make sure to never do code that needs the robot to be disabled. 
	*This will cause the robot to be bypassed in a match. 	
	*/
		
		
	}
	
	
	@Override
	public void autonomous() {
		if (state != null) {
			state.interrupt();
		}
		
	}

	
	@Override
	public void operatorControl() {
		
	}
	
	/**
	 * Runs during test mode
	 */
	@Override
	public void test() {
		
	}
}
