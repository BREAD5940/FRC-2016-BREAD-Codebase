package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;

public class DrivetrainRightTest implements Test {

	@Override
	public String getName() {
		return "Drivetrain Right";
	}

	@Override
	public void runTest() {
		Components.drivetrain.updateArcade(0, 0.25, 1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Components.drivetrain.updateArcade(0, 0, 1);
	}

}
