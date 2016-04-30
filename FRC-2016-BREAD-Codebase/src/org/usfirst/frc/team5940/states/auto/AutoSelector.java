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
		
		if (Components.sel0.get()) {
			SmartDashboard.putNumber("auto", 0);
			return null;}
		if (Components.sel1.get()) {
			SmartDashboard.putNumber("auto", 1);
			return new Breach(robot);}
		if (Components.sel2.get()) {
			SmartDashboard.putNumber("auto", 2);
			return new BreachAndComeBack(robot);}
		if (Components.sel3.get()) {
			SmartDashboard.putNumber("auto", 3);
			return new BreachAndPass(robot);}
		if (Components.sel4.get()) {
			SmartDashboard.putNumber("auto", 4);
			return new BreachAndPassAndComeBack(robot);}
		if (Components.sel5.get()) {
			SmartDashboard.putNumber("auto", 5);
			return new Anti2BallAuto(robot);}
		return new Breach(robot);
		
	}
}
