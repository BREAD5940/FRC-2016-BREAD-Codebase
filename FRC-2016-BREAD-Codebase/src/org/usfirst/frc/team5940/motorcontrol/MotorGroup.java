package org.usfirst.frc.team5940.motorcontrol;

public interface MotorGroup {
	
	/**
	 * Sets the value of output for the motors.
	 * @param value A float between -1 an 1
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
