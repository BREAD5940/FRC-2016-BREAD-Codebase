package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.other.GeneralMethods;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoManager extends State {

	public AutoManager(RobotBase robot) {
		super(robot);
	}

	// DualMGDrivetrain driver;
	// int auto_program = 1;
	static float auto_distance_of_1 = 400; // TODO Get the right value
	static float auto_distance_of_3 = 15500; // TODO Get the right value
	static float defaultAutoSpeed = (float) 0.6;
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
	static long breachDistance = 5000;
	static float rightEncoderDistance;
	static float leftEncoderDistance;
	static float rightEncoderRate;
	static float leftEncoderRate;
	static float pastDistance;
	static float rightBonusSpeed = 0;
	static float leftBonusSpeed = 0;
	static float newRightSpeed;
	static float newLeftSpeed;
	static float[] newSpeeds;
	static float TestNumber = 0;
	static long currentTime;
	static long startTime;
	public static final long BREACH_TIME = 5000;
	// rightEncoder.setDistancePerPulse(1);
	// leftEncoder.setDistancePerPulse(1);

	/**
	 * Moves the robot through a defense and back to the courtyard through the
	 * same defense.
	 */
	public static void backAndForth() {
		SmartDashboard.putBoolean("Back And Forth Activated", true);
		moveForDistance((float) 0.2,3000);

		moveForDistance((float) -0.2,3000);
	}

	/**
	 * Moves the robot through a defense and then throws a ball out of the
	 * pickup system and into the courtyard.
	 */
	/*public void breachAndPass() {
		moveForDistance( (float) 0.5,3000);

		Components.rollerGroup.setValue((float) 0.5);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Components.rollerGroup.setValue(0);

		moveForDistance((float) -0.5,3000);
	}*/

	/**
	 * This method breaches a defense a goes back to the neutral zone through
	 * another defense. All distances are set by variables auto_distance_of + a
	 * number + a letter in some instances.
	 */
	public void loopAround() {
		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);
	}

	/**
	 * moves forward a set distance.
	 */
	public static void moveForward() {
		SmartDashboard.putBoolean("Move Forward Activated", true);
		moveForDistance((float) 0.2,3000);
	}

	/*
	 * public void breachAndShoot() { moveForDistance(auto_distance_of_3,
	 * (float) 0.5);
	 * 
	 * moveForDistance(auto_distance_of_6, (float) 0.5);
	 * 
	 * driver.spinToAngle(30, true); // Both need to be greater than 10 inches
	 * // Both need to be less than 35 inches // Both need to be within 20
	 * inches of each other // TODO get correct distances if
	 * (leftUltrasonic.getRangeInches() < 6 || leftUltrasonic.getRangeInches() >
	 * 35 || rightUltrasonic.getRangeInches() - leftUltrasonic.getRangeInches()
	 * > 20) { // turn right while (leftUltrasonic.getRangeInches() < 6 ||
	 * leftUltrasonic.getRangeInches() > 35 || rightUltrasonic.getRangeInches()
	 * - leftUltrasonic.getRangeInches() > 20) { driver.updateArcade(0, 0.25,
	 * 1); } } else if (rightUltrasonic.getRangeInches() < 6 ||
	 * rightUltrasonic.getRangeInches() > 35 || leftUltrasonic.getRangeInches()
	 * - rightUltrasonic.getRangeInches() > 20) { // turn left while
	 * (rightUltrasonic.getRangeInches() < 6 || rightUltrasonic.getRangeInches()
	 * > 35 || leftUltrasonic.getRangeInches() -
	 * rightUltrasonic.getRangeInches() > 20) { driver.updateArcade(0, -0.25,
	 * 1); } } driver.updateArcade(0, 0, 1);
	 * 
	 * shooter.setValue((float) 0.5); try { Thread.sleep(500); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } shooter.setValue(0); }
	 */

	/**
	 * Moves for a certain distance.
	 * 
	 * @param distance
	 * @param speed
	 */
	public static void moveForDistance(float speed, long time) {
		//Components.r1.setPosition(0);
		//Components.l1.setPosition(0);
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// Components.r1.set(0);
		// Components.r2.set(0);
		// Components.l1.set(0);
		// Components.l2.set(0);
		TestNumber += 1;
		SmartDashboard.putNumber("Move For Distance", TestNumber);
		// r1.setPosition(0);
		// l3.setPosition(0);
		// if (TestNumber == 1) {
		//SmartDashboard.putNumber("Distance", distance);
		SmartDashboard.putNumber("Right Encoder Start Distance", Components.r1.getEncPosition());
		SmartDashboard.putNumber("Left Encoder Start Distance", Components.l1.getEncPosition());
		// }
		startTime = System.currentTimeMillis();
		currentTime = 0;
		while (currentTime <= startTime + time) {

			SmartDashboard.putNumber("Left Encoder Distance", Components.l1.getEncPosition());
			SmartDashboard.putNumber("Right Encoder Distance", Components.r1.getEncPosition());

			// if (speed > 0) {
			// if (leftEncoderRate > rightEncoderRate) {
			// // Left is going faster than the right
			// // Give right a bonus to match the left encoder speed
			// rightBonusSpeed += 0.01;
			// } else if (rightEncoderRate > leftEncoderRate) {
			// // Right is going faster than the left
			// // Give left a bonus to match the right encoder speed
			// leftBonusSpeed += 0.01;
			// }
			// }
			// else {
			// if (leftEncoderRate < rightEncoderRate) {
			// // Left is going faster than the right because it is going
			// backwards
			// rightBonusSpeed -= 0.01;
			// }
			// else if (rightEncoderRate < leftEncoderRate) {
			// // Right is going faster than the left becuase it is going
			// backwards
			// leftBonusSpeed -= 0.01;
			// }
			// }
			// newSpeeds = GeneralMethods.lowerToNumber(speed + leftBonusSpeed,
			// speed + rightBonusSpeed);
			// newRightSpeed = newSpeeds[1];
			// newLeftSpeed = newSpeeds[0];
			Components.drivetrain.updateArcade(speed, 0, 1);
			// Components.r1.set(-speed);
			// Components.r2.set(-speed);
			// Components.l1.set(speed);
			// Components.l2.set(speed);

			// SmartDashboard.putNumber("Speed of the left encoder",
			// leftEncoderRate);
			// SmartDashboard.putNumber("Speed of the right encoder",
			// rightEncoderRate);
			// SmartDashboard.putNumber("Distance of the left encoder",
			// leftEncDistance);
			// SmartDashboard.putNumber("Distance of the right encoder",
			// rightEncDistance);
			currentTime = System.currentTimeMillis();
			SmartDashboard.putNumber("currentTime", currentTime);
		}
		// SmartDashboard.putNumber("Left Encoder Distance",
		// l3.getEncPosition());
		// SmartDashboard.putNumber("Right Encoder Distance",
		// r1.getEncPosition());
		Components.drivetrain.updateArcade(0, 0, 1);
		// Components.r1.set(0);
		// Components.r2.set(0);
		// Components.l1.set(0);
		// Components.l2.set(0);

	}
	
	public static void moveForTime(long time, double speed) {
		Components.drivetrain.updateArcade(speed, 0, 1);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Components.drivetrain.updateArcade(0, 0, 1);
	}

	@Override
	protected void update() {
		// // TODO Make the auto Note: This should be in INIT()
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
	}

}
