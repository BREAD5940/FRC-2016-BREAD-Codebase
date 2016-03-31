
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.camera.CameraServerInit;
import org.usfirst.frc.team5940.states.auto.Breach;
import org.usfirst.frc.team5940.states.opcon.OpConStandardState;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Our management code runs 
 * @author Developer
 *
 */
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
	final static String AllOfThem = "Systems Test";
	final static String Motors = "Motors Test";
	final static String RollerTest = "Rollers Test";
	final static String SensorTest = "Sensors Test";
	final static String CameraTest = "Camera Test";
	
	public Robot() {
		//Call the super constructor
		super();
		SmartDashboard.putString("Codebase Version", "DEB Fixes v2.0");
	}
	
	@Override
	public void robotInit() {
		Components.setupTalons();
	}
	
	/**
	 * Starts our autonomous thread.
	 */
	@Override
	public void autonomous() {
//		this.autoManager = new Thread(new AutoManager(this));
//		this.autoManager.start();
		
		
		//Intturupt state if existent
		if (state != null) {
			state.interrupt();
		}
		//Activate auto
		//state = new Thread(AutoSelector.getSelectedAuto(this));
		state = new Thread(new Breach(this));
		try{
			SmartDashboard.putString("Auto State Name", state.getClass().getSimpleName());
			state.start();
		}catch(Exception e) {SmartDashboard.putString("Status", "Auto state failed to start."); }
		
	}

	/**
	 * Starts our Operator Control thread
	 */
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
		//Activate op con
		state = new Thread(new OpConStandardState(this));
		try{ state.start(); }catch(Exception e) {SmartDashboard.putString("Status", "OpCon state failed to start."); }
	}
	
	/**
	 * Runs our system test code.
	 */
	//Test robot
	@Override
	public void test() {
		
		SendableChooser testChooser = new SendableChooser();
		testChooser.addDefault("All of them", AllOfThem);
		testChooser.addObject("Drive Train only", Motors);
		testChooser.addObject("Roller only",RollerTest);
		testChooser.addObject("Ball Sensor Only", SensorTest);
		testChooser.addObject("Camera only", CameraTest);
		//String selected = (String) testChooser.getSelected();
		
		// Move forwards
		if ((testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(Motors))){
			startTime = System.currentTimeMillis();
			SmartDashboard.putNumber("Start time for test", startTime);
			SmartDashboard.putString("Test Status", "Moving robot forward");
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
		}
    	
    	// move backwards
		if ( (testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(Motors))){
			startTime = System.currentTimeMillis();
    		SmartDashboard.putNumber("Start time for test", startTime);
			SmartDashboard.putString("Test Status", "Moving robot backward");
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
		}
    	
    	// move right
		if ( (testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(Motors))){
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start time for test", startTime);
		SmartDashboard.putString("Test Status", "Moving robot to the right");
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
		}
		
    	//move left
		if ( (testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(Motors))){
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start time for test", startTime);
    	SmartDashboard.putString("Test Status", "Moving robot to the left");
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
		}
    	
    	//move roller forwards
		if ((testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(RollerTest))){
			startTime = System.currentTimeMillis();
			SmartDashboard.putNumber("Start Time For Test", startTime);
			SmartDashboard.putString("Test Status", "Moving roller forwards");
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
		}
    	
    	//move roller backwards
		if ( (testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(RollerTest))){
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start Time For Test", startTime);
		SmartDashboard.putString("Test Status", "Moving roller backwards");
    	while (currentTime >= startTime + 2000){
    		Components.roller.set(-1 * rollerValue);
    		currentTime = System.currentTimeMillis();
    	}
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    	
    	// Make the ball sensor test
		if ( (testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(SensorTest))){
	    	startTime = System.currentTimeMillis();
	    	SmartDashboard.putNumber("Start Time For Test", startTime);
			SmartDashboard.putString("Test Status", "Testing the ball sensor");
		while (currentTime >= startTime + 2000){
    		SmartDashboard.putBoolean("Is the detector working? You tell me!", Components.getCorrectedDetector());
    		currentTime = System.currentTimeMillis();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
    	
    	// Test the camera
    	if ((testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(CameraTest))){
			SmartDashboard.putString("Test Status", "Activating camera feed.");
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
}
