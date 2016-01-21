
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.camera.CameraServerInit;
import org.usfirst.frc.team5940.states.auto.AutoStandardState;
import org.usfirst.frc.team5940.states.opcon.OpConStandardState;

import edu.wpi.first.wpilibj.Encoder;
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
		state = new Thread(new AutoStandardState(this));
	}

	@Override
	public void operatorControl() {
		if (state != null) {
			state.interrupt();
		}
		state = new Thread(new CameraServerInit(this));
	}
	
	/**
	 * Runs during test mode
	 */
	@Override
	public void test() {
		
	}
}
