package org.usfirst.frc.team5940.other;

import edu.wpi.first.wpilibj.Joystick;

public class ConfigurableJoystick extends Joystick {

	double[][] deadzones;
	double[] exponents;
	
	public ConfigurableJoystick(int port, double[][] deadzones, double[] exponents) {
		super(port);
		this.deadzones = deadzones;
		this.exponents = exponents;
	}
	
	@Override
	public double getRawAxis(final int axis) {
		double out = super.getRawAxis(axis);
		if(axis < deadzones.length) out = addDeadzone(axis, out);
		if(axis < exponents.length) out = addPowInputFixed(axis, out);
		return out;
	}
	
	double addDeadzone(int axis, double value) {
		double lowDead = deadzones[axis][0];
		double highDead = deadzones[axis][1];
		
		if(lowDead < value && value < highDead) return 0;
		
		if(lowDead >= value) return (value - lowDead)/(1+lowDead);
		
		if(highDead <= value) return (value - highDead)/(1-highDead);
		
		return 0;
	}
	
	/**Puts something to a power while saving the negative
	 * @param value The input
	 * @param power Power to put it to
	 * @return result THe result
	*/
	double addPowInputFixed(int axis, double value) {
		double power = exponents[axis];
		boolean negative = false;
		//Save negativity
		if(value<0){
			negative=true;
			value *=-1;
		}
		//Remove negativity and do power
		value=Math.pow(value, power);
		//Add back in negativity
		if(negative)
			value*=-1;
		return value;
	}

}
