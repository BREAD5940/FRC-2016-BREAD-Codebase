package org.usfirst.frc.team5940.states.opcon;

import org.usfirst.frc.team5940.motorcontrol.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
//import org.usfirst.frc.team5940.motorcontrol.digtalinput;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import other.GeneralMethods;


public class OpConStandardState extends State {

	Joystick controller;
	double horizontal;
	double forward;
	double shooterControl;
	double maxValue;
	boolean shiftDown;
	boolean shiftUp;
	DualMGDrivetrain driver;
    boolean buttonHeld;
    String driverPerson = "NickH"; 
	
	//DigitalInput limitswitch = new DigitalInput(9);
	// for testing the navx when it is setup
	// AHRS navx;
	float scaleFactor = 1;
	int on = 0;

	public OpConStandardState(RobotBase robot) {
		// Call the state thing
		super(robot);
	}

	@Override
	protected void init() {
		// Notify about new code
		SmartDashboard.putBoolean("New Code", true);
		// Set controller to a joystick
		
		if (driverPerson == "NickH"); {
			controller = new Joystick(1);
		}
		//controller = new Joystick(1);
		// driver = new DualMGDrivetrain(new CANTalonDrive(1,2),new
		// CANTalonDrive(3,4)); Doesn't Work
		// Use driver = new DualMGDrivetrain(new CANTalonDrive(new Talon[]{new
		// Talon(1),new Talon(2)},new Talon[]{new Talon(3),new
		// Talon(4)}),false);
		// TODO make the variables not equal to null

		// WIRING DIFFERENT FOR DIFFERENT ROBOTS!!!
		// Set victors
		/*Victor r1 = new Victor(1);
		Victor r2 = new Victor(3);
		Victor l3 = new Victor(0);
		Victor l4 = new Victor(2);

		driver = new DualMGShiftingDrivetrain(new VictorSimpleGroup(new Victor[] { l3, l4 }, false),
				new VictorSimpleGroup(new Victor[] { r1, r2 }, true), new DoubleSolenoid(0, 1), 1);*/
		
		CANTalon r1 = new CANTalon(1);
		CANTalon r2 = new CANTalon(2);
		CANTalon l3 = new CANTalon(3);
		CANTalon l4 = new CANTalon(4);
		CANTalon s = new CANTalon(5);

		driver = new DualMGShiftingDrivetrain(new CANTalonSimpleGroup(new CANTalon[] { l3, l4 }, true),
				new CANTalonSimpleGroup(new CANTalon[] { r1, r2 }, false), new DoubleSolenoid(6, 0, 1), 1);
		CANTalonSimpleGroup shooter = new CANTalonSimpleGroup(new CANTalon[] {s}, false);
	}

	@Override
	protected void update() {
		SmartDashboard.putNumber("on?", on++);
		// TODO Auto-generated method stub
		// TODO Create different driver scenarios for each driver.
		
		// For testing the navx when it is setup
		// SmartDashboard.putNumber("Angle", navx.getAngle());
		// Set all of the things
		if (driverPerson == "NickH"){
			forward = (controller.getRawAxis(3) - controller.getRawAxis(2));
			horizontal = controller.getRawAxis(4);
			shooterControl = controller.getRawAxis(0);
			if (controller.getRawButton(2)){

	    		
	    		float[] vals = driver.getSetValue();
	    		vals[0] = vals[0]*-1;
	    		vals[1] = vals[1]*-1;
	    		driver.setValues(vals[0], vals[1]);
	    		try {
					Thread.sleep(200);
				} catch (InterruptedException e) { e.printStackTrace(); }
	    		driver.setValues(0, 0);
	    		while(controller.getRawButton(2));
	    	}
			
			shiftDown = (controller.getPOV(0) == 180);
			shiftUp = (controller.getPOV(0) == 0);
		}

		if (driverPerson == "")
		
// other brake
		
//		double victor_thing1 = victor1.get();
//		double victor_thing2 = victor2.get();
//		double victor_thing3 = victor3.get();
//		double victor_thing4 = victor4.get();
//		victor_thing1 = victor_thing1 * -1;
//		victor_thing2 = victor_thing2 * -1;
//		victor_thing3 = victor_thing3 * -1;
//		victor_thing4 = victor_thing4 * -1;
//		victor1.set(victor_thing1);
//		victor2.set(victor_thing2);
//		victor3.set(victor_thing3);
//		victor4.set(victor_thing4);
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		victor1.set(0);
//		victor2.set(0);
//		victor3.set(0);
//		victor4.set(0);
		
		//maxValue = controller.getRawAxis(3);
		SmartDashboard.putNumber("Forward Input", forward);
		forward = GeneralMethods.powInputFixed(forward, 2);
		horizontal = GeneralMethods.powInputFixed(horizontal, 2);
		
//		if (controller.getRawButton(3) && driver.turning == false) {
//			driver.spinToAngle(controller.getDirectionDegrees(), true);
//		}
		driver.updateArcade(forward, horizontal, scaleFactor);
		driver.updateTank2(shooterControl,1);
		//limitswitchheld();
		
		
		
		if (shiftUp) {
			driver.setGears(0);
		}
		else if (shiftDown) {
			driver.setGears(1);
		}
	}	
	
	/*public void limitswitchheld(){
		buttonHeld = limitswitch.get();
		SmartDashboard.putBoolean("ballHeld?", buttonHeld);
	}*/
}
