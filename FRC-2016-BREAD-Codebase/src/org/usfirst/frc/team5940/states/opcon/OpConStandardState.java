package org.usfirst.frc.team5940.states.opcon;
import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.VictorSimpleGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import other.GeneralMethods;

public class OpConStandardState extends State{


	Joystick controller;
	double horizontal;
	double forward;
	DualMGDrivetrain driveThing;
	// for testing the navx when it is setup
//	AHRS navx;
	float scaleFactor;

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
		
		
		//WIRING DIFFERENT FOR DIFFERENT ROBOTS!!!
		Victor r1 = new Victor(1);
		Victor r2 = new Victor(3);
		Victor l3 = new Victor(0);
		Victor l4 = new Victor(2);
		// for testing the navx when it is setup
//        navx = new AHRS(SPI.Port.kMXP); 
        scaleFactor = 3;
		
		//TODO incorrect infersions here and below...
		driveThing =  new DualMGDrivetrain(new VictorSimpleGroup(new Victor[]{l3, l4}, false), new VictorSimpleGroup(new Victor[]{r1, r2}, true));
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		if (controller.getRawButton(5)) {
			scaleFactor = 3f;
		}
		else {
			scaleFactor = 1f;
		}
		//For testing the navx when it is setup
//		SmartDashboard.putNumber("Angle", navx.getAngle());
		forward = -controller.getRawAxis(1);
		horizontal = controller.getRawAxis(4);
		forward = GeneralMethods.powInputFixed(forward, 2);
		horizontal = GeneralMethods.powInputFixed(horizontal, 2);
		driveThing.updateArcade(forward, horizontal/scaleFactor, 1);
	}
}