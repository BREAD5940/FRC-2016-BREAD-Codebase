package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.MotorGroup;
import org.usfirst.frc.team5940.motorcontrol.VictorSimpleGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoStandardState extends State {

	// DualMGDrivetrain driver;
	Encoder leftEncoder = new Encoder(2, 3);
	Encoder rightEncoder = new Encoder(0, 1);
	Victor r1 = new Victor(1);
	Victor r2 = new Victor(3);
	Victor l3 = new Victor(0);
	Victor l4 = new Victor(2);
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

	public void backAndForth() {
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

	public void ultrasonicHomeIn() {
		while (leftUltrasonic.getRangeInches() > 20) {
//			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance From Objects", leftUltrasonic.getRangeInches());
		}
//		driver.updateArcade(0, 0, 0);
	}

	// Putting ball in courtyard if you already have the ball and going
	// through

	public void breachAndPass() {
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
	public void loopAround() {
		while (leftEncoder.getDistance() < auto_distance_of_4a && rightEncoder.getDistance() < auto_distance_of_4a) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4b && rightEncoder.getDistance() < auto_distance_of_4b) {
			driver.updateArcade(0, 0.5, 1); // TODO find the right direction
											// and value.
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4c && rightEncoder.getDistance() < auto_distance_of_4c) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4b && rightEncoder.getDistance() < auto_distance_of_4b) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_4a && rightEncoder.getDistance() < auto_distance_of_4a) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void moveForward() {
		while (leftEncoder.getDistance() < auto_distance_of_1 && rightEncoder.getDistance() < auto_distance_of_1) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

	}

	public void breachAndShoot() {
		while (leftEncoder.getDistance() < auto_distance_of_3 && rightEncoder.getDistance() < auto_distance_of_3) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());

		}
		//driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		while (leftEncoder.getDistance() < auto_distance_of_6 && rightEncoder.getDistance() < auto_distance_of_6) {
			driver.updateArcade(0.5, 0, 1);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncoder.getDistance());
			SmartDashboard.putNumber("Distance of the right encoder", rightEncoder.getDistance());
		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();

		driver.spinToAngle(30, true);
		// Both need to be greater than 10 inches
		// Both need to be less than 35 inches
		// Both need to be within 20 inches of each other
		// TODO get correct distances
		if (leftUltrasonic.getRangeInches() < 6 || leftUltrasonic.getRangeInches() > 35
				|| rightUltrasonic.getRangeInches() - leftUltrasonic.getRangeInches() > 20) {
			// turn right
			while (leftUltrasonic.getRangeInches() < 6 || leftUltrasonic.getRangeInches() > 35
					|| rightUltrasonic.getRangeInches() - leftUltrasonic.getRangeInches() > 20) {
				driver.updateArcade(0, 0.25, 1);
			}
		} else if (rightUltrasonic.getRangeInches() < 6 || rightUltrasonic.getRangeInches() > 35
				|| leftUltrasonic.getRangeInches() - rightUltrasonic.getRangeInches() > 20) {
			// turn left
			while (rightUltrasonic.getRangeInches() < 6 || rightUltrasonic.getRangeInches() > 35
					|| leftUltrasonic.getRangeInches() - rightUltrasonic.getRangeInches() > 20) {
				driver.updateArcade(0, -0.25, 1);
			}
		}
		driver.updateArcade(0, 0, 1);
		leftEncoder.reset();
		rightEncoder.reset();
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
		SmartDashboard.putNumber("Distance From Objects", potato.getVoltage()/0.1024);
		SmartDashboard.putNumber("Average Value", Math.sqrt(potato.getAverageValue())/6);
		SmartDashboard.putNumber("Voltage Thingy", potato.getVoltage());
		SmartDashboard.putNumber("Value Thingy", (potato.getValue())/24);
		SmartDashboard.putNumber("Average Voltage", Math.pow(potato.getAverageVoltage(), 2)*100);
	}
}
