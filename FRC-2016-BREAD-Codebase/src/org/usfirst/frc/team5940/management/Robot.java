
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.camera.CameraServerInit;
import org.usfirst.frc.team5940.states.auto.AutoSelector;
import org.usfirst.frc.team5940.states.auto.AutoManager;
import org.usfirst.frc.team5940.states.opcon.OpConStandardState;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
	//Set the threads
	public Thread state;
	public Thread camera;
	public Thread autoManager;

	public Robot() {
		//Call the super constructor
		super();
		SmartDashboard.putString("Codebase Version", "DEB Fixes v2.0");
	}
	
	@Override
	public void robotInit() {
		Components.setupTalons();
	}
	
	
	@Override
	public void autonomous() {
		this.autoManager = new Thread(new AutoManager(this));
		this.autoManager.start();
		
		
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
		SmartDashboard.putBoolean("cam", false);
        SmartDashboard.putBoolean("caminitend", false);
		this.camera = new Thread(new CameraServerInit(this));
		this.camera.start();
		
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
