package org.usfirst.frc.team5940.states.opcon;

import java.lang.reflect.Array;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.other.GeneralMethods;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OpConStandardState extends State {

	Joystick controller = Components.controller;
	double horizontal;
	double forward;
	double maxValue;
	boolean shiftDown;
	boolean shiftUp;
	double shooterControl;
	DualMGShiftingDrivetrain driver = Components.drivetrain;
	MotorGroup roller = Components.rollerGroup;
	int working = 0;
	float scaleFactor;
	
    String driverPerson = "NickH"; 

	public OpConStandardState(RobotBase robot) {
		// Call the state thing
		super(robot);
	}

	@Override
	protected void init() {
	}

	@Override
	protected void update() {
//		forward = Components.getCorrectedAxis(3)-Components.getCorrectedAxis(2);
//		horizontal = Components.getCorrectedAxis(4);
//		SmartDashboard.putNumber("Forward Input", forward);
//		forward = GeneralMethods.powInputFixed(forward, 2);
//		horizontal = GeneralMethods.powInputFixed(horizontal, 2);

		SmartDashboard.putNumber("Update working?", working++);
		
		driver.updateArcade(forward, horizontal, 1);
		
		if(driverPerson.equals("NickH")) setNickH();
		if(driverPerson.equals("Hung")) setHung();
		if(driverPerson.equals("Alex")) setAlex();
		if(driverPerson.equals("Marius")) setMarius();
		
		
		shiftDown = controller.getRawButton(1);
		shiftUp = controller.getRawButton(4);
		
		if (shiftUp) {
			driver.setGears(0);
		}
		else if (shiftDown) {
			driver.setGears(1);
		}
		
		float newRVal = (float) shooterControl;
		if(Components.getCorrectedDetector() && newRVal < 0) newRVal = 0;
		roller.setValue(newRVal);
	}
	
	private void setNickH() {
		SmartDashboard.putNumber("NickH working?", working++);
		forward = (Components.getCorrectedAxis(3) - Components.getCorrectedAxis(2));
		horizontal = Components.getCorrectedAxis(4);
		shooterControl = Components.getCorrectedAxis(1);
		
		if (controller.getRawButton(1)){
			driver.setValues(0, 0);
		}
		
		if (controller.getRawButton(2)){

    		
    		float[] vals = driver.getSetValue();
    		vals[0] = vals[0]*-1;
    		vals[1] = vals[1]*-1;
    		driver.setValues(vals[0], vals[1]);
    		try {
				Thread.sleep(200);
			} catch (InterruptedException e) { e.printStackTrace(); }
    		driver.setValues(0, 0);
    		while(controller.getRawButton(2));
    	}
		
		shiftDown = (controller.getPOV(0) == 180);
		shiftUp = (controller.getPOV(0) == 0);
	}
	
	private void setHung() {
		
	}
	
	private void setAlex() {
		
	}
	
	private void setMarius() {
		
	}
}
