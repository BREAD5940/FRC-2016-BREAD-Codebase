package org.usfirst.frc.team5940.motorcontrol;

import java.lang.reflect.Array;

import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.other.GeneralMethods;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DualMGDrivetrain {

	// The variables that stores the left and right motor groups
	public final MotorGroup left;
	public final MotorGroup right;

	// This stores the variables in order to make the robot move straight
	// this will most likely not be used for competition robot because there
	// are better ways to do this
	public float leftSpeedBonus = 0;
	public float rightSpeedBonus = 0;
	public float leftSpeedPenalty = 0;
	public float rightSpeedPenalty = 0;

	// This stores the previous angle the robot turned to for the spin to angle
	// method.
	public double previousAngle = 0;
	// This says whether or not the robot is turning for the spin to angle
	// method.
	public boolean turning = false;

	/**
	 * The method to initialize dualMGDrivetrain
	 * 
	 * @param left
	 *            Makes a MotorGroup equal to the input to be used later by
	 *            DualMGDrivetrain
	 * @param right
	 *            Makes a MotorGroup equal to the input to be used later by
	 *            DualMGDrivetrain
	 */
	public DualMGDrivetrain(MotorGroup left, MotorGroup right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * This returns the maximum amount of gears that both of the motorGroups
	 * have.
	 * 
	 * @return an int representing above
	 */
	public int getGearsAmount() {

		// This is the current number of gears that each of the motorGroups have
		int leftGearCount;
		int rightGearCount;

		leftGearCount = left.getGearsAmount();
		rightGearCount = right.getGearsAmount();

		// This checks the which gear is smaller and returns that one
		if (leftGearCount < rightGearCount) {
			return leftGearCount;
		} else {
			return rightGearCount;
		}
	}

	/**
	 * This sets the gears to the input and if you input a gear that is greater
	 * than the amount of gears we have than it will set it to the max gear. Oh
	 * yeah by the way I have the total amount of gears currently set to four
	 * and we might need to change that.
	 * 
	 * @param gear
	 *            the gear to change to
	 */
	public void setGears(int gear) {

		// This is the total amount of gears in the gear box
		int totalGears = 4;

		// This is because in order to set the gear you need to subtract one?
		gear = gear - 1;
		if (gear < 0) {
			// If the gear is less than one set it to zero
			left.setGear(0);
			right.setGear(0);
		} else if (gear >= totalGears) {
			// If the new gear is greater than the total amount of gears
			left.setGear(totalGears - 1);
			right.setGear(totalGears - 1);
		} else {
			// If the gear is within the bounds
			left.setGear(gear);
			right.setGear(gear);
		}
	}

	/**
	 * This returns the current gear for the left motor. The right gear will be
	 * the same as the left gear.
	 * 
	 * @return gear the gear of the left motor
	 */
	public int getGears() {
		return left.getGear();
	}

	/**
	 * The right motor will also be enabled if the left one is.
	 * 
	 * @return whether or not the left motor is enabled
	 */
	public boolean getEnabled() {
		return left.getEnabled();
	}

	/**
	 * Disables or enables both motors.
	 * 
	 * @param enabled
	 *            Whether or not to enable the motors
	 */
	public void setEnabled(Boolean enabled) {
		left.setEnabled(enabled);
		right.setEnabled(enabled);
	}

	/**
	 * Sets the speed of the motors. This could work for tank drive but it is
	 * recommended to use updateTank.
	 * 
	 * @param leftValue
	 *            the value to set to the left motorGroup
	 * @param rightValue
	 *            the value to set to the right motorGroup
	 */
	public void setValues(float leftValue, float rightValue) {
		left.setValue(leftValue);
		right.setValue(rightValue);
	}

	/**
	 * This returns the speed of the two motors.
	 * 
	 * @return it is an array with the first value being the left motorGroup
	 *         value and the second value being the right motorGroup value
	 */
	public float[] getSetValue() {
		float[] speeds = new float[] { left.getSetValue(), right.getSetValue() };

		return speeds;
	}

	/**
	 * This is used for tank drive. Tank drive is useful for testing but not
	 * much else.
	 * 
	 * @param leftSpeed
	 *            the speed for the right motor
	 * @param rightSpeed
	 *            the speed for the right motor
	 * @param scaleFactor
	 *            Scales the output by this number, use 1 for default
	 */
	public void updateTank(double leftSpeed, double rightSpeed, double scaleFactor) {

		leftSpeed *= GeneralMethods.boundToUnitVector(scaleFactor);
		rightSpeed *= GeneralMethods.boundToUnitVector(scaleFactor);

		left.setValue((float) leftSpeed);
		right.setValue((float) rightSpeed);
	}

	/**
	 * This is used for the robot to be controlled for arcade drive. This can
	 * also be used for west coast because they are programmatically the same
	 * but the inputs are different.
	 * 
	 * @param forwardInput
	 *            forward speed
	 * @param horiszontalInput
	 *            horizontal speed
	 * @param scaleFactor
	 *            Scales the output by this number, use 1 for default
	 */
	public void updateArcade(double forwardInput, double horizontalInput, double scaleFactor) {

		float[] motorSpeeds = calculateArcade(forwardInput, horizontalInput, scaleFactor);

		float rightOut = Array.getFloat(motorSpeeds, 0);
		float leftOut = Array.getFloat(motorSpeeds, 1);

		left.setValue((float) leftOut);
		right.setValue((float) rightOut);
		// TODO add scaling
	}

	/**
	 * Calculates the math behind updateArcade. The advantage of doing the math
	 * outside of the original method is to be able to use JUnit test in order
	 * to check the math
	 * 
	 * @param forwardInput
	 *            the power that you want it to go forward
	 * @param horizontalInput
	 *            the power you want it to turn
	 * @param scaleFactor
	 *            the power the inputs are set to
	 * @return the speed for the wheels to go
	 */
	public static float[] calculateArcade(double forwardInput, double horizontalInput, double scaleFactor) {
		// This is the stuff I added
		/*
		 * if (forwardInput > -0.05 & forwardInput < 0.05) { horizontalInput =
		 * horizontalInput / 5; }
		 */
		// It is up to here
		// Set floats

		float leftOut = (float) forwardInput;
		float rightOut = (float) forwardInput;

		// Change the outputs
		leftOut += horizontalInput;
		rightOut -= horizontalInput;

		// Set the absolute values
		float leftAbsoluteValue = (float) Math.abs(leftOut);
		float rightAbsoluteValue = (float) Math.abs(rightOut);
		// Change the things
//		if (rightAbsoluteValue > 1 || leftAbsoluteValue > 1) {
//			if (rightAbsoluteValue > leftAbsoluteValue) {
//				leftOut /= rightAbsoluteValue;
//				rightOut /= rightAbsoluteValue;
//			} else {
//				leftOut /= leftAbsoluteValue;
//				rightOut /= leftAbsoluteValue;
//			}
//		}

		// Multipley the things by the scaleFactor
//		leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
//		rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);

		// return the things
		float[] motorSpeeds = new float[2];
		Array.setFloat(motorSpeeds, 0, rightOut);
		Array.setFloat(motorSpeeds, 1, leftOut);
		return motorSpeeds;
	}

	/**
	 * sets the speed of the wheels to what calculateBrakingDriveReturns returns
	 * This makes the robot move slower based on the maxValue input.
	 * 
	 * @param forwardInput
	 *            the power that you want it to go forward
	 * @param horizontalInput
	 *            the power you want it to turn
	 * @param scaleFactor
	 *            the power the inputs are set to
	 * @param maxValue
	 *            the maxSpeed the wheels to go
	 */
	public void updateBrakingDrive(double forwardInput, double horizontalInput, double scaleFactor, double maxValue) {
		// Get the things from the thing
		float[] motorSpeeds = calculateBrakingDrive(forwardInput, horizontalInput, scaleFactor, maxValue);
		// Get the things from the array
		float rightOut = Array.getFloat(motorSpeeds, 0);
		float leftOut = Array.getFloat(motorSpeeds, 1);

		// Set the values
		left.setValue((float) leftOut);
		right.setValue((float) rightOut);
		// TODO add scaling
	}

	/**
	 * Calculates the math behind braking drive
	 * 
	 * @param forwardInput
	 *            the power that you want it to go forward
	 * @param horizontalInput
	 *            the power you want it to turn
	 * @param scaleFactor
	 *            the power the inputs are set to
	 * @param maxValue
	 *            the maxSpeed the wheels to go
	 * @return speed the speed of the wheels
	 */
	public static float[] calculateBrakingDrive(double forwardInput, double horizontalInput, double scaleFactor,
			double maxValue) {

		// Set the things
		float leftOut = (float) forwardInput;
		float rightOut = (float) forwardInput;

		// Change the things by the input
		leftOut += horizontalInput;
		rightOut -= horizontalInput;

		// Set the absolute values
		float leftAbsoluteValue = (float) Math.abs(leftOut);
		float rightAbsoluteValue = (float) Math.abs(rightOut);

		// Set the things
		if (rightAbsoluteValue > 1 || leftAbsoluteValue > 1) {
			if (rightAbsoluteValue > leftAbsoluteValue) {
				leftOut /= rightAbsoluteValue;
				rightOut /= rightAbsoluteValue;
			} else {
				leftOut /= leftAbsoluteValue;
				rightOut /= leftAbsoluteValue;
			}
		}

		leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
		rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);
		maxValue = maxValue - maxValue * 0.15;
		maxValue = 1 - maxValue;
		leftOut *= maxValue;
		rightOut *= maxValue;

		// Return the motorspeeds
		float[] motorSpeeds = new float[2];
		Array.setFloat(motorSpeeds, 0, rightOut);
		Array.setFloat(motorSpeeds, 1, leftOut);
		return motorSpeeds;
	}

	/**
	 * This is arcade drive but the inputs are divided by breakinput. And if
	 * breakinput is the only input it goes backwards. This is meant for when
	 * the forward input can't be negative, like for triggers.
	 * 
	 * @param forwardInput
	 *            The forward power
	 * @param horizontalInput
	 *            The horizontal power
	 * @param scaleFactor
	 *            the scalefactor
	 * @param breakInput
	 *            the reverse power or amount the other inputs will be divided
	 *            by
	 */
	public void updateReverseBrakingDrive(double forwardInput, double horizontalInput, double scaleFactor,
			double breakInput) {
		float leftOut;
		float rightOut;
		if (forwardInput > 0.05) {
			leftOut = (float) forwardInput;
			rightOut = (float) forwardInput;
			SmartDashboard.putNumber("Test Number Thing", forwardInput);
			leftOut += horizontalInput;
			rightOut -= horizontalInput;
			SmartDashboard.putBoolean("small forward input", false);
			leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
			rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);
			// hi
			breakInput = breakInput - breakInput * 0.10;
			breakInput = 1 - breakInput;
			leftOut *= breakInput;
			rightOut *= breakInput;
		} else {
			forwardInput = -breakInput;
			leftOut = (float) forwardInput;
			rightOut = (float) forwardInput;
			SmartDashboard.putNumber("Test Number Thing", forwardInput);
			leftOut += horizontalInput;
			rightOut -= horizontalInput;
			SmartDashboard.putBoolean("small forward input", true);

			leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
			rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);
		}
		left.setValue((float) leftOut);
		right.setValue((float) rightOut);
	}

	/**
	 * This spins the robot to a new angle based on time. This is not that
	 * accurate but works.
	 * 
	 * @param newAngle
	 *            This is the angle that you want the robot to move to
	 * @param relativePositioning
	 *            this says whether or not you want the robot to rotate to
	 *            newAngle or to rotate the newAngle input degrees
	 */
	public void spinToAngle(double newAngle, boolean relativePositioning) {
		turning = true;
		if (relativePositioning) {
			previousAngle = 0;
		}
		boolean negativeTime = false;
		SmartDashboard.putBoolean("Turning", turning);
		double turningTime;
		SmartDashboard.putNumber("New Angle", newAngle);
		SmartDashboard.putNumber("Past Angle", previousAngle);
		left.setValue((float) -0.25);
		right.setValue((float) 0.25);
		turningTime = (previousAngle - newAngle) / 90;
		if (turningTime < 0) {
			negativeTime = true;
			turningTime = -turningTime;
			left.setValue((float) 0.25);
			right.setValue((float) -0.25);
		}
		previousAngle = newAngle;
		SmartDashboard.putNumber("Time", turningTime);
		Timer.delay(turningTime);
		if (negativeTime) {
			left.setValue((float) -0.25);
			right.setValue((float) 0.25);
		} else {
			left.setValue((float) 0.25);
			right.setValue((float) -0.25);
		}
		Timer.delay(0.1);
		left.setValue(0);
		right.setValue(0);
		turning = false;
	}

	/**
	 * Sets both the motor speeds to zero
	 */
	public void disableMotors() {
		left.setValue(0);
		right.setValue(0);
	}

	/**
	 * Makes the robot turn at a quarter speed
	 * 
	 * @param rightTurn
	 *            whether or not turn right
	 */
	public void turn(boolean rightTurn) {
		if (rightTurn) {
			left.setValue((float) 0.25);
			right.setValue((float) -0.25);
		} else {
			left.setValue((float) -0.25);
			right.setValue((float) 0.25);
		}
	}

	/**
	 * Makes the robot move straight
	 * 
	 * @param speed
	 *            The speed at which the robot moves
	 * @param leftRate
	 *            how fast the encoders say the left wheels of the robot is
	 *            moving
	 * @param rightRate
	 *            how fast the encoders say the right wheels of the robot is
	 *            moving
	 */
	public void moveStraight(float speed, float leftRate, float rightRate) {
		float leftSpeed = speed;
		float rightSpeed = speed;
		if (rightRate > leftRate) {
			leftSpeedBonus += 0.01;
		} else if (leftRate > rightRate) {
			rightSpeedBonus += 0.01;
		}
		leftSpeed += leftSpeedBonus;
		rightSpeed += rightSpeedBonus;
		float[] speeds = GeneralMethods.lowerToNumber(leftSpeed, rightSpeed);
		leftSpeed = Array.getFloat(speeds, 0);
		rightSpeed = Array.getFloat(speeds, 1);
		left.setValue(speed);
		right.setValue(speed);
	}
}
