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
 * 
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

		// Checks who the current driver is and updates variables on the
		// controller values
		if (driverPerson.equals("NickH"))
			updateNickH();
		if (driverPerson.equals("Hung"))
			updateHung();
		if (driverPerson.equals("Alex"))
			updateAlex();
		if (driverPerson.equals("Marius"))
			updateMarius();

		// Makes the robot move in arcade drive based on what updateDriver set
		// forward and horizontal to
		driver.updateArcade(forward, horizontal, 1);

		// Changes the gears based on what updateDriver set the variables to.
		if (shiftUp) {
			driver.setGears(0);
		} else if (shiftDown) {
			driver.setGears(1);
		}

		// Updates the shooter
		float newRVal = (float) shooterControl;
		// Checks if driver is trying to grab a ball with one already in it and
		// sets rollerSpeed to zero if that is true. This is to make sure the
		// driver does not suck the ball in to far into the robot
		if (Components.getCorrectedDetector() && newRVal < 0)
			newRVal = 0;
		// Sets the roller to the wanted roller speed
		Components.roller.setValue(newRVal);
	}

	/**
	 * Updates the control for Nick's drive.
	 */
	private void updateNickH() {
		// getCorrectedAxis returns the controller axis value based on if it is
		// inverted. This sets the forward and horizontal speed for if this was
		// arcade drive
		forward = (Components.getCorrectedAxis(3) - Components.getCorrectedAxis(2));
		horizontal = Components.getCorrectedAxis(4);

		// Sets the shooter speed
		shooterControl = Components.getCorrectedAxis(1);

		// If the pass button is being pressed it sets the shooter speed to
		// slower
		if (controller.getRawButton(1)) {
			shooterControl = 0.25;
		}

		// This is the EBrake
		if (controller.getRawButton(2)) {

			// sets vals to the current speed of the driver and then finds then
			// inverts them
			float[] vals = driver.getSetValue();
			vals[0] = vals[0] * -1;
			vals[1] = vals[1] * -1;
			// This sets the robot speed to the inverted values
			driver.setValues(vals[0], vals[1]);
			// This pauses the program for a second so that the robot has time
			// to stop.
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Sets the robot speed to zero and waits for the driver to stop pressing the e-brake button
			driver.setValues(0, 0);
			while (controller.getRawButton(2))
				;
		}

		// Sets shiftUp and down based on what the d-pad said.
		shiftDown = (controller.getPOV(0) == 180);
		shiftUp = (controller.getPOV(0) == 0);
	}

	/**
	 * Updates the control for Hung's drive.
	 */
	private void updateHung() {
		// See updateNickH()
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
	 * Updates the control for Alex's drive.
	 */
	private void updateAlex() {
		// see updateNickH()
		forward = Components.getCorrectedAxis(1);
		horizontal = Components.getCorrectedAxis(4);
		shooterControl = (Components.getCorrectedAxis(3) - Components.getCorrectedAxis(2));
		if (controller.getRawButton(1)) {
			// shooterControl = 0.25;
			Components.roller.pass();
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
			while (controller.getRawButton(10))
				;
		}

		shiftDown = controller.getRawButton(6);
		shiftUp = controller.getRawButton(5);
	}

	/**
	 * Updates the control for Marius's drive.
	 */
	private void updateMarius() {
		// We never found Marius's preferred driver type.

	}
}