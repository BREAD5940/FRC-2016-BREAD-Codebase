package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

public class ControllerTest implements Test {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Controller Test";
	}

	@Override
	public void runTest() {
		// TODO Auto-generated method stub
		long endTime = System.currentTimeMillis() + 10000;
		while(endTime > System.currentTimeMillis()) {
			TestingStandardState.updateStatus("Button 1 Pressed: " + Components.controller.getRawButton(0) + ", Button 2 Pressed: " + Components.controller.getRawButton(1) + ", Button 3 Pressed: " + Components.controller.getRawButton(2) + "Button 4 Pressed: " + Components.controller.getRawButton(3) + ", Button 5 Pressed: " + Components.controller.getRawButton(4) + ", Button 6 Pressed: " + Components.controller.getRawButton(5) + "Button 7 Pressed: " + Components.controller.getRawButton(6) + ", Button 8 Pressed: " + Components.controller.getRawButton(7) + ", Button 9 Pressed: " + Components.controller.getRawButton(8) + ", Axis One: " + Components.controller.getRawAxis(0) + ", Axis Two: " + Components.controller.getRawAxis(1) + ", Axis Three: " + Components.controller.getRawAxis(2) + ", Axis Four: " + Components.controller.getRawAxis(3) + ", Axis Five: " + Components.controller.getRawAxis(4) + ", Axis Six: " + Components.controller.getRawAxis(5) + ", Axis Seven: " + Components.controller.getRawAxis(6) + ", Axis Eight: " + Components.controller.getRawAxis(7) + ", POV: " + Components.controller.getPOV());
		}
	}

}
