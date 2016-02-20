package org.usfirst.frc.team5940.motorcontrol.groups;

import edu.wpi.first.wpilibj.Victor;

public class VictorGroup implements MotorGroup {
	
    Victor[] victors;
    float motorsOut = 0;
    boolean enabled = true;
    boolean inverted = false;
    	
	public VictorGroup(Victor[] victors) {
		//Sets the victors
		this.victors = victors;
		
	}
	
	@Override
	/**Gets the name
	 * @return The name of the group
	*/
	public String getName() {
		//The name of the goup
		return "VictorGroup";
	}

	@Override
	/**Gets the set value
	 * @return The previously set value
	*/
	public float getSetValue() {
		//The set value
		return motorsOut;
	}

	@Override
	//The next four don't apply
	public float getSpeed() {
		return 0;
	}

	@Override
	public int getGearsAmount() {
		return 1;
	}

	@Override
	public int getGear() {
		return 1;
	}

	@Override
	public void setGear(int gear) {}

	@Override
	/**Sets the enabledness
	 * @param enabled The enabledness
	*/
	public void setEnabled(boolean enabled) {
		//Sets enabledness
		this.enabled = enabled;
		//Sets enabledness of the victors
		for (int i = 0; i < victors.length; i++){
			victors[i].setSafetyEnabled(!enabled);
			
		}
	}

	@Override
	/**Gets the enabledness
	 * @return The enabledness of the motors
	*/
	public boolean getEnabled() {
		//The enabledness
		return enabled;
	}

	@Override
	//Just can't do this
	public boolean canMeasureSpeed() {
		//Can't measure speed
		return false;
	}
	/**Sets the invertedness
	 * @param inverted The invertedness
	*/
	public void setInverted(boolean inverted){
		//Set invertedness
		this.inverted = inverted;
	}
	/**Gets the invertedness
	 * @return The invertedness
	*/
	public boolean getInverted(){
		//Gets invertedness
		return inverted;
		
	}

	@Override
	/**Sets the motorvalues
	 * @return motorsOut The value
	*/
	public void setValue(float motorsOut) {
		//Invert if inverted
		if (inverted){this.motorsOut = motorsOut * -1;}
		else {this.motorsOut = motorsOut;}
		//Set victor values
		for (int i = 0; i < victors.length; i++){
			victors[i].set(this.motorsOut);
			
		}
	}

}
