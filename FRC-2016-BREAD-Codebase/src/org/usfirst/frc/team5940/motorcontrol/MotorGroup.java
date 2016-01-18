package org.usfirst.frc.team5940.motorcontrol;

public interface MotorGroup {
	
	/**
	 * This should return the name of the MotorGroup. This should only be used for human identification purposes, not in code. If no name has been set (for instance through a constructor) it should return the name of its class file.
	 * @return A String representing the name of the MotorGroup.
	 */
	public String getName();
	
	/**
	 * Sets the value of output for the motors. -1 should represent full speed backwards, 0 should represent completely stopped, 1 should represent full speed forward. Any non linearity or other control systems (voltage based, PID, PWM, etc...) are up to the implementation, as long as they follow those guidelines.
	 * @param value A float between -1 an 1.
	 */
	public void setValue(float value);
	
	/**
	 * This should return the value corresponding to the last setValue. If the MotorGroup is disabled, it should still return the value that will resume being used when re-enabled.
	 * @return The float the MotorGroup is set to.
	 */
	public float getSetValue();
	
	/**
	 * Returns the speed in rpm of the motor shafts, undefined if no encoder.
	 * @return A float representing the speed of the motor group in rpm
	 */
	public float getSpeed();
	
	/**
	 * Returns the number of gears this MotorGroup has. This should be a number between 1 and infinity as you always have one gear.
	 * @return An int representing the number of gearing positions this motor group can be in.
	 */
	public int getGearsAmount();
	
	/**
	 * 
	 * @return
	 */
	public int getGear();
	
	public void setGear(int gear);
	
	public void setEnabled(boolean enabled);
	
	public boolean getEnabled();
}
