package org.usfirst.frc.team5940.motorcontrol;

public interface MotorGroup {
	
	public void setValue(float value);
	
	public float getSetValue();
	
	public float getSpeed();
	
	public int getGearsAmount();
	
	public void getGear();
	
	public void setGear(int gear);
	
	public int getCurrentGear();
	
	public void setEnabled(boolean enabled);
	
	public boolean getEnabled();
}
