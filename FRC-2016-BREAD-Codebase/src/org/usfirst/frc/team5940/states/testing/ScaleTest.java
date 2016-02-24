package org.usfirst.frc.team5940.states.testing;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotBase;

public class ScaleTest extends State {

	public ScaleTest(RobotBase robot) {
		super(robot);
	}

	@Override
	protected void init() {
		//Components.lETalon.setControlMode(CANTalon.TalonControlMode.Position);
		
	}

	@Override
	protected void update() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
