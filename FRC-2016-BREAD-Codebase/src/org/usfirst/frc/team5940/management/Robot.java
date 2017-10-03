
package org.usfirst.frc.team5940.management;


import org.usfirst.frc.team5940.states.auto.AutoSelector;
import org.usfirst.frc.team5940.states.opcon.OpConStandardState;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

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

	
	
	public Robot() {
		//Call the super constructor
		super();
		Components.robot = this;
		SmartDashboard.putString("Codebase Version", "DEB Fixes v2.1");
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
		state = new Thread(AutoSelector.getSelectedAuto(this));
//		state = new Thread(new Breach(this));
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
		
		SendableChooser testVsOpCon = new SendableChooser();
		testVsOpCon.addDefault("Standard Operator Control", "OPCON");
		testVsOpCon.addObject("Standard Testing", "TEST");
		SmartDashboard.putData("OpCon vs ", testVsOpCon);
		
		//Intrupt state if existent
		if (state != null) {
			state.interrupt();
		}
		//Activate op con
		if(testVsOpCon.getSelected().equals("TEST")) state = new Thread(new TestingStandardState(this));
		if(testVsOpCon.getSelected().equals("OPCON")) state = new Thread(new OpConStandardState(this));
		try{ state.start(); }catch(Exception e) {SmartDashboard.putString("Status", "OpCon state failed to start."); }
		//testCode();
		
	}
	
	/**
	 * Runs our system test code.
	 */
	//Test robot
	@Override
	public void test() {
		
		
	}

	void testCode() {
		Components.setupTalons();
		float rightValue = 0.5f;
		float leftValue = 0.5f;
		float rollerValue = 0.5f;
		long startTime = 0;
		long currentTime = 0;
		String AllOfThem = "Systems Test";
		String Motors = "Motors Test";
		String RollerTest = "Rollers Test";
		String SensorTest = "Sensors Test";
		String CameraTest = "Camera Test";
		
		int testofthetest = 0;
		
		SmartDashboard.putNumber("point", 1);
		SendableChooser testChooser = new SendableChooser();
		testChooser.addDefault("All of them", AllOfThem);
		testChooser.addObject("Drive Train only", Motors);
		testChooser.addObject("Roller only",RollerTest);
		testChooser.addObject("Ball Sensor Only", SensorTest);
		testChooser.addObject("Camera only", CameraTest);

		SmartDashboard.putNumber("point", 2);
		SmartDashboard.putData("chooser",testChooser);
		//String selected = (String) testChooser.getSelected();
		
		SmartDashboard.putString("Test Status", "Choose in 5...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		SmartDashboard.putString("Test Status", "Choose in 4...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		SmartDashboard.putString("Test Status", "Choose in 3...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		SmartDashboard.putString("Test Status", "Choose in 2...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		SmartDashboard.putString("Test Status", "Choose in 1...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		
		// Move forwards
		SmartDashboard.putString("Object", (String)testChooser.getSelected());
		if ((testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(Motors))){
			startTime = System.currentTimeMillis();
			SmartDashboard.putNumber("Start time for test", startTime);
			SmartDashboard.putString("Test Status", "Moving robot forward");
		while (currentTime <= startTime + 2000){
			SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
			SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
			Components.rGroup.setValue(rightValue);
			Components.lGroup.setValue(leftValue);
			currentTime = System.currentTimeMillis();
		}
    	Components.rGroup.setValue(0);    	
		Components.lGroup.setValue(0);
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
    	while (currentTime <= startTime + 2000){
    		SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
    		SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
    		Components.rGroup.setValue(-1 * rightValue);
    		Components.lGroup.setValue(-1 * leftValue);
    		currentTime = System.currentTimeMillis();
    	}
    	Components.rGroup.setValue(0);    	
		Components.lGroup.setValue(0);
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
    	while (currentTime <= startTime + 2000){
    		SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
    		SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
    		Components.rGroup.setValue(-1 * rightValue);
    		Components.lGroup.setValue(leftValue);
    		currentTime = System.currentTimeMillis();
    	}
    	Components.rGroup.setValue(0);    	
		Components.lGroup.setValue(0);
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
    	while (currentTime <= startTime + 2000){
    		SmartDashboard.putNumber("Right Encoder Value", Components.rETalon.getEncPosition());
    		SmartDashboard.putNumber("Left Encoder Value", Components.lETalon.getEncPosition());
    		Components.rGroup.setValue(rightValue);    	
    		Components.lGroup.setValue(-1 * leftValue);
    		currentTime = System.currentTimeMillis();
    	}
    	Components.rGroup.setValue(0);    	
		Components.lGroup.setValue(0);
		SmartDashboard.putString("Test Status", "Complete");
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
    	while (currentTime <= startTime + 2000){
    		Components.rollerGroup.setValue(rollerValue);
    		currentTime = System.currentTimeMillis();
    	}
		Components.rollerGroup.setValue(0);
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    	
    	//move roller forwards
		if ( (testChooser.getSelected().equals(AllOfThem)) || (testChooser.getSelected().equals(RollerTest))){
    	startTime = System.currentTimeMillis();
    	SmartDashboard.putNumber("Start Time For Test", startTime);
		SmartDashboard.putString("Test Status", "Moving roller backwards");
    	while (currentTime <= startTime + 2000){
    		Components.rollerGroup.setValue(-1 * rollerValue);
    		currentTime = System.currentTimeMillis();
    	}
		Components.rollerGroup.setValue(0);
		SmartDashboard.putString("Test Status", "Complete");
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
		while (currentTime <= startTime + 2000){
    		SmartDashboard.putBoolean("Is the detector working? You tell me!", Components.getCorrectedDetector());
    		currentTime = System.currentTimeMillis();
		}
		SmartDashboard.putString("Test Status", "Complete");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
    	
    	// Test the camera
    	
    	
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    		camera.interrupt();
    		SmartDashboard.putString("Test Status", "Complete");
    	}
	}