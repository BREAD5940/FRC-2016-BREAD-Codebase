package org.usfirst.frc.team5940.motorcontrol.groups;

import com.ctre.*;

/**
 * 
 * @author Team 5940
 *
 */
public class CANTalonSimpleGroup implements MotorGroup {

	CANTalon[] talons;
	float motorsOut = 0;
	boolean enabled = true;
	boolean inverted = false;

	public CANTalonSimpleGroup(CANTalon[] canTalons, boolean inverted) {
		// Set talons
		this.talons = canTalons;
		// Set control mode
		for (int i = 0; i < canTalons.length; i++) {
			this.talons[i].setControlMode(0);

		}
		// Set inverted
		this.inverted = inverted;

	}

	@Override
	/**
	 * Says the name of the motors
	 * 
	 * @return name The name of the group
	 */
	public String getName() {
		// Return name
		return "CANTalonDrive";
	}

	@Override
	/**
	 * Sets the speed of the motors
	 * 
	 * @param motorsOut
	 *            The speed for the motors.
	 */
	public void setValue(float motorsOut) {
		// Invert if inverted
		if (inverted) {
			this.motorsOut = motorsOut * -1;
		} else {
			this.motorsOut = motorsOut;
		}
		// Set talon values
		for (int i = 0; i < talons.length; i++) {
			talons[i].set(this.motorsOut);

		}
	}

	@Override
	/**
	 * Returns the speed that the motors have been set
	 * 
	 * @return motorsOut the value that has been set
	 */
	public float getSetValue() {
		// Return the set value
		return motorsOut;
	}

	// Next four are not used by this so just return 1 or 0 or do nothing
	@Override
	public float getSpeed() {
		// Only one gear
		return 0;
	}

	@Override
	public int getGearsAmount() {
		// Only one gear
		return 2;
	}

	@Override
	public int getGear() {
		// Only one gear
		return 1;
	}

	@Override
	public void setGear(int gear) {
		// Only one gear to use can't set

	}

	@Override
	/**
	 * Sets the motors to be enabled or disabled
	 * 
	 * @param enabled
	 *            Whether or not to enable or disable the motors
	 */
	public void setEnabled(boolean enabled) {
		// Sets the enabled prop
		this.enabled = enabled;
		// Set talons enabledness
		for (int i = 0; i < talons.length; i++) {
			talons[i].setSafetyEnabled(!enabled);

		}
	}

	@Override
	/**
	 * Returns whether or not the motors are enabled
	 * 
	 * @return If the motors are enabled
	 */
	public boolean getEnabled() {
		// Returns the enabled prop
		return enabled;
	}

	@Override
	// This motor group has one speed so you can't measure it
	public boolean canMeasureSpeed() {
		// Can't measure speed
		return false;
	}

	/**
	 * Sets whether or not to invert the motors
	 * 
	 * @param inverted
	 *            Whether or not to invert the motors
	 */
	public void setInverted(boolean inverted) {
		// Set the invertedness
		this.inverted = inverted;
		// Sets the values
		this.setValue(this.motorsOut);
	}

	/**
	 * Returns whether or not the motors are inverted
	 * 
	 * @return If the motors are inverted
	 */
	public boolean getInverted() {
		// Returns the invertedness
		return inverted;

	}

}
