package org.usfirst.frc.team5940.motorcontrol;

import edu.wpi.first.wpilibj.CANTalon;

public class CANTalonSimpleGroup implements MotorGroup {
	
    CANTalon[] talons;
    float motorsOut = 0;
    boolean enabled = true;
    boolean inverted = false;
    
	public CANTalonSimpleGroup(CANTalon[] canTalons, boolean inverted) {
		//Set talons
		this.talons = canTalons;
		//Set control mode
		for (int i = 0; i < canTalons.length; i++){
			this.talons[i].setControlMode(0);
			
		}
		//Set inverted
		this.inverted = inverted;
		
		
	}
	
	@Override
	/**Gets the name
	 * @return name THe name of the group\
	*/
	public String getName() {
		//Return name
		return "CANTalonDrive";
	}

	@Override
	/**Sets the value of the motors
	 * @param motorsOut The value for the motors.
	*/
	public void setValue(float motorsOut) {
		//Invert if inverted
		if (inverted){this.motorsOut = motorsOut * -1;}
		else {this.motorsOut = motorsOut;}
		//Set talon values
		for (int i = 0; i < talons.length; i++){
			talons[i].set(this.motorsOut);
			
		}
	}

	@Override
	/**Returns the values that have been set
	 * @return motorsOut the value that has been set
	*/
	public float getSetValue() {
		//Return the set value
		return motorsOut;
	}
	//Next four are not used by this so just return 1 or 0 or do nothing
	@Override
	public float getSpeed() {
		//Only one gear
		return 0;
	}

	@Override
	public int getGearsAmount() {
		//Only one gear
		return 2;
	}

	@Override
	public int getGear() {
		//Only one gear
		return 1;
	}

	@Override
	public void setGear(int gear) {
		//Only one gear to use can't set
		
	}

	@Override
	/**Sets the enabledness
	 * @param enabled The enabledness
	*/
	public void setEnabled(boolean enabled) {
		//Sets the enabled prop
		this.enabled = enabled;
		//Set talons enabledness
		for (int i = 0; i < talons.length; i++){
			talons[i].setSafetyEnabled(!enabled);
			
		}
	}

	@Override
	/**Gives the enabledness
	 * @return enabled The enabledness
	*/
	public boolean getEnabled() {
		// Returns the enabled prop
		return enabled;
	}

	@Override
	//This motor group has one speed so you can't measure it
	public boolean canMeasureSpeed() {
		//Can't measure speed
		return false;
	}
	/**Sets the invertedness
	 * @param inverted The invertedness
	*/
	public void setInverted(boolean inverted){
		//Set the invertedness
		this.inverted = inverted;
		//Sets the values
		this.setValue(this.motorsOut);
	}
	/**Gets the invertedness
	 * @return inverted The invertedness
	*/
	public boolean getInverted(){
		//Returns the invertedness
		return inverted;
		
	}

}
