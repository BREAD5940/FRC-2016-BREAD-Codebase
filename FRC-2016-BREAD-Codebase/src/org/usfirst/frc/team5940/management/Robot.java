
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.camera.CameraServerInit;
import org.usfirst.frc.team5940.states.auto.AutoSelector;
import org.usfirst.frc.team5940.states.auto.AutoManager;
import org.usfirst.frc.team5940.states.opcon.OpConStandardState;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
	//Set the threads
	public Thread state;
	public Thread camera;
	public Thread autoManager;

	double rightValue = 0.5;
	double leftValue = 0.5;
	double rollerValue = 0.5;
	long startTime;
	long currentTime;
	
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
		// Move forwards
		startTime = System.currentTimeMillis();
		SmartDashboard.putNumber("Start time for test", startTime);
		while (currentTime >= startTime + 2000){
			SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
			SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
			Components.r1.set(rightValue);
			Components.r2.set(rightValue);
			Components.l1.set(leftValue);
			Components.l2.set(leftValue);
			currentTime = System.currentTimeMillis();
		}
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// move backwards
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start time for test", startTime);
    	while (currentTime >= startTime + 2000){
    		SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
    		SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
    		Components.r1.set(-1 * rightValue);
    		Components.r2.set(-1 * rightValue);
    		Components.l1.set(-1 * leftValue);
    		Components.l2.set(-1 * leftValue);
    		currentTime = System.currentTimeMillis();
    	}
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// move right
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start time for test", startTime);
    	while (currentTime >= startTime + 2000){
    		SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
    		SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
    		Components.r1.set(rightValue);
    		Components.r2.set(rightValue);
    		Components.l1.set(-1 * leftValue);
    		Components.l2.set(-1 * leftValue);
    		currentTime = System.currentTimeMillis();
    	}
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//move left
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start time for test", startTime);
    	while (currentTime >= startTime + 2000){
    		SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
    		SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
    		Components.r1.set(-1 * leftValue);
    		Components.r2.set(-1 * leftValue);    	
    		Components.l1.set(rightValue);
    		Components.l2.set(rightValue);
    		currentTime = System.currentTimeMillis();
    	}
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//move roller forwards
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start Time For Test", startTime);
    	while (currentTime >= startTime + 2000){
    		Components.roller.set(rollerValue);
    		currentTime = System.currentTimeMillis();
    	}

    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//move roller backwards
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start Time For Test", startTime);
    	while (currentTime >= startTime + 2000){
    		Components.roller.set(-1 * rollerValue);
    		currentTime = System.currentTimeMillis();
    	}
    	
    	// Make the ball sensor test
    	SmartDashboard.putBoolean("Is the detector working? You tell me!", Components.getCorrectedDetector());
    	
    	
    	// Test the camera
    	Thread camera =  new Thread(new CameraServerInit(this));
    	camera.start();
    	
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	camera.interrupt();
    	
	}
}
