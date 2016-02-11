package org.usfirst.frc.team5940.motorcontrol;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

// This doesn't do anything 
@Deprecated

public class CANTalonShiftingGroup extends CANTalonSimpleGroup {
	
	DoubleSolenoid solo = new DoubleSolenoid(0, 1);
	//COnsructor
	public CANTalonShiftingGroup(CANTalon[] talons, boolean inverted) {
		super(talons, inverted);
		// TODO Auto-generated constructor stub
	}
	/**Sets the gears
	 * @param gear the gear to use
	*/
	//TODO Make actually do things
	public void setGear(int gear) {
		
	}
}
