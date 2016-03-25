
package org.usfirst.frc.team5940.motorcontrol;

import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSpeedyGroup;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DualMGShiftingSpeedyDrivetrain extends DualMGShiftingDrivetrain {

	double lowLGearScale;
	double highLGearScale;
	double lowRGearScale;
	double highRGearScale;
	
	public DualMGShiftingSpeedyDrivetrain(CANTalonSpeedyGroup left, CANTalonSpeedyGroup right, DoubleSolenoid gearShifter, int gear, double lowLGearScale, double highLGearScale, double lowRGearScale, double highRGearScale) {
		super(left, right, gearShifter, gear);
		this.lowLGearScale = lowLGearScale;
		this.highLGearScale = highLGearScale;
		this.lowRGearScale = lowRGearScale;
		this.highRGearScale = highRGearScale;
		this.setGears(gear);
	}
	
	/**
	 * This sets the gears to the input. If you input a gear that is greater or less than
	 * than the amount of gears we have than it will set it to the max gear or the lowest gear, respectively. 
	 * @param gear
	 *            the gear to change to
	 */
	@Override
	public void setGears(int gear) {
		super.setGears(gear);
		CANTalonSpeedyGroup left = (CANTalonSpeedyGroup) this.left;
		CANTalonSpeedyGroup right = (CANTalonSpeedyGroup) this.right;
		
		if(gear == 0) {
			left.setScaleFactor((float) lowLGearScale);
			right.setScaleFactor((float) lowRGearScale);
		}
		
		if(gear == 1) {
			left.setScaleFactor((float) highLGearScale);
			right.setScaleFactor((float) highRGearScale);
		}
	}

}
