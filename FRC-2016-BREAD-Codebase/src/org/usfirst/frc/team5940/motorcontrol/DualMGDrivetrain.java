package org.usfirst.frc.team5940.motorcontrol;

import java.lang.reflect.Array;

import other.GeneralMethods;

public class DualMGDrivetrain {

	public final MotorGroup left;
	public final MotorGroup right;

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
	 * @return
	 */
	public int getGears() {
		return left.getGear();
	}

	/**
	 * This just returns the left MotorGroup enabled-ness so just hope that it
	 * is the same as the right MotorGroup enabled-ness thing.
	 * 
	 * @return
	 */
	public boolean getEnabled() {
		return left.getEnabled();
	}

	/**
	 * Disables both motors or enables them your choice dawg
	 * 
	 * @param enabled
	 *            tells whether or not to enable it
	 */
	public void setEnabled(Boolean enabled) {
		left.setEnabled(enabled);
		right.setEnabled(enabled);
	}

	/**
	 * Sets the value of the two MotorGroups
	 * 
	 * @param leftValue
	 *            the value of the left motorGroup
	 * @param rightValue
	 *            the value of the right motorGroup
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
	 * @param rightSpeed
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
	 * THis arcade steers
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

	public static float[] calculateArcade(double forwardInput, double horizontalInput, double scaleFactor) {
		// This is the stuff I added
		if (forwardInput > -0.05 & forwardInput < 0.05) {
			horizontalInput = horizontalInput / 5;
		}
		// It is up to here

		float leftOut = (float) forwardInput;
		float rightOut = (float) forwardInput;

		leftOut += horizontalInput;
		rightOut -= horizontalInput;

		float leftAbsoluteValue = (float) Math.abs(leftOut);
		float rightAbsoluteValue = (float) Math.abs(rightOut);
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

		float[] motorSpeeds = new float[2];
		Array.setFloat(motorSpeeds, 0, rightOut);
		Array.setFloat(motorSpeeds, 1, leftOut);
		return motorSpeeds;
	}

	public void updateStupidDrive(double forwardInput, double horizontalInput, double scaleFactor, double maxValue) {

		// This is the stuff I added
		if (forwardInput > -0.05 & forwardInput < 0.05) {
			horizontalInput = horizontalInput / 5;
		}
		// It is up to here

		float leftOut = (float) forwardInput;
		float rightOut = (float) forwardInput;

		leftOut += horizontalInput;
		rightOut -= horizontalInput;

		float leftAbsoluteValue = (float) Math.abs(leftOut);
		float rightAbsoluteValue = (float) Math.abs(rightOut);
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
		
		if (maxValue > 0.05) {
			leftOut /= maxValue;
			rightOut /= maxValue;
		}
		left.setValue((float) leftOut);
		right.setValue((float) rightOut);
		// TODO add scaling
	}
}
