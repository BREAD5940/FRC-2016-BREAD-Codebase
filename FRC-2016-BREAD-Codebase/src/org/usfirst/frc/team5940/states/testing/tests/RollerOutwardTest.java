package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

public class RollerOutwardTest implements Test {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Roller Outward";
	}

	@Override
	public void runTest() {
		// TODO Auto-generated method stub
		Components.roller.setValue(0.25f);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Components.roller.setValue(0);
	}
}