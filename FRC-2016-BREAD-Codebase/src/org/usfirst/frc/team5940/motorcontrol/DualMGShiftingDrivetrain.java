package org.usfirst.frc.team5940.motorcontrol;

import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DualMGShiftingDrivetrain extends DualMGDrivetrain {

	DoubleSolenoid gearChanger;
	int currentGear;

	public DualMGShiftingDrivetrain(MotorGroup left, MotorGroup right, DoubleSolenoid gearShifter, int gear) {
		super(left, right);
		gearChanger = gearShifter;
		currentGear = gear;
		// TODO Auto-generated constructor stub
	}

	/**
	 * This returns the maximum amount of gears that both of the motorGroups
	 * have.
	 * 
	 * @return an int representing above
	 */
	@Override
	public int getGearsAmount() {
		return 2;
	}

	/**
	 * This sets the gears to the input. If you input a gear that is greater or less than
	 * than the amount of gears we have than it will set it to the max gear or the lowest gear, respectively. 
	 * @param gear
	 *            the gear to change to
	 */
	@Override
	public void setGears(int gear) {

		// This is the total amount of gears in the gear box
		int totalGears = getGearsAmount();

		if (gear < 0) {
			gear = 0;
		} else if (gear > totalGears) {
			gear = totalGears - 1;
		}
		if (gear == 1) {
			gearChanger.set(DoubleSolenoid.Value.kForward);
			currentGear = gear;
		} else if (gear == 0) {
			gearChanger.set(DoubleSolenoid.Value.kReverse);
			currentGear = gear;
		}
	}

	/**
	 * This just returns the MotorGroup gear.
	 * 
	 * @return gear the gear of the left motor
	 */
	@Override
	public int getGears() {
		return currentGear;
	}
}
