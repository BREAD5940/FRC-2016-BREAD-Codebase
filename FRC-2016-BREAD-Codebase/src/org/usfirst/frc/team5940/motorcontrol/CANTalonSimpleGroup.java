package org.usfirst.frc.team5940.motorcontrol;

import edu.wpi.first.wpilibj.CANTalon;

public class CANTalonSimpleGroup implements MotorGroup {
	
    CANTalon[] talons;
    float motorsOut = 0;
    boolean enabled = true;
    boolean inverted = false;
    
	public CANTalonSimpleGroup(CANTalon[] talons, boolean inverted) {
		//cT1 = new CANTalon(m1);
		//cT2 = new CANTalon(m2);
		//cT3 = new CANTalon(m3);
		//cT4 = new CANTalon(m4);
		//TODO Set method of talons
		this.talons = talons;
		for (int i = 0; i < talons.length; i++){
			this.talons[i].setControlMode(0);
			
		}
		
		this.inverted = inverted;
		
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "CANTalonDrive";
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
		for (int i = 0; i < talons.length; i++){
			talons[i].set(this.motorsOut);
			
		}
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
		//cT1.setSafetyEnabled(!enabled);
		//cT2.setSafetyEnabled(!enabled);
		//cT3.setSafetyEnabled(!enabled);
		//cT4.setSafetyEnabled(!enabled);
		//TODO Set talons enabled
		for (int i = 0; i < talons.length; i++){
			talons[i].setSafetyEnabled(!enabled);
			
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
		this.setValue(this.motorsOut);
	}
	
	public boolean getInverted(){
		
		return inverted;
		
	}

}
