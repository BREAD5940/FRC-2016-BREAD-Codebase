package org.usfirst.frc.team5940.motorcontrol;

import edu.wpi.first.wpilibj.Victor;

public class VictorGroup implements MotorGroup {
	
    Victor[] victors;
    float motorsOut = 0;
    boolean enabled = true;
    boolean inverted = false;
    
	public VictorGroup(Victor[] victors) {
		this.victors = victors;
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "VictorGroup";
	}

	@Override
	public float getSetValue() {
		// TODO Auto-generated method stub
		return motorsOut;
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGearsAmount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getGear() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void setGear(int gear) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		this.enabled = enabled;
		for (int i = 0; i < victors.length; i++){
			victors[i].setSafetyEnabled(!enabled);
			
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
	public void setInverted(boolean inverted){
		this.inverted = inverted;
	}
	public boolean getInverted(){
		
		return inverted;
		
	}

	@Override
	public void setValue(float motorsOut) {
		// TODO Auto-generated method stub
		if (inverted){this.motorsOut = motorsOut * -1;}
		else {this.motorsOut = motorsOut;}
		//cT1.set(motorsOut);
    	//cT2.set(motorsOut);
    	//cT3.set(motorsOut);
    	//cT4.set(motorsOut);
		//TODO set talon values
		for (int i = 0; i < victors.length; i++){
			victors[i].set(this.motorsOut);
			
		}
	}

}
