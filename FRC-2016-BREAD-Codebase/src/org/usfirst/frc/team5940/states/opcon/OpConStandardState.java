package org.usfirst.frc.team5940.states.opcon;

import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.groups.VictorSimpleGroup;
import org.usfirst.frc.team5940.other.GeneralMethods;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OpConStandardState extends State {

	Joystick controller;
	double horizontal;
	double forward;
	double maxValue;
	boolean shiftDown;
	boolean shiftUp;
	DualMGShiftingDrivetrain driver;
	CANTalonSimpleGroup roller = new CANTalonSimpleGroup(new CANTalon[]{new CANTalon(0)}, false);
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
		// Set controller to a joystick
		controller = new Joystick(0);
		// driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new
		// CANTalonDrive(3,4)); Doesn't Work
		// Use driver = new DualMGDrivetrain(new CANTalonDrive(new Talon[]{new
		// Talon(1),new Talon(2)},new Talon[]{new Talon(3),new
		// Talon(4)}),false);
		// TODO make the variables not equal to null

		// WIRING DIFFERENT FOR DIFFERENT ROBOTS!!!
		
		// for testing the navx when it is setup
		// navx = new AHRS(SPI.Port.kMXP);
		
		initTalon();
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

		// For testing the navx when it is setup
		// SmartDashboard.putNumber("Angle", navx.getAngle());
		// Set all of the things
		forward = controller.getRawAxis(3)-controller.getRawAxis(2);
		horizontal = controller.getRawAxis(4);
		//maxValue = controller.getRawAxis(3);
		SmartDashboard.putNumber("Forward Input", forward);
		forward = -GeneralMethods.powInputFixed(forward, 2);
		horizontal = GeneralMethods.powInputFixed(horizontal, 2);
//		if (controller.getRawButton(3) && driver.turning == false) {
//			driver.spinToAngle(controller.getDirectionDegrees(), true);
//		}
		driver.updateArcade(forward, horizontal, 1);
		roller.setValue((float) -controller.getRawAxis(1));
		shiftDown = controller.getRawButton(3);
		shiftUp = controller.getRawButton(4);
		
		if (shiftUp) {
			driver.setGears(0);
		}
		else if (shiftDown) {
			driver.setGears(1);
		}
	}
	
	private void initTalon() {
		CANTalon r1 = new CANTalon(1);
		CANTalon r2 = new CANTalon(2);
		CANTalon l3 = new CANTalon(3);
		CANTalon l4 = new CANTalon(4);
		
		driver = new DualMGShiftingDrivetrain(new CANTalonSimpleGroup(new CANTalon[] { l3, l4 }, true), new CANTalonSimpleGroup(new CANTalon[] { r1, r2 }, false), new DoubleSolenoid(6, 0, 1), 1);
	}
	
	private void initVictor() {
		Victor r1 = new Victor(0);
		Victor r2 = new Victor(2);
		Victor l3 = new Victor(1);
		Victor l4 = new Victor(3);
		driver = new DualMGShiftingDrivetrain(new VictorSimpleGroup(new Victor[] { l3, l4 }, false), new VictorSimpleGroup(new Victor[] { r1, r2 }, true), new DoubleSolenoid(6, 0, 1), 1);
		
	}
}
