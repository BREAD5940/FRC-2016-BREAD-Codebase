package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

public class ControllerTest implements Test {

	String currentTest;
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Controller Test: " + currentTest;
	}

	@Override
	public void runTest() {
		currentTest = "Buttons";
		// TODO Auto-generated method stub
		long endTime = System.currentTimeMillis() + 10000;
		while(endTime > System.currentTimeMillis()) {
			TestingStandardState.updateStatus("Button 1 Pressed: " + Components.controller.getRawButton(0) + ", Button 2 Pressed: " + Components.controller.getRawButton(1) + ", Button 3 Pressed: " + Components.controller.getRawButton(2) + "Button 4 Pressed: " + Components.controller.getRawButton(3) + ", Button 5 Pressed: " + Components.controller.getRawButton(4) + ", Button 6 Pressed: " + Components.controller.getRawButton(5) + "Button 7 Pressed: " + Components.controller.getRawButton(6) + ", Button 8 Pressed: " + Components.controller.getRawButton(7) + ", Button 9 Pressed: " + Components.controller.getRawButton(8));
		}
		currentTest = "Axis and POV";
		endTime = System.currentTimeMillis() + 10000;
		while(endTime > System.currentTimeMillis()) {
			TestingStandardState.updateStatus("Axis One: " + Components.getCorrectedAxis(0) + ", Axis Two: " + Components.getCorrectedAxis(1) + ", Axis Three: " + Components.getCorrectedAxis(2) + ", Axis Four: " + Components.getCorrectedAxis(3) + ", Axis Five: " + Components.getCorrectedAxis(4) + ", Axis Six: " + Components.getCorrectedAxis(5) + ", Axis Seven: " + Components.getCorrectedAxis(6) + ", Axis Eight: " + Components.getCorrectedAxis(7) + ", POV: " + Components.controller.getPOV());
		}
	}

}
