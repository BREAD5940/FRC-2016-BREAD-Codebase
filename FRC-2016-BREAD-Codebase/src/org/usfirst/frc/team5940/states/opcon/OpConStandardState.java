package org.usfirst.frc.team5940.states.opcon;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
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

	double horizontal;
	double forward;
	double maxValue;
	boolean shiftDown;
	boolean shiftUp;
	DualMGShiftingDrivetrain driver = Components.drivetrain;
	MotorGroup roller = Components.rollerGroup;
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
		// driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new
		// CANTalonDrive(3,4)); Doesn't Work
		// Use driver = new DualMGDrivetrain(new CANTalonDrive(new Talon[]{new
		// Talon(1),new Talon(2)},new Talon[]{new Talon(3),new
		// Talon(4)}),false);
		// TODO make the variables not equal to null

		// WIRING DIFFERENT FOR DIFFERENT ROBOTS!!!
		
		// for testing the navx when it is setup
		// navx = new AHRS(SPI.Port.kMXP);
		
	}

	@Override
	protected void update() {
		forward = Components.getCorrectedAxis(3)-Components.getCorrectedAxis(2);
		horizontal = Components.getCorrectedAxis(4);
		SmartDashboard.putNumber("Forward Input", forward);
		forward = GeneralMethods.powInputFixed(forward, 2);
		horizontal = GeneralMethods.powInputFixed(horizontal, 2);
		driver.updateArcade(forward, horizontal, 1);
		
		//Components.lGroup.setValue(0.5f);
		
		//SHIFTING
		shiftDown = Components.controller.getRawButton(3);
		shiftUp = Components.controller.getRawButton(4);
		
		if (shiftUp) {
			driver.setGears(0);
		}
		else if (shiftDown) {
			driver.setGears(1);
		}
		
		//ROLLER
		float currentVal = roller.getSetValue();
		float newVal = (float) Components.getCorrectedAxis(1);
		boolean ballDetect = Components.ballDetector.get();
		if(Components.rollerDetectorInvert) ballDetect = !ballDetect;
		if (newVal < 0 && ballDetect) newVal = 0;
		roller.setValue(newVal);
	}
	
	/*private void initTalon() {
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
		
	}*/
}
