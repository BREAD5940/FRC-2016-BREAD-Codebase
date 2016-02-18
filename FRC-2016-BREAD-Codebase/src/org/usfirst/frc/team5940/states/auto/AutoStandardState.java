package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.VictorSimpleGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
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
		int auto_program = 1;
		int auto_distance_of_1 = 400;
		Ultrasonic sensor = new Ultrasonic(1, 2);

		driver = new DualMGShiftingDrivetrain(new CANTalonSimpleGroup(new CANTalon[] { l3, l4 }, false),
				new CANTalonSimpleGroup(new CANTalon[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);
				// while (leftEncoder.getDistance() < 400) {
				// driver.moveStraight((float) 0.75, (float)
				// leftEncoder.getRate(),
				// (float) rightEncoder.getRate());
				// }
				// leftEncoder.reset();
				// rightEncoder.reset();
				// while (leftEncoder.getDistance() < 400) {
				// driver.moveStraight((float) -0.75, (float)
				// leftEncoder.getRate(),
				// (float) rightEncoder.getRate());
				// }

		// back-and-forth auto program
		if (auto_program == 1) {
			leftEncoder.setDistancePerPulse(1);
			rightEncoder.setDistancePerPulse(1);
			while (leftEncoder.getDistance() < auto_distance_of_1 && rightEncoder.getDistance() < auto_distance_of_1) {
				driver.updateArcade(0.5, 0.5, 1);
				SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
				SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

			}
			driver.updateArcade(0, 0, 1);
			leftEncoder.reset();
			rightEncoder.reset();
			while (leftEncoder.getDistance() < auto_distance_of_1 && rightEncoder.getDistance() < auto_distance_of_1) {
				driver.updateArcade(-0.5, -0.5, 1);
				SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
				SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

			}
			driver.updateArcade(0, 0, 1);
			leftEncoder.reset();
			rightEncoder.reset();

		}
		else if (auto_program == 2) {
			while (sensor.getRangeInches() < 20) {
				driver.updateArcade(0.5, 0, 1);
				SmartDashboard.putNumber("Distance From Objects", sensor.getRangeInches());
			}
			driver.updateArcade(0, 0, 0);
		}
	}

	@Override
	protected void update() {
		// TODO Make the auto Note: now that I'm looking at it, this should be
		// in INIT()

	}
}
