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
		// This checks what the auto selector switch is returning and does the
		// one it is set to. Some of them do not have any auto program.
		if (Components.sel0.get()) {
			return null;
		}
		if (Components.sel1.get()) {
			return new Breach(robot);
		}
		if (Components.sel2.get()) {
			return new BreachAndComeBack(robot);
		}
		if (Components.sel3.get()) {
			return new BreachAndPass(robot);
		}
		if (Components.sel4.get()) {
			return new BreachAndPassAndComeBack(robot);
		}
		if (Components.sel5.get()) {
			return new DoubleBreach(robot);
		}
		return new Breach(robot);

	}
}
