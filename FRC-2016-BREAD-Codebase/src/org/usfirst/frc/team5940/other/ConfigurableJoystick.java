package org.usfirst.frc.team5940.other;

import edu.wpi.first.wpilibj.Joystick;

public class ConfigurableJoystick extends Joystick {

	double[][] deadzones;
	double[] exponents;
	
	/**
	 * Constructor
	 * @param port The port of the joystick, passed to super.
	 * @param deadzones A 2d array of the deadzones of the joystick. The first dimension is for each axis of the joystick. The second is two doubles as the lower, then upper bound of the deadzone.
	 * @param exponents A 2d array of exponents to raise the input to per axis. Negative inputs are corrected for, no need to worry.
	 */
	public ConfigurableJoystick(int port, double[][] deadzones, double[] exponents) {
		super(port);
		this.deadzones = deadzones;
		this.exponents = exponents;
	}
	
	/**
	 * Overrides getRawAxis of super. Applies deadzones and exponents.
	 */
	@Override
	public double getRawAxis(final int axis) {
		double out = super.getRawAxis(axis);
		if(axis < deadzones.length) out = addDeadzone(axis, out);
		if(axis < exponents.length) out = addPowInputFixed(axis, out);
		return out;
	}
	
	/**
	 * Local method to add the deadzones and scale remaining.
	 * @param axis The axis of the joystick
	 * @param value The currect computed value of said joystick
	 * @return The new value of the stick
	 */
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
	 * @return result The result
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
