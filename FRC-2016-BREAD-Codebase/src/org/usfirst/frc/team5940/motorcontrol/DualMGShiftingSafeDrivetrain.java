package org.usfirst.frc.team5940.motorcontrol;

import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DualMGShiftingSafeDrivetrain extends DualMGShiftingDrivetrain {

	public DualMGShiftingSafeDrivetrain(MotorGroup left, MotorGroup right, DoubleSolenoid gearShifter, int gear) {
		super(left, right, gearShifter, gear);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setGears(int gear)
	{
		if(gear == -1)
		{
			//TODO add death stuff hear
		}
		else 
		{
			super.setGears(gear);
		}
	}
}
