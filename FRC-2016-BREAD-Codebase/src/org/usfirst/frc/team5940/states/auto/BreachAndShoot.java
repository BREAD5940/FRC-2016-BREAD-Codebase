package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;

public class BreachAndShoot extends State {

	public BreachAndShoot(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	DualMGShiftingDrivetrain driver = Components.drivetrain;
	MotorGroup shooter = Components.rollerGroup;
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		AutoStandardState.moveForDistance(400, (float) 0.5);
		
		AutoStandardState.moveForDistance(500, (float) 0.5);

		driver.spinToAngle(30, true);
		// Both need to be greater than 10 inches
		// Both need to be less than 35 inches
		// Both need to be within 20 inches of each other
		// TODO get correct distances
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
		driver.updateArcade(0, 0, 1);
		
		shooter.setValue((float) 0.5);
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
