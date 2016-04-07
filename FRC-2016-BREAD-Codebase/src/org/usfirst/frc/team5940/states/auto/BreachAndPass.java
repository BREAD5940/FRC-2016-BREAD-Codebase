package org.usfirst.frc.team5940.states.auto;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.states.State;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** this class will move the robot through a defense, pass the ball, and move back. 
 */
public class BreachAndPass extends State {

	public BreachAndPass(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		AutoManager.moveForDistance((float) AutoManager.defaultAutoSpeed, AutoManager.breachDistance);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Components.roller.pass();
		
		//AutoManager.moveForDistance(AutoManager.auto_distance_of_3, (float) -1000);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

}
