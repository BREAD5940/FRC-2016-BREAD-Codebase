package org.usfirst.frc.team5940.states.opcon;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;

public class OpConStandardState extends State{


	Joystick controller;
	double right;
	double left;
	DualMGDrivetrain driveThing;

	public OpConStandardState(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	DualMGDrivetrain driver;
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		Joystick controller = new Joystick (0);
		//driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new CANTalonDrive(3,4));
		// TODO make the variables not equal to null
		driveThing =  new DualMGDrivetrain(null, null);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		left = controller.getRawAxis(1);
		right = controller.getRawAxis(4);
		left = -left;
		// TODO add the squaring the input thingy
		driveThing.arcade(left, right);
	}
}
