package org.usfirst.frc.team5940.motorcontrol;

public interface MotorGroup {
	
	public void setValue();
	
	public float getSetValue();
	
	public float getSpeed();
	
	public int getGearsAmount();
	
	public void getGear();
	
	public void setGear();
	
	public int getCurrentGear();
	
	public void setEnabled();
	
	public boolean getEnabled();
}
