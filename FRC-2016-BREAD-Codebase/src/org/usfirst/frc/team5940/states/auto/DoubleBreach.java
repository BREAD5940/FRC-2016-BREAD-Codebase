package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DoubleBreach extends State {

	public DoubleBreach(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * This class moves through a defense, back, and through again.
	 */
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		AutoManager.moveForDistance(AutoManager.auto_distance_of_1, (float) 0.2);
		AutoManager.moveForDistance(AutoManager.auto_distance_of_1, (float) -0.2);
		AutoManager.moveForDistance(AutoManager.auto_distance_of_1, (float) 0.2);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
