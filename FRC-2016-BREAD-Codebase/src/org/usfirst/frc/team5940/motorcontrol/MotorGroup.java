package org.usfirst.frc.team5940.motorcontrol;

public interface MotorGroup {
	
	/**
	 * Sets the value of output for the motors. This is a 
	 * @param value
	 */
	public void setValue(float value);
	
	public float getSetValue();
	
	public float getSpeed();
	
	public int getGearsAmount();
	
	public int getGear();
	
	public void setGear(int gear);
	
	public int getCurrentGear();
	
	public void setEnabled(boolean enabled);
	
	public boolean getEnabled();
}
