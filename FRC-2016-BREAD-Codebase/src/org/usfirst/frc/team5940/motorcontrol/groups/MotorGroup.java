package org.usfirst.frc.team5940.motorcontrol.groups;

public interface MotorGroup {
	
	/**
	 * This should return the name of the MotorGroup. This should only be used for human identification purposes, not in code. If no name has been set (for instance through a constructor) it should return the name of its class file.
	 * @return A String representing the name of the MotorGroup.
	 */
	public String getName();
	
	/**
	 * Sets the value of output for the motors. -1 should represent full speed backwards, 0 should represent completely stopped, 1 should represent full speed forward. Any non linearity or other control systems (voltage based, PID, PWM, etc...) are up to the implementation, as long as they follow those guidelines.
	 * TODO: Rename this method!  Perhaps setOutput() ?
	 * @param value A float between -1 an 1.
	 */
	public void setValue(float value);
	
	/**
	 * This should return the value corresponding to the last setValue. If the MotorGroup is disabled, it should still return the value that will resume being used when re-enabled.
	 * @return The float the MotorGroup is set to.
	 */
	public float getSetValue();
	
	/**
	 * Returns the speed in rpm of the motor shafts, undefined if canMeasureSpeed is false or null.
	 * @return A float representing the speed of the motor group in rpm
	 */
	public float getSpeed();
	
	/**
	 * Returns if this motor group has speed measurement.
	 * @return boolean, see above.
	 */
	public boolean canMeasureSpeed();
	
	/**
	 * Returns the number of gears this MotorGroup has. This should be a number between 1 and infinity as you always have one gear.
	 * @return An int representing the number of gearing positions this motor group can be in.
	 */
	public int getGearsAmount();
	
	/**
	 * Gets the gear of the motor. This should update immediately with new gears from setGear, if shifting fails, etc.. it should update back to it's actual state.
	 * @return An int representing the current gear of the motor group.
	 */
	public int getGear();
	
	/**
	 * Sets the gear of the MotorGroup. This starts from 0 as the highest torque/ lowest speed, up to getGearsAmount() -1.
	 * @param gear Sets the gear of this MotorGroup.
	 */
	public void setGear(int gear);
	
	/**
	 * Sets whether or not this MotorGroup is enabled.
	 * @param enabled A boolean representing enabledness.
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Gets whether this MotorGroup is enabled.
	 * @return A boolean representing enabledness.
	 */
	public boolean getEnabled();
	
	/**
	 * This tells the motors whether or not to be inverted
	 * @param inverted whether or not 
	 */
	public void setInverted(boolean inverted);
	
	public boolean getInverted();
}
