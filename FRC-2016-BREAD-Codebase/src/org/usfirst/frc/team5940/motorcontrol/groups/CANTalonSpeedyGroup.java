package org.usfirst.frc.team5940.motorcontrol.groups;

import edu.wpi.first.wpilibj.CANTalon;

public class CANTalonSpeedyGroup implements MotorGroup {
	
    CANTalon[] talons;
    private float motorsOut = 0;
    private boolean enabled = true;
    private boolean inverted = false;
    private float scaleFactor;
    
	public CANTalonSpeedyGroup(CANTalon[] talons, boolean inverted, float scaleFactor) {
		//Set talons
		this.talons = talons;
		// Set control mode
		for (int i = 0; i < talons.length; i++) {
			this.talons[i].setControlMode(2);

		}
		// Set inverted
		this.inverted = inverted;
		this.scaleFactor = scaleFactor;
	}

	@Override
	/**
	 * Gets the name of the motors
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
		//Invert if inverted
		this.motorsOut = motorsOut;
		float value = motorsOut;
		if (inverted){value = -value;}
		value = value*scaleFactor;
		//Set talon values
		for (int i = 0; i < talons.length; i++){
			talons[i].set(this.motorsOut);
			
		}
	}

	@Override
	/**
	 * Returns the speed that the motors have been set
	 * 
	 * @return motorsOut the speed that has been set
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
	 * Enables or disables the motors
	 * 
	 * @param enabled
	 *            Whether or not to enable the motors
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
	 * @return true if the motors are enabled
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
	 * Sets the motors to inverted
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
	 * Returns if the motors are inverted
	 * 
	 * @return True if the motors are inverted
	 */
	public boolean getInverted() {
		// Returns the invertedness
		return inverted;

	}

	/**
	 * Returns the conversion factor between speed pulses per second
	 * 
	 * @return the scale factor
	 */
	public float getScaleFactor() {
		return scaleFactor;
	}

	/**
	 * Sets the conversion factor between pulses per second and speed
	 * 
	 * @param scaleFactor
	 *            the conversion factor
	 */
	public void setScaleFactor(float scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

}
