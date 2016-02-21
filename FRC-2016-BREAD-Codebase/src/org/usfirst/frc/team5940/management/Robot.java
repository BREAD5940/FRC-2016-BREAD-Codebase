
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.camera.CameraServerInit;
import org.usfirst.frc.team5940.states.auto.AutoSelector;
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
		try{ this.camera.start(); }catch(Exception e) {SmartDashboard.putString("Status", "Camera state failed to start."); }
	}
	
	
	@Override
	public void autonomous() {
		//Intturupt state if existent
		if (state != null) {
			state.interrupt();
		}
		//Activate auto
		state = new Thread(AutoSelector.getSelectedAuto(this));
		try{
			SmartDashboard.putString("Auto State Name", state.getClass().getSimpleName());
			state.start();
		}catch(Exception e) {SmartDashboard.putString("Status", "Auto state failed to start."); }
		
	}

	@Override
	public void operatorControl() {
		//Intrupt state if existent
		if (state != null) {
			state.interrupt();
		}
		//Activiate op con
		state = new Thread(new OpConStandardState(this));
		try{ state.start(); }catch(Exception e) {SmartDashboard.putString("Status", "OpCon state failed to start."); }
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
