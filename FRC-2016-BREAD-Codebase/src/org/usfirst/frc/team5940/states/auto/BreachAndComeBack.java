package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** this class will move the robot through a defense, pass the ball, and move back. 
 */
public class BreachAndComeBack extends State {

	public BreachAndComeBack(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	//MotorGroup shooter = Components.rollerGroup;
	
	@Override
	protected void init() {
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) AutoManager.defaultAutoSpeed);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) -AutoManager.defaultAutoSpeed);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
