package org.usfirst.frc.team5940.states.opcon;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This code runs all of our driver-related code. 
 * @author The 5940 Robotics team.
 *
 */
public class OpConStandardState extends State {

	Joystick controller = Components.controller;
	double horizontal;
	double forward;
	double maxValue;
	boolean shiftDown;
	boolean shiftUp;
	double shooterControl;
	DualMGShiftingDrivetrain driver = Components.drivetrain;
	MotorGroup roller = Components.rollerGroup;
	int working = 0;
	float scaleFactor;

	String driverPerson = "Alex";

	public OpConStandardState(RobotBase robot) {
		// Call the state thing
		super(robot);
	}

	@Override
	protected void init() {
	}

	/**
	 * Updates driver inputs and controls.
	 */
	@Override
	protected void update() {
		// forward =
		// Components.getCorrectedAxis(3)-Components.getCorrectedAxis(2);
		// horizontal = Components.getCorrectedAxis(4);
		// SmartDashboard.putNumber("Forward Input", forward);
		// forward = GeneralMethods.powInputFixed(forward, 2);
		// horizontal = GeneralMethods.powInputFixed(horizontal, 2);

		SmartDashboard.putNumber("Update working?", working++);

		if (driverPerson.equals("NickH"))
			setNickH();
		if (driverPerson.equals("Hung"))
			setHung();
		if (driverPerson.equals("Alex"))
			setAlex();
		if (driverPerson.equals("Marius"))
			setMarius();

		SmartDashboard.putNumber("forward", forward);
		
		driver.updateArcade(-forward, -horizontal, 1);
		
		SmartDashboard.putNumber("Left Encoder Distance", Components.l1.getEncPosition());
		SmartDashboard.putNumber("Right Encoder Distance", Components.r2.getEncPosition());
		// shiftDown = controller.getRawButton(1);
		// shiftUp = controller.getRawButton(4);

		if (shiftUp) {
			driver.setGears(0);
		} else if (shiftDown) {
			driver.setGears(1);
		}

		float newRVal = (float) shooterControl;
		if (Components.getCorrectedDetector() && newRVal < 0)
			newRVal = 0;
		roller.setValue(newRVal);
	}

	/**
	 * Sets the control for Nick's drive.
	 */
	private void setNickH() {
		SmartDashboard.putNumber("NickH working?", working++);
		forward = (Components.getCorrectedAxis(3) - Components.getCorrectedAxis(2));
		horizontal = Components.getCorrectedAxis(4);
		shooterControl = Components.getCorrectedAxis(1);

		if (controller.getRawButton(1)) {
			shooterControl = 0.25;
		}

		if (controller.getRawButton(2)) {

			float[] vals = driver.getSetValue();
			vals[0] = vals[0] * -1;
			vals[1] = vals[1] * -1;
			driver.setValues(vals[0], vals[1]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.setValues(0, 0);
			while (controller.getRawButton(2))
				;
		}

		shiftDown = (controller.getPOV(0) == 180);
		shiftUp = (controller.getPOV(0) == 0);
	}

	/**
	 * Sets the control for Hung's drive.
	 */
	private void setHung() {
		forward = (Components.getCorrectedAxis(3) - Components.getCorrectedAxis(2));
		horizontal = Components.getCorrectedAxis(0);
		boolean rightBumperOn = controller.getRawButton(5);
		boolean leftBumperOn = controller.getRawButton(6);
		if (rightBumperOn) {
			shooterControl = 1;
		}
		if (leftBumperOn) {
			shooterControl = -1;
		}
		if (controller.getRawButton(4)) {
			shooterControl = 0.25;
		}

		if (controller.getRawButton(2)) {

			float[] vals = driver.getSetValue();
			vals[0] = vals[0] * -1;
			vals[1] = vals[1] * -1;
			driver.setValues(vals[0], vals[1]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.setValues(0, 0);
			while (controller.getRawButton(2))
				;
		}
		shiftDown = (controller.getRawButton(1));
		shiftUp = (controller.getRawButton(3));
	}

	/**
	 * Sets the control for Alex's drive.
	 */
	private void setAlex() {
		forward = Components.getCorrectedAxis(1);
		horizontal = Components.getCorrectedAxis(4);
		shooterControl = (Components.getCorrectedAxis(3) - Components.getCorrectedAxis(2));
		if (controller.getRawButton(9)) {
			shooterControl = 0.25;
		}

		if (controller.getRawButton(10)) {
			float[] vals = driver.getSetValue();
			vals[0] = vals[0] * -1;
			vals[1] = vals[1] * -1;
			driver.setValues(vals[0], vals[1]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.setValues(0, 0);
			while (controller.getRawButton(10));
		}

		shiftDown = controller.getRawButton(5);
		shiftUp = controller.getRawButton(6);
	}

	/**
	 * Sets the control for Marius's drive.
	 */
	private void setMarius() {

	}
}