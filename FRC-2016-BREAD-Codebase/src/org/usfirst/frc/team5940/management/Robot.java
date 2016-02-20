
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.camera.CameraServerInit;
import org.usfirst.frc.team5940.states.auto.AutoStandardState;
import org.usfirst.frc.team5940.states.opcon.OpConStandardState;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
	//Set the threads
	public Thread state;
	public Thread camera;

	public Robot() {
		//Call the super constructor
		super();
		SmartDashboard.putString("Codebase Version", "DEB Fixes v1.5");
	}
	
	@Override
	public void robotInit() {
		this.camera = new Thread(new CameraServerInit(this));
		this.camera.start();
	}
	
	
	@Override
	public void autonomous() {
		//Intturupt state if existent
		if (state != null) {
			state.interrupt();
		}
		//Activate auto
		state = new Thread(new AutoStandardState(this));
		state.start();
	}

	@Override
	public void operatorControl() {
		//Intrupt state if existent
		if (state != null) {
			state.interrupt();
		}
		//Activiate op con
		state = new Thread(new OpConStandardState(this));
		state.start();
	}
	
	/**
	 * Runs during test mode
	 */
	@Override
	public void test() {
		//Test robot
		//TODO Make this do something
	}
}
