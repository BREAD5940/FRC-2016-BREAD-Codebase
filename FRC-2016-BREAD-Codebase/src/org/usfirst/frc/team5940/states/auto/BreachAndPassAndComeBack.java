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
		SmartDashboard.putNumber("pos", 0);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) AutoManager.defaultAutoSpeed);
		SmartDashboard.putNumber("pos", 1);
		Components.roller.pass();
		SmartDashboard.putNumber("pos", 2);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) -AutoManager.defaultAutoSpeed);
		SmartDashboard.putNumber("pos", 3);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
