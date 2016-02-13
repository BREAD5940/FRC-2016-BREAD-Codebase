package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.MotorGroup;
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

	//DualMGDrivetrain driver;
	Encoder leftEncoder = new Encoder(2, 3);
	Encoder rightEncoder = new Encoder(0, 1);
	Victor r1 = new Victor(1);
	Victor r2 = new Victor(3);
	Victor l3 = new Victor(0);
	Victor l4 = new Victor(2);
	Ultrasonic sensor = new Ultrasonic(1, 2);

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
//	rightEncoder.setDistancePerPulse(1);
//	leftEncoder.setDistancePerPulse(1);

	DualMGDrivetrain driver = new DualMGShiftingDrivetrain(new VictorSimpleGroup(new Victor[] { l3, l4 }, false),
			new VictorSimpleGroup(new Victor[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);

	MotorGroup shooter = new CANTalonSimpleGroup(new CANTalon[] { new CANTalon(0) }, false);// TODO
																							// Correct
																							// CAN
																							// port

	public AutoStandardState(RobotBase robot) {
		// Call the state constructor
		super(robot);
	}

	public void back_and_forth() {
		while (leftEncoder.getDistance() < auto_distance_of_1 && rightEncoder.getDistance() < auto_distance_of_1) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();
		while (leftEncoder.getDistance() < auto_distance_of_1 && rightEncoder.getDistance() < auto_distance_of_1) {
			driver.updateArcade(-0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

	}

	public void ultrasonic_home_in() {
		while (sensor.getRangeInches() < 20) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance From Objects", sensor.getRangeInches());
		}
		driver.updateArcade(0, 0, 0);
	}

	// Putting ball in courtyard if you already have the ball and going
	// through
	
	public void breach_and_pass () {
		while (leftEncoder.getDistance() < auto_distance_of_3 && rightEncoder.getDistance() < auto_distance_of_3) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		shooter.setValue((float) 0.1);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shooter.setValue(0);

		while (leftEncoder.getDistance() < auto_distance_of_3 && rightEncoder.getDistance() < auto_distance_of_3) {
			driver.updateArcade(-0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();
	}

	// an auto program that goes forward, to a different defense, and goes
	// through that defense.
	public void loop_around() {
		while (leftEncoder.getDistance() < auto_distance_of_4a
				&& rightEncoder.getDistance() < auto_distance_of_4a) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4b
				&& rightEncoder.getDistance() < auto_distance_of_4b) {
			driver.updateArcade(0, 0.5, 1); // TODO find the right direction
											// and value.
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4c
				&& rightEncoder.getDistance() < auto_distance_of_4c) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4b
				&& rightEncoder.getDistance() < auto_distance_of_4b) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4a
				&& rightEncoder.getDistance() < auto_distance_of_4a) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

	}

	
	public void move_forward() {
		while (leftEncoder.getDistance() < auto_distance_of_1 && rightEncoder.getDistance() < auto_distance_of_1) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

	}
	
	@Override
	protected void init() {
		// TODO Make the init
		

		// driver = new DualMGShiftingDrivetrain(new VictorSimpleGroup(new
		// Victor[] { l3, l4 }, false),
		// new VictorSimpleGroup(new Victor[] { r1, r2 }, true), new
		// DoubleSolenoid(0, 1), 1);

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

		

		// back-and-forth auto program
		

	}

	@Override
	protected void update() {
		// TODO Make the auto Note: This should be in INIT()

	}
}
