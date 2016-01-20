package org.usfirst.frc.team5940.states.opcon;

import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Talon;

public class OpConStandardState extends State{

	public OpConStandardState(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	Joystick controller;
	Talon backleft;
	Talon frontleft;
	Talon backright;
	Talon frontright;
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		Joystick controller = new Joystick (0);
		Talon backleft = new Talon (0);
		Talon frontleft = new Talon (0);
		Talon backright = new Talon (0);
		Talon frontright = new Talon (0);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

}
