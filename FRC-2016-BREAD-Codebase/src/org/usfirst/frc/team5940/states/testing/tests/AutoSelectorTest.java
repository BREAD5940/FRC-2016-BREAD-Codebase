package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.testing.Test;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

public class AutoSelectorTest implements Test {

	@Override
	public String getName() {
		return "Auto Selector";
	}

	@Override
	public void runTest() {
		long endTime = System.currentTimeMillis() + 10000;
		while(endTime > System.currentTimeMillis()) {
			TestingStandardState.updateStatus("Auto selector values: SW0-" + Components.sel0 + ", SW1-" + Components.sel1 + ", SW2-" + Components.sel2 + ", SW3-" + Components.sel3 + ", SW4-" + Components.sel4 + ", SW5-" + Components.sel5);
		}
		
	}

}
