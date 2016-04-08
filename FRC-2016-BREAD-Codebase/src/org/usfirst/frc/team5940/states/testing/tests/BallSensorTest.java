package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

public class BallSensorTest implements Test {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Ball Sensor";
	}

	@Override
	public void runTest() {
		// TODO Auto-generated method stub
		long endTime = System.currentTimeMillis() + 10000;
		while(endTime > System.currentTimeMillis()) {
			TestingStandardState.updateStatus("Currently Sensing Ball: " + Components.getCorrectedDetector());
		}
	}

}
