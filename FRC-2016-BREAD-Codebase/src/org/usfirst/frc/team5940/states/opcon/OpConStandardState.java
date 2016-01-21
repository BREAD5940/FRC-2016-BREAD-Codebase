package org.usfirst.frc.team5940.states.opcon;
import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
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
	DualMGDrivetrain driver;
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		Joystick controller = new Joystick (0);
		//driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new CANTalonDrive(3,4));
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

}
