package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;

public class GearShiftUpTest implements Test {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Shift Up";
	}

	@Override
	public void runTest() {
		// TODO Auto-generated method stub
		Components.drivetrain.setGears(1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
