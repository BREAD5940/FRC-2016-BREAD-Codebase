package org.usfirst.frc.team5940.states.opcon;

import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.VictorSimpleGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import other.GeneralMethods;

public class OpConStandardState extends State {

	Joystick controller;
	double horizontal;
	double forward;
	double maxValue;
	DualMGDrivetrain driver;
	// for testing the navx when it is setup
	// AHRS navx;
	float scaleFactor;

	public OpConStandardState(RobotBase robot) {
		// Call the state thing
		super(robot);
	}

	@Override
	protected void init() {
		// Notify about new code
		SmartDashboard.putBoolean("New Code", true);
		// Set controller to a joystick
		controller = new Joystick(1);
		// driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new
		// CANTalonDrive(3,4)); Doesn't Work
		// Use driver = new DualMGDrivetrain(new CANTalonDrive(new Talon[]{new
		// Talon(1),new Talon(2)},new Talon[]{new Talon(3),new
		// Talon(4)}),false);
		// TODO make the variables not equal to null

		// WIRING DIFFERENT FOR DIFFERENT ROBOTS!!!
		// Set victors
		Victor r1 = new Victor(1);
		Victor r2 = new Victor(3);
		Victor l3 = new Victor(0);
		Victor l4 = new Victor(2);
		// for testing the navx when it is setup
		// navx = new AHRS(SPI.Port.kMXP);
		// Set scale factor
		scaleFactor = 3;

		// TODO incorrect infersions here and below...
		// Set the driver
		driver = new DualMGDrivetrain(new VictorSimpleGroup(new Victor[] { l3, l4 }, false),
				new VictorSimpleGroup(new Victor[] { r1, r2 }, true));
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

		// For testing the navx when it is setup
		// SmartDashboard.putNumber("Angle", navx.getAngle());
		// Set all of the things
		forward = controller.getRawAxis(1);
		horizontal = controller.getRawAxis(2);
		maxValue = controller.getRawAxis(3);
		SmartDashboard.putNumber("Direction", controller.getDirectionDegrees());
		forward = GeneralMethods.powInputFixed(forward, 2);
		horizontal = GeneralMethods.powInputFixed(horizontal, 2);
		if (controller.getRawButton(3) && driver.turning == false) {
			driver.spinToAngle(controller.getDirectionDegrees(), true);
		}
		// Update the meme drive of the driver
	}
}
