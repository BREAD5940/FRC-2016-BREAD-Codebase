package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

public class RollerInwardTest implements Test {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Roller Inward";
	}

	@Override
	public void runTest() {
		// TODO Auto-generated method stub
		Components.roller.setValue(-0.25f);
		TestingStandardState.pause(5000);
		Components.roller.setValue(0);
	}
}
