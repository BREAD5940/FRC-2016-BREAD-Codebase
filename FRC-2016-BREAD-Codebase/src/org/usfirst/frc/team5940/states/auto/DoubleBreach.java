package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * This class moves through a defense, back, and through again. This will breach a defense twice in Autonomous.
 */
public class DoubleBreach extends State {

	public DoubleBreach(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initalizes the auto program.
	 */
	@Override
	protected void init() {
		SmartDashboard.putString("Current Auto", "Double Breach");
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) AutoManager.defaultAutoSpeed);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) -AutoManager.defaultAutoSpeed);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) AutoManager.defaultAutoSpeed);
		Thread.currentThread().interrupt();
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
