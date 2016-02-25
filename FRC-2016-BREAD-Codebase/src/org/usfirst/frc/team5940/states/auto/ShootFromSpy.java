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
	
	/** 
	 * This class can shoot from the spy box.
	 */
	@Override
	protected void init() {
		// TODO Make the right value.
		AutoManager.moveForDistance(400, (float) 0.5);
		
		if (AutoManager.leftUltrasonic.getRangeInches() < 6 || AutoManager.leftUltrasonic.getRangeInches() > 35
				|| AutoManager.rightUltrasonic.getRangeInches() - AutoManager.leftUltrasonic.getRangeInches() > 20) {
			// turn right
			while (AutoManager.leftUltrasonic.getRangeInches() < 6 || AutoManager.leftUltrasonic.getRangeInches() > 35
					|| AutoManager.rightUltrasonic.getRangeInches() - AutoManager.leftUltrasonic.getRangeInches() > 20) {
				driver.updateArcade(0, 0.25, 1);
			}
		} else if (AutoManager.rightUltrasonic.getRangeInches() < 6 || AutoManager.rightUltrasonic.getRangeInches() > 35
				|| AutoManager.leftUltrasonic.getRangeInches() - AutoManager.rightUltrasonic.getRangeInches() > 20) {
			// turn left
			while (AutoManager.rightUltrasonic.getRangeInches() < 6 || AutoManager.rightUltrasonic.getRangeInches() > 35
					|| AutoManager.leftUltrasonic.getRangeInches() - AutoManager.rightUltrasonic.getRangeInches() > 20) {
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
