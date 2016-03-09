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
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoManager extends State {

	public AutoManager(RobotBase robot) {
		super(robot);
	}


	// DualMGDrivetrain driver;
	//int auto_program = 1;
	static int auto_distance_of_1 = 400; // TODO Get the right value
	static int auto_distance_of_3 = 400; // TODO Get the right value
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
	// rightEncoder.setDistancePerPulse(1);
	// leftEncoder.setDistancePerPulse(1);

	

	/**
	 * Moves the robot through a defense and back.
	 */
	public static void backAndForth() {
		moveForDistance(auto_distance_of_1, (float) 0.5);

		moveForDistance(auto_distance_of_1, (float) -0.5);
	}

	// Putting ball in courtyard if you already have the ball and going
	// through

	
	public void breachAndPass() {
		moveForDistance(auto_distance_of_3, (float) 0.5);

		Components.rollerGroup.setValue((float) 0.1);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Components.rollerGroup.setValue(0);

		moveForDistance(auto_distance_of_3, (float) -0.5);
	}

	// an auto program that goes forward, to a different defense, and goes
	// through that defense.
	
	/**
	 * This method breaches a defense a goes back to the middle area through another. 
	 */
	public void loopAround() {
		moveForDistance(auto_distance_of_4a, (float) 0.5);

		moveForDistance(auto_distance_of_4b, (float) 0.5);

		moveForDistance(auto_distance_of_4c, (float) 0.5);

		moveForDistance(auto_distance_of_4b, (float) 0.5);

		moveForDistance(auto_distance_of_4a, (float) 0.5);
	}

	/**
	 * moves forward
	 */
	public static void moveForward() {
		moveForDistance(auto_distance_of_1, (float) 0.5);
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
	 * @param distance 
	 * @param speed
	 */
	public static void moveForDistance(float distance, float speed) {
		float leftEncDistance = leftEncoderDistance;
		float rightEncDistance = rightEncoderDistance;
		leftEncDistance -= pastDistance;
		rightEncDistance -= pastDistance;
		CANTalon r1 = new CANTalon(1);
		CANTalon r2 = new CANTalon(2);
		CANTalon l3 = new CANTalon(3);
		CANTalon l4 = new CANTalon(4);
		while (leftEncoderDistance < distance && rightEncoderDistance < distance) {
			if (speed > 0) {
				if (leftEncoderRate > rightEncoderRate) {
					// Left is going faster than the right
					// Give right a bonus to match the left encoder speed
					rightBonusSpeed += 0.01;
				} else if (rightEncoderRate > leftEncoderRate) {
					// Right is going faster than the left
					// Give left a bonus to match the right encoder speed
					leftBonusSpeed += 0.01;
				}
			}
			else {
				if (leftEncoderRate < rightEncoderRate) {
					// Left is going faster than the right because it is going backwards
					rightBonusSpeed -= 0.01;
				}
				else if (rightEncoderRate < leftEncoderRate) {
					// Right is going faster than the left becuase it is going backwards
					leftBonusSpeed -= 0.01;
				}
			}
			newSpeeds = GeneralMethods.lowerToNumber(speed + leftBonusSpeed, speed + rightBonusSpeed);
			newRightSpeed = newSpeeds[1];
			newLeftSpeed = newSpeeds[0];
			r1.set(newRightSpeed);
			r2.set(newRightSpeed);
			l3.set(newLeftSpeed);
			l4.set(newLeftSpeed);

			SmartDashboard.putNumber("Speed of the left encoder", leftEncoderRate);
			SmartDashboard.putNumber("Speed of the right encoder", rightEncoderRate);
			SmartDashboard.putNumber("Distance of the left encoder", leftEncDistance);
			SmartDashboard.putNumber("Distance of the right encoder", rightEncDistance);
		}
		pastDistance += distance;
		Components.drivetrain.updateArcade(0, 0, 1);
	}

	
/**
 * updates the talon and encoders.
 */
	@Override
	protected void update() {
		CANTalon r2 = Components.rETalon;
		CANTalon l3 = Components.lETalon;
		SmartDashboard.putNumber("Right Encoder Position", r2.getEncPosition());
		SmartDashboard.putNumber("Right Encoder Position", l3.getEncPosition());
		leftEncoderDistance = l3.getEncPosition();
		rightEncoderDistance = -r2.getEncPosition();
		leftEncoderRate = l3.getEncVelocity();
		rightEncoderRate = -r2.getEncVelocity();
		// // TODO Make the auto Note: This should be in INIT()
	}

@Override
protected void init() {
	// TODO Auto-generated method stub
	
}

}
