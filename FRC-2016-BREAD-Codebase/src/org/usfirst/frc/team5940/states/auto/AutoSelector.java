package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {

	/**
	 * This function chooses a auto program based on a rotary switch input.
	 */
	public static State getSelectedAuto(RobotBase robot) {
		boolean practiceMode = true;
		if (practiceMode == true) {
			return new BreachAndPass(robot);
		} else {
			if (Components.sel0.get())
				return new BreachAndPass(robot);
			if (Components.sel1.get())
				return new DoubleBreach(robot);
			if (Components.sel2.get())
				return null;
			if (Components.sel3.get())
				return null;
			if (Components.sel4.get())
				return null;
			if (Components.sel5.get())
				return null;
			return null;
		}
	}
}
