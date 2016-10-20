package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.DualMGDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.other.GeneralMethods;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoManager extends State {

	public AutoManager(RobotBase robot) {
		super(robot);
	}

	// DualMGDrivetrain driver;
	// int auto_program = 1;
	static float auto_distance_of_1 = 400; // TODO Get the right value
	static float auto_distance_of_3 = 15500; // TODO Get the right value
	static float defaultAutoSpeed = (float) 0.6;
	int auto_distance_of_4a = 400; // TODO Get the right value
	int auto_distance_of_4b = 100;
	int auto_distance_of_4c = 800; // TODO Get the right value (Note: It
									// might change depending on
									// circumstance) (Note again: if this is
									// smaller than "auto_distance_of_4a"
									// than you measured VERY wrong )
	int auto_distance_of_5 = 400;
	int auto_distance_of_6 = 500;
	int distanceToBackOfHole;
	static long breachDistance = 5000;
	static float rightEncoderDistance;
	static float leftEncoderDistance;
	static float rightEncoderRate;
	static float leftEncoderRate;
	static float pastDistance;
	static float rightBonusSpeed = 0;
	static float leftBonusSpeed = 0;
	static float newRightSpeed;
	static float newLeftSpeed;
	static float[] newSpeeds;
	static float TestNumber = 0;
	static long currentTime;
	static long startTime;
	static int tooSlow = 500;//Minimum encoder velocity to continue moveForTime()ing after getMoving //TODO Update based on Michael's Thursday Testing
	static long getMoving = 100;//ms Delay before tooSlow gets checked so motors can get up to speed //TODO Increase if auto keeps breaking after a short move
	public static final long BREACH_TIME = 3750;
	// rightEncoder.setDistancePerPulse(1);
	// leftEncoder.setDistancePerPulse(1);

	/**
	 * Moves the robot through a defense and back to the courtyard through the
	 * same defense.
	 */
	public static void backAndForth() {
		moveForDistance((float) 0.2,3000);

		moveForDistance((float) -0.2,3000);
	}

	/**
	 * This method breaches a defense a goes back to the neutral zone through
	 * another defense. All distances are set by variables auto_distance_of + a
	 * number + a letter in some instances.
	 */
	public void loopAround() {
		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);

		moveForDistance((float) 0.5,3000);
	}

	/**
	 * moves forward a set distance.
	 */
	public static void moveForward() {
		moveForDistance((float) 0.2,3000);
	}

	/**
	 * Moves for a certain distance.
	 * 
	 * @param distance
	 * @param speed
	 */
	public static void moveForDistance(float speed, long time) {
		//Components.r1.setPosition(0);
		//Components.l1.setPosition(0);
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// Components.r1.set(0);
		// Components.r2.set(0);
		// Components.l1.set(0);
		// Components.l2.set(0);
		TestNumber += 1;
		// r1.setPosition(0);
		// l3.setPosition(0);
		// if (TestNumber == 1) {
		//SmartDashboard.putNumber("Distance", distance);
		// }
		startTime = System.currentTimeMillis();
		currentTime = 0;
		while (currentTime <= startTime + time) {

			// if (speed > 0) {
			// if (leftEncoderRate > rightEncoderRate) {
			// // Left is going faster than the right
			// // Give right a bonus to match the left encoder speed
			// rightBonusSpeed += 0.01;
			// } else if (rightEncoderRate > leftEncoderRate) {
			// // Right is going faster than the left
			// // Give left a bonus to match the right encoder speed
			// leftBonusSpeed += 0.01;
			// }
			// }
			// else {
			// if (leftEncoderRate < rightEncoderRate) {
			// // Left is going faster than the right because it is going
			// backwards
			// rightBonusSpeed -= 0.01;
			// }
			// else if (rightEncoderRate < leftEncoderRate) {
			// // Right is going faster than the left becuase it is going
			// backwards
			// leftBonusSpeed -= 0.01;
			// }
			// }
			// newSpeeds = GeneralMethods.lowerToNumber(speed + leftBonusSpeed,
			// speed + rightBonusSpeed);
			// newRightSpeed = newSpeeds[1];
			// newLeftSpeed = newSpeeds[0];
			Components.drivetrain.updateArcade(speed, 0, 1);
			// Components.r1.set(-speed);
			// Components.r2.set(-speed);
			// Components.l1.set(speed);
			// Components.l2.set(speed);

			// SmartDashboard.putNumber("Speed of the left encoder",
			// leftEncoderRate);
			// SmartDashboard.putNumber("Speed of the right encoder",
			// rightEncoderRate);
			// SmartDashboard.putNumber("Distance of the left encoder",
			// leftEncDistance);
			// SmartDashboard.putNumber("Distance of the right encoder",
			// rightEncDistance);
			currentTime = System.currentTimeMillis();
		}
		// SmartDashboard.putNumber("Left Encoder Distance",
		// l3.getEncPosition());
		// SmartDashboard.putNumber("Right Encoder Distance",
		// r1.getEncPosition());
		Components.drivetrain.updateArcade(0, 0, 1);
		// Components.r1.set(0);
		// Components.r2.set(0);
		// Components.l1.set(0);
		// Components.l2.set(0);

	}
	
	public static void moveForTime(long time, double speed) {
		Components.drivetrain.updateArcade(speed, 0, 1);
		/*try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		if(time <= getMoving) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			try {
				Thread.sleep(getMoving);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			long startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() < (startTime+(time-getMoving))) {
				int absLeft = Math.abs(Components.getCorrectedLeftVelocity());
				int absRight = Math.abs(Components.getCorrectedRightVelocity());
				if((absLeft < tooSlow) || (absRight < tooSlow))
					Components.drivetrain.setGears(-1);
			}
		}
		
		Components.drivetrain.updateArcade(0, 0, 1);
	}

	@Override
	protected void update() {
	}

	@Override
	protected void init() {
	}

}
