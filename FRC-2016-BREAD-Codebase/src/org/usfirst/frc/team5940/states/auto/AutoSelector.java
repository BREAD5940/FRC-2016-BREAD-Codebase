package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;

public class AutoSelector {
	
	public static State getSelectedAuto(RobotBase robot) {
		if(Components.sel0.get()) return new BreachAndShoot(robot);
		if(Components.sel1.get()) return new DoubleBreach(robot);
		if(Components.sel2.get()) return new BreachAndPass(robot);
		if(Components.sel3.get()) return new ShootFromSpy(robot);
		if(Components.sel4.get()) return null;
		if(Components.sel5.get()) return null;
		return null;
	}
}
