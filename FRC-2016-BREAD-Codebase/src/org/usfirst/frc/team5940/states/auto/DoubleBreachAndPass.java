package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * This class moves through a defense, back, and through again. This will breach a defense twice in Autonomous.
 */
public class DoubleBreachAndPass extends State {

	public DoubleBreachAndPass(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	MotorGroup shooter = Components.rollerGroup;
	
	/**
	 * Initalizes the auto program.
	 */
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("Current Auto", "Double Breach and Pass");
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) AutoManager.defaultAutoSpeed);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) -AutoManager.defaultAutoSpeed);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) AutoManager.defaultAutoSpeed);
		Components.roller.pass();
		Thread.currentThread().interrupt();
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
