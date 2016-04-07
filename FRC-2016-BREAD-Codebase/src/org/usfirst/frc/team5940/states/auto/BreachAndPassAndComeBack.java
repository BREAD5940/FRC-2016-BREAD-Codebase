package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * this class will move the robot through a defense, pass the ball, and move
 * back.
 */
public class BreachAndPassAndComeBack extends State {

	public BreachAndPassAndComeBack(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void init() {
		// TODO Auto-generated method stub
		SmartDashboard.putBoolean("Started", true);

		AutoManager.moveForDistance((float) AutoManager.defaultAutoSpeed, AutoManager.breachDistance);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Components.roller.pass();

		SmartDashboard.putBoolean("At the end", true);
		AutoManager.moveForDistance((float) -AutoManager.defaultAutoSpeed, AutoManager.breachDistance);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
