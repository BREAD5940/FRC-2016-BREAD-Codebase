package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoStandardState extends State {

	DualMGDrivetrain driver;
	Encoder leftEncoder;
	Encoder rightEncoder;

	public AutoStandardState(RobotBase robot) {
		// Call the state constructor
		super(robot);
	}

	@Override
	protected void init() {
		// TODO Make the init
		rightEncoder = new Encoder(0, 1);
		leftEncoder = new Encoder(2, 3);
		CANTalon r1 = new CANTalon(1);
		CANTalon r2 = new CANTalon(3);
		CANTalon l3 = new CANTalon(0);
		CANTalon l4 = new CANTalon(2);
		driver = new DualMGShiftingDrivetrain(new CANTalonSimpleGroup(new CANTalon[] { l3, l4 }, false),
				new CANTalonSimpleGroup(new CANTalon[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);
		// while (leftEncoder.getDistance() < 400) {
		// driver.moveStraight((float) 0.75, (float) leftEncoder.getRate(),
		// (float) rightEncoder.getRate());
		// }
		// leftEncoder.reset();
		// rightEncoder.reset();
		// while (leftEncoder.getDistance() < 400) {
		// driver.moveStraight((float) -0.75, (float) leftEncoder.getRate(),
		// (float) rightEncoder.getRate());
		// }
		leftEncoder.setDistancePerPulse(1);
		while (leftEncoder.getDistance() < 400) {
			driver.updateArcade(0.25, 0, 1);
			SmartDashboard.putNumber("Distance", leftEncoder.getDistance());
		}
		driver.updateArcade(0, 0, 1);
	}

	@Override
	protected void update() {
		// TODO Make the auto

	}
}
