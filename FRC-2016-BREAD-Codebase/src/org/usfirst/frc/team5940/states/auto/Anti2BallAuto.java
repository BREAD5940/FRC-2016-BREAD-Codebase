package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Anti2BallAuto extends State {

	public Anti2BallAuto(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}
	protected void init() {
		Components.roller.setValue(1);
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) -AutoManager.defaultAutoSpeed);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AutoManager.moveForTime(AutoManager.BREACH_TIME, (float) AutoManager.defaultAutoSpeed);
		Thread.currentThread().interrupt();
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}
}
