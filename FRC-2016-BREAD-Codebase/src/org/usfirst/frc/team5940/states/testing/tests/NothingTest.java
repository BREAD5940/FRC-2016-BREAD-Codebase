package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.states.testing.Test;

public class NothingTest implements Test {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Doing Nothing";
	}

	@Override
	public void runTest() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
