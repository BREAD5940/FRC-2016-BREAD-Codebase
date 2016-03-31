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
		AutoManager.moveForDistance((float) AutoManager.defaultAutoSpeed, 3000);
		AutoManager.moveForDistance((float) -AutoManager.defaultAutoSpeed, 3000);
		AutoManager.moveForDistance((float) AutoManager.defaultAutoSpeed, 3000);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shooter.setValue((float) 0.2);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shooter.setValue(0);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
