package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;

public class BreachAndPass extends State {

	public BreachAndPass(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	MotorGroup shooter = Components.rollerGroup;
	/** this class will move the robot through a defense, pass the ball, and move back. 
	 */
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		AutoStandardState.moveForDistance(AutoStandardState.auto_distance_of_3, (float) 0.5);

		shooter.setValue((float) 0.1);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shooter.setValue(0);
		
		AutoStandardState.moveForDistance(AutoStandardState.auto_distance_of_3, (float) -0.5);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
