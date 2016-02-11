package org.usfirst.frc.team5940.motorcontrol;

import java.lang.reflect.Array;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import other.GeneralMethods;

public class DualMGDrivetrain {

	public final MotorGroup left;
	public final MotorGroup right;
	public float leftSpeedBonus = 0;
	public float rightSpeedBonus = 0;
	public float leftSpeedPenalty = 0;
	public float rightSpeedPenalty = 0;
	public double previousAngle = 0;
	public boolean turning = false;

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
	 * and we might need to change that
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
			left.setGear(0);
			right.setGear(0);
		} else if (gear >= totalGears) {
			left.setGear(totalGears - 1);
			right.setGear(totalGears - 1);
		} else {
			left.setGear(gear);
			right.setGear(gear);
		}
	}

	/**
	 * This just returns the left MotorGroup gear so just hope that it is the
	 * same as the right MotorGroup gear thing.
	 * 
	 * @return gear the gear of the left motor
	 */
	public int getGears() {
		return left.getGear();
	}

	/**
	 * This just returns the left MotorGroup enabled-ness so just hope that it
	 * is the same as the right MotorGroup enabled-ness thing.
	 * 
	 * @return enabledness the enabledness of the left motor
	 */
	public boolean getEnabled() {
		return left.getEnabled();
	}

	/**
	 * Disables both motors or enables them your choice dawg
	 * 
	 * @param enabled
	 *            the enabledness to set to the motors
	 */
	public void setEnabled(Boolean enabled) {
		left.setEnabled(enabled);
		right.setEnabled(enabled);
	}

	/**
	 * Sets the value of the two MotorGroups
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
	 * This shows the value of the two motors. It might not work fifty fifty
	 * chance.
	 * 
	 * @return it is an array with the first value being the left motorGroup
	 *         value and the second value being the right motorGroup value
	 */
	public Array getSetValue() {
		Array speeds = null;
		Array.setFloat(speeds, 0, left.getSetValue());
		Array.setFloat(speeds, 1, right.getSetValue());

		return speeds;
	}

	/**
	 * This updates the tank
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
	 * This arcade steers or west coast
	 * 
	 * @param forwardInput
	 *            amount forward
	 * @param horiszontalInput
	 *            amount horizontal
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
	 * Calculates the arcade drive but also could be used for west coast drive
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
		if (forwardInput > -0.05 || forwardInput < 0.05) {
			horizontalInput = horizontalInput / 5;
		}
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
		if (rightAbsoluteValue > 1 || leftAbsoluteValue > 1) {
			if (rightAbsoluteValue > leftAbsoluteValue) {
				leftOut /= rightAbsoluteValue;
				rightOut /= rightAbsoluteValue;
			} else {
				leftOut /= leftAbsoluteValue;
				rightOut /= leftAbsoluteValue;
			}
		}

		// Multipley the things by the scaleFactor
		leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
		rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);

		// return the things
		float[] motorSpeeds = new float[2];
		Array.setFloat(motorSpeeds, 0, rightOut);
		Array.setFloat(motorSpeeds, 1, leftOut);
		return motorSpeeds;
	}

	/**
	 * sets the speed of the wheels to what calculateStupidDrive returns
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
	public void updateStupidDrive(double forwardInput, double horizontalInput, double scaleFactor, double maxValue) {
		// Get the things from the thing
		float[] motorSpeeds = calculateStupidDrive(forwardInput, horizontalInput, scaleFactor, maxValue);
		// Get the things from the array
		float rightOut = Array.getFloat(motorSpeeds, 0);
		float leftOut = Array.getFloat(motorSpeeds, 1);

		// Set the values
		left.setValue((float) leftOut);
		right.setValue((float) rightOut);
		// TODO add scaling
	}

	/**
	 * Calculates the math behind stupid drive
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
	public static float[] calculateStupidDrive(double forwardInput, double horizontalInput, double scaleFactor,
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

		// Scale
		leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
		rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);
		// Do max value stuff
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
	public void updateMemeDrive(double forwardInput, double horizontalInput, double scaleFactor, double breakInput) {
		float leftOut;
		float rightOut;
		// Set things
		if (forwardInput > 0.05) {
			//Set the outs
			leftOut = (float) forwardInput;
			rightOut = (float) forwardInput;
			//Update dashboard
			SmartDashboard.putNumber("Test Number Thing", forwardInput);
			//Change inputs
			leftOut += horizontalInput;
			rightOut -= horizontalInput;
			//Update dashboard again
			SmartDashboard.putBoolean("small forward input", false);
			//Scale and bind the scale factor
			leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
			rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);
			//Scale break input
			breakInput = breakInput - breakInput * 0.10;
			breakInput = 1 - breakInput;
			//Update outs
			leftOut *= breakInput;
			rightOut *= breakInput;
		} else {
			//Set forwared input
			forwardInput = -breakInput;
			//Set outs
			leftOut = (float) forwardInput;
			rightOut = (float) forwardInput;
			//Update dashboard
			SmartDashboard.putNumber("Test Number Thing", forwardInput);
			//Update outs
			leftOut += horizontalInput;
			rightOut -= horizontalInput;
			//Update dashboard
			SmartDashboard.putBoolean("small forward input", true);
			//Update outs
			leftOut *= GeneralMethods.boundToUnitVector(scaleFactor);
			rightOut *= GeneralMethods.boundToUnitVector(scaleFactor);
		}
		// Set the values
		left.setValue((float) leftOut);
		right.setValue((float) rightOut);
	}

	/**
	 * Four seconds to spin at 0.25 power
	 * 
	 * @param newAngle
	 */
	public void spinToAngle(double newAngle, boolean relativePositioning) {
		//Set turing
		turning = true;
		//Check relative positioning if should use previous angle
		if (relativePositioning) {
			previousAngle = 0;
		}
		//Set negative time
		boolean negativeTime = false;
		//Update dashboard
		SmartDashboard.putBoolean("Turning", turning);
		//Set turning time
		double turningTime;
		//Update dashboard
		SmartDashboard.putNumber("New Angle", newAngle);
		SmartDashboard.putNumber("Past Angle", previousAngle);
		//Set values
		left.setValue((float) -0.25);
		right.setValue((float) 0.25);
		//Set turning time
		turningTime = (previousAngle - newAngle) / 90;
		//Check turning time
		if (turningTime < 0) {
			//Set negative time turning time and the values
			negativeTime = true;
			turningTime = -turningTime;
			left.setValue((float) 0.25);
			right.setValue((float) -0.25);
		}
		// if (turningTime > 0.5) {
		// turningTime = turningTime - 0.45;
		// } else if (turningTime < -0.5) {
		// turningTime = turningTime + 0.45;
		// }
		//Set previous angle
		previousAngle = newAngle;
		//Update dashboard
		SmartDashboard.putNumber("Time", turningTime);
		//Delay
		Timer.delay(turningTime);
		//CHeck negative time
		if (negativeTime) {
			//Set values
			left.setValue((float) -0.25);
			right.setValue((float) 0.25);
		} else {
			left.setValue((float) 0.25);
			right.setValue((float) -0.25);
		}
		//Dealy
		Timer.delay(0.1);
		//Set value
		left.setValue(0);
		right.setValue(0);
		//Set turning
		turning = false;
	}

	public void disableMotors() {
		//Set values to zero
		left.setValue(0);
		right.setValue(0);
	}

	public void turn(boolean rightTurn) {
		//Turn right or left
		if (rightTurn) {
			left.setValue((float) 0.25);
			right.setValue((float) -0.25);
		} else {
			left.setValue((float) -0.25);
			right.setValue((float) 0.25);
		}
	}

	public void moveStraight(float speed, float leftRate, float rightRate) {
		//Set speeds
		float leftSpeed = speed;
		float rightSpeed = speed;
		//Check rates and adjust
		if (rightRate > leftRate) {
			leftSpeedBonus += 0.01;
		}
		else if (leftRate > rightRate) {
			rightSpeedBonus += 0.01;
		}
		//Change speeds
		leftSpeed += leftSpeedBonus;
		rightSpeed += rightSpeedBonus;
		float[] speeds = GeneralMethods.lowerToNumber(leftSpeed, rightSpeed, 1);
		//Set the things
		leftSpeed = Array.getFloat(speeds, 0);
		rightSpeed = Array.getFloat(speeds, 1);
		left.setValue(speed);
		right.setValue(speed);
	}
}
