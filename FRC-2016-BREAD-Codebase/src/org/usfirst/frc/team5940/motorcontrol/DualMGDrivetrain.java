package org.usfirst.frc.team5940.motorcontrol;

public class DualMGDrivetrain {
	
	public final MotorGroup left;
	public final MotorGroup right;
	
	public DualMGDrivetrain(MotorGroup left, MotorGroup right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * This returns the maximum amount of gears that the motorGroups have.
	 * @return
	 */
	public int getGearsAmount() {
		
		// Yo this be the current that each of the motorGroups are in dawg
		int leftGearCount;
		int rightGearCount;
		
		//I just be setting them to the correct amount
		leftGearCount = left.getGearsAmount();
		rightGearCount = right.getGearsAmount();
		
		//This checks the which gear is smaller and returns that one
		if (leftGearCount < rightGearCount) {
			return leftGearCount;
		}
		else {
			return rightGearCount;
		}
	}
	
	/**
	 * This sets the gears to the input and if you input a gear that is greater than the amount of gears we have than it will set it to the max gear.
	 * Oh yeah by the way I have the total amount of gears currently set to four and we might need to change that
	 * @param gear
	 */
	public void setGears(int gear) {
		
		//Yo dawg this be the total amount of gears in the gear box
		int totalGears = 4;
		
		//This be because in order to set the gear you need
		gear = gear - 1;
		if (gear < 0) {
			left.setGear(0);
			right.setGear(0);
		}
		else if (gear >= totalGears){
			left.setGear(totalGears - 1);
			right.setGear(totalGears - 1);
		}
		else {
			left.setGear(gear);
			right.setGear(gear);
		}
	}
}