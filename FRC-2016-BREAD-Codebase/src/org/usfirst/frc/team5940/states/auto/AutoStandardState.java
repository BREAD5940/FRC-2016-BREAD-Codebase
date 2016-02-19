package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoStandardState extends State {

	// DualMGDrivetrain driver;
	Encoder leftEncoder = new Encoder(2, 3);
	Encoder rightEncoder = new Encoder(0, 1);
	CANTalon r1 = new CANTalon(1);
	CANTalon r2 = new CANTalon(3);
	CANTalon l3 = new CANTalon(0);
	CANTalon l4 = new CANTalon(2);
	Ultrasonic leftUltrasonic = new Ultrasonic(4, 5);
	Ultrasonic rightUltrasonic = new Ultrasonic(6, 7);
	int auto_program = 1;
	int auto_distance_of_1 = 400; // TODO Get the right value
	int auto_distance_of_3 = 400; // TODO Get the right value
	int auto_distance_of_4a = 400; // TODO Get the right value
	int auto_distance_of_4b = 100;
	int auto_distance_of_4c = 800; // TODO Get the right value (Note: It
									// might change depending on
									// circumstance) (Note again: if this is
									// smaller than "auto_distance_of_4a"
									// than you measured VERY wrong )
	int auto_distance_of_5 = 400;
	int auto_distance_of_6 = 500;
	int distanceToBackOfHole;
	AnalogInput potato = new AnalogInput(0);
	// rightEncoder.setDistancePerPulse(1);
	// leftEncoder.setDistancePerPulse(1);

	DualMGDrivetrain driver = new DualMGShiftingDrivetrain(new CANTalonSimpleGroup(new CANTalon[] { l3, l4 }, false),
			new CANTalonSimpleGroup(new CANTalon[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);

	MotorGroup shooter = new CANTalonSimpleGroup(new CANTalon[] { new CANTalon(0) }, false);// TODO
																							// Correct
																							// CAN
																							// port

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

		// driver = new DualMGShiftingDrivetrain(new VictorSimpleGroup(new
		// Victor[] { l3, l4 }, false),
		// new VictorSimpleGroup(new Victor[] { r1, r2 }, true), new
		// DoubleSolenoid(0, 1), 1);

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
