package org.usfirst.frc.team5940.states.opcon;
import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import other.Input;

public class OpConStandardState extends State{


	Joystick controller;
	double horizontal;
	double forward;
	DualMGDrivetrain driveThing;

	public OpConStandardState(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	DualMGDrivetrain driver;
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		controller = new Joystick (0);
		//driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new CANTalonDrive(3,4));
		// TODO make the variables not equal to null
		CANTalon r1 = new CANTalon(1);
		CANTalon r2 = new CANTalon(2);
		CANTalon l3 = new CANTalon(3);
		CANTalon l4 = new CANTalon(4);
		
		//TODO incorrect infersions here and below...
		driveThing =  new DualMGDrivetrain(new CANTalonSimpleGroup(new CANTalon[]{l3, l4}, false), new CANTalonSimpleGroup(new CANTalon[]{r1, r2}, true));
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		forward = -controller.getRawAxis(1);
		horizontal = controller.getRawAxis(4);
		forward = Input.powInputFixed(forward, 2);
		horizontal = Input.powInputFixed(horizontal, 2);
		driveThing.updateArcade(forward, horizontal);
	}
}
