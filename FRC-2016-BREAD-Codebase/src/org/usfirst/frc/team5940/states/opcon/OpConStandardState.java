package org.usfirst.frc.team5940.states.opcon;

import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;

import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.VictorSimpleGroup;
//import org.usfirst.frc.team5940.motorcontrol.digtalinput;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import other.GeneralMethods;


public class OpConStandardState extends State {

	Joystick controller;
	double horizontal;
	double forward;
	double maxValue;
	boolean shiftDown;
	boolean shiftUp;
	DualMGDrivetrain driver;
    boolean buttonHeld;
	DigitalInput limitswitch = new DigitalInput(1);
	// for testing the navx when it is setup
	// AHRS navx;
	float scaleFactor;
	CANTalon ballControllerCANTaloon;

	public OpConStandardState(RobotBase robot) {
		// Call the state thing
		super(robot);
	}

	@Override
	protected void init() {
		// Notify about new code
		SmartDashboard.putBoolean("New Code", true);
		// Set controller to a joystick
		controller = new Joystick(1);
		// driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new
		// CANTalonDrive(3,4)); Doesn't Work
		// Use driver = new DualMGDrivetrain(new CANTalonDrive(new Talon[]{new
		// Talon(1),new Talon(2)},new Talon[]{new Talon(3),new
		// Talon(4)}),false);
		// TODO make the variables not equal to null

		// WIRING DIFFERENT FOR DIFFERENT ROBOTS!!!
		// Set victors
		
		initializeCANTaloons();
		ballControllerCANTaloon = new CANTalon(0);
		// for testing the navx when it is setup
		// navx = new AHRS(SPI.Port.kMXP);
		// Set scale factor
		scaleFactor = 1;

		// TODO incorrect inversions here and below...
		// Set the driver
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

		// For testing the navx when it is setup
		// SmartDashboard.putNumber("Angle", navx.getAngle());
		// Set all of the things
		forward = controller.getRawAxis(1);
		horizontal = controller.getRawAxis(2);
		//maxValue = controller.getRawAxis(3);
		SmartDashboard.putNumber("Forward Input", forward);
		forward = GeneralMethods.powInputFixed(forward, 2);
		horizontal = GeneralMethods.powInputFixed(horizontal, 2);
//		if (controller.getRawButton(3) && driver.turning == false) {
//			driver.spinToAngle(controller.getDirectionDegrees(), true);
//		}
		driver.updateArcade(forward, horizontal, scaleFactor);
		
//		if (controller.getRawButton(2)){
//    		double victor_thing1 = victor1.get();
//    		double victor_thing2 = victor2.get();
//    		double victor_thing3 = victor3.get();
//    		double victor_thing4 = victor4.get();
//    		victor_thing1 = victor_thing1 * -1;
//    		victor_thing2 = victor_thing2 * -1;
//    		victor_thing3 = victor_thing3 * -1;
//    		victor_thing4 = victor_thing4 * -1;
//    		victor1.set(victor_thing1);
//    		victor2.set(victor_thing2);
//    		victor3.set(victor_thing3);
//    		victor4.set(victor_thing4);
//    		try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		victor1.set(0);
//    		victor2.set(0);
//    		victor3.set(0);
//    		victor4.set(0);
    		
			
			// Yo Dawg I commented out this code because I don't know what it does and it was erroring
//    		float[] vals = driver.getSetValue();
//    		vals[0] = vals[0]*-1;
//    		vals[1] = vals[1]*-1;
//    		driver.setValues(vals[0], vals[1]);
//    		try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) { e.printStackTrace(); }
//    		driver.setValues(0, 0);
//    		while(controller.getRawButton(2));
//    	}
		
		
		if (controller.getRawButton(1) && limitSwitchHeld()) {
			ballControllerCANTaloon.set(0.5);
		}
		else if (controller.getRawButton(1) && !limitSwitchHeld()) {
			ballControllerCANTaloon.set(-0.5);
		}
		else {
			ballControllerCANTaloon.set(0);
		}
		
		shiftDown = controller.getRawButton(3);
		shiftUp = controller.getRawButton(4);
		
		if (shiftUp) {
			driver.setGears(2);
		}
		else if (shiftDown) {
			driver.setGears(1);
		}
	}
	
	public boolean limitSwitchHeld(){
		buttonHeld = limitswitch.get();
		SmartDashboard.putBoolean("ballHeld?", buttonHeld);
		return buttonHeld;
	}
	
	public void initializeCANTaloons() {
		CANTalon r1 = new CANTalon(1);
		CANTalon r2 = new CANTalon(2);
		CANTalon l3 = new CANTalon(3);
		CANTalon l4 = new CANTalon(4);
		
		driver = new DualMGShiftingDrivetrain(new CANTalonSimpleGroup(new CANTalon[] { l3, l4 }, false),
				new CANTalonSimpleGroup(new CANTalon[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);
	}
	
	public void initializeVictors() {
		Victor r1 = new Victor(1);
		Victor r2 = new Victor(3);
		Victor l3 = new Victor(0);
		Victor l4 = new Victor(2);
		
		driver = new DualMGShiftingDrivetrain(new VictorSimpleGroup(new Victor[] { l3, l4 }, false),
				new VictorSimpleGroup(new Victor[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);
	}
}

