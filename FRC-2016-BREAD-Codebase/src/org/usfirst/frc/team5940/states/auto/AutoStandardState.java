package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.VictorSimpleGroup;
import org.usfirst.frc.team5940.states.State;

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
		Victor r1 = new Victor(1);
		Victor r2 = new Victor(3);
		Victor l3 = new Victor(0);
		Victor l4 = new Victor(2);
		int auto_program = 1;
		int auto_distance_of_1 = 400;
		Ultrasonic sensor = new Ultrasonic(1, 2);

		driver = new DualMGShiftingDrivetrain(new VictorSimpleGroup(new Victor[] { l3, l4 }, false),
				new VictorSimpleGroup(new Victor[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);
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
		
	}

	@Override
	protected void update() {
		// TODO Make the auto Note: now that I'm looking at it, this should be
		// in INIT()

	}
}
