package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;

public class ShootFromSpy extends State {

	public ShootFromSpy(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	DualMGShiftingDrivetrain driver = Components.drivetrain;
	MotorGroup shooter = Components.rollerGroup;
	
	@Override
	protected void init() {
		// TODO Make the right value.
		AutoStandardState.moveForDistance(400, (float) 0.5);
		
		if (AutoStandardState.leftUltrasonic.getRangeInches() < 6 || AutoStandardState.leftUltrasonic.getRangeInches() > 35
				|| AutoStandardState.rightUltrasonic.getRangeInches() - AutoStandardState.leftUltrasonic.getRangeInches() > 20) {
			// turn right
			while (AutoStandardState.leftUltrasonic.getRangeInches() < 6 || AutoStandardState.leftUltrasonic.getRangeInches() > 35
					|| AutoStandardState.rightUltrasonic.getRangeInches() - AutoStandardState.leftUltrasonic.getRangeInches() > 20) {
				driver.updateArcade(0, 0.25, 1);
			}
		} else if (AutoStandardState.rightUltrasonic.getRangeInches() < 6 || AutoStandardState.rightUltrasonic.getRangeInches() > 35
				|| AutoStandardState.leftUltrasonic.getRangeInches() - AutoStandardState.rightUltrasonic.getRangeInches() > 20) {
			// turn left
			while (AutoStandardState.rightUltrasonic.getRangeInches() < 6 || AutoStandardState.rightUltrasonic.getRangeInches() > 35
					|| AutoStandardState.leftUltrasonic.getRangeInches() - AutoStandardState.rightUltrasonic.getRangeInches() > 20) {
				driver.updateArcade(0, -0.25, 1);
			}
		}
		
		shooter.setValue(1);
		
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

}
