package org.usfirst.frc.team5940.motorcontrol;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class CANTalonShiftingGroup extends CANTalonSimpleGroup {
	
	DoubleSolenoid solo = new DoubleSolenoid(0, 1);
	
	public CANTalonShiftingGroup(CANTalon[] talons, boolean inverted) {
		super(talons, inverted);
		// TODO Auto-generated constructor stub
	}
	
	public void setGear(int gear) {
		
	}
}
