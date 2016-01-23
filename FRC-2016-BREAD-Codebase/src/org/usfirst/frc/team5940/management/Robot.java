
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.camera.CameraServerInit;
import org.usfirst.frc.team5940.states.auto.AutoStandardState;
import org.usfirst.frc.team5940.states.opcon.OpConStandardState;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	
	public Thread state;
	public Thread camera;

	public Robot() {
		super();
	}
	
	@Override
	public void robotInit() {
		this.camera = new Thread(new CameraServerInit(this));
		this.camera.start();
	}
	
	
	@Override
	public void autonomous() {
		if (state != null) {
			state.interrupt();
		}
		state = new Thread(new AutoStandardState(this));
		state.start();
	}

	@Override
	public void operatorControl() {
		if (state != null) {
			state.interrupt();
		}
		state = new Thread(new OpConStandardState(this));
		state.start();
	}
	
	/**
	 * Runs during test mode
	 */
	@Override
	public void test() {
		
	}
}
