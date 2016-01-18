package org.usfirst.frc.team5940.motorcontrol;

public interface MotorGroup {
	
	/**
	 * This should return the name of the MotorGroup. This should only be used for human identification purposes, not in code. If no name has been set (for instance through a constructor) it should return the name of its class file.
	 * @return A String representing the name of the MotorGroup.
	 */
	public String getName();
	
	/**
	 * Sets the value of output for the motors. -1 should represent full speed backwards, 0 should represent 
	 * @param value A float between -1 an 1.
	 */
	public void setValue(float value);
	
	/**
	 * 
	 * @return
	 */
	public float getSetValue();
	
	public float getSpeed();
	
	public int getGearsAmount();
	
	public int getGear();
	
	public void setGear(int gear);
	
	public void setEnabled(boolean enabled);
	
	public boolean getEnabled();
}
