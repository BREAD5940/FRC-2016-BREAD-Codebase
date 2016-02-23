package org.usfirst.frc.team5940.motorcontrol.groups;

import edu.wpi.first.wpilibj.Victor;

public class VictorSimpleGroup implements MotorGroup{
	
	
    Victor[] motors;
    float motorsOut = 0;
    boolean enabled = true;
    boolean inverted = false;
    
	public VictorSimpleGroup(Victor[] motor, boolean inverted) {
		//cT1 = new Victor(m1);
		//cT2 = new Victor(m2);
		//cT3 = new Victor(m3);
		//cT4 = new Victor(m4);
		//TODO Set method of victors
		this.motors = motor;		
		this.inverted = inverted;
		
		
	}
	
	/**
	 * This returns VictorDrive
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "VictorDrive";
	}
	
	/**
	 * This inputs a value into the motors
	 */
	@Override
	public void setValue(float motorsOut) {
		// TODO Auto-generated method stub
		if (inverted){this.motorsOut = motorsOut * -1;}
		else {this.motorsOut = motorsOut;}
		//cT1.set(motorsOut);
    	//cT2.set(motorsOut);
    	//cT3.set(motorsOut);
    	//cT4.set(motorsOut);
		//TODO set victor values
		for (int i = 0; i < motors.length; i++){
			motors[i].set(this.motorsOut);
			
		}
	}

	/**
	 * This returns the value inputed into the motor
	 */
	@Override
	public float getSetValue() {
		// TODO Auto-generated method stub
		return motorsOut;
	}

	/**
	 * This will return the current speed of the motor group but not yet
	 */
	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * This will return the total amount of gears on the robot 
	 */
	@Override
	public int getGearsAmount() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * This returns the gear the robot is in
	 */
	@Override
	public int getGear() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * This sets the robot to a gear 
	 */
	@Override
	public void setGear(int gear) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		this.enabled = enabled;
		//cT1.setSafetyEnabled(!enabled);
		//cT2.setSafetyEnabled(!enabled);
		//cT3.setSafetyEnabled(!enabled);
		//cT4.setSafetyEnabled(!enabled);
		//TODO Set victors enabled
		for (int i = 0; i < motors.length; i++){
			motors[i].setSafetyEnabled(!enabled);
			
		}
	}

	@Override
	public boolean getEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean canMeasureSpeed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * This inverts the motors
	 */
	public void setInverted(boolean inverted){
		this.inverted = inverted;
		this.setValue(this.motorsOut);
	}
	
	/**
	 * This returns if the motors are inverted
	 */
	public boolean getInverted(){
		return inverted;
	}

}
