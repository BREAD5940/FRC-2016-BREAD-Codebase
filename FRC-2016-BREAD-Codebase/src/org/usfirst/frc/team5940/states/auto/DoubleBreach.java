package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;

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
		AutoManager.backAndForth();
		AutoManager.moveForward();
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}