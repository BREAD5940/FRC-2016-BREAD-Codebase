package org.usfirst.frc.team5940.motorcontrol;

import edu.wpi.first.wpilibj.Encoder;

public class RobotPostion implements Runnable {

	Encoder encoderLeft;
	Encoder encoderRight;
	double encoder_val1;
	double encoder_val2;
	double encoder_diff;
	double position;
	double robot_wheel_base_width_for_trig;
	
	public RobotPostion (Encoder encoder_l, Encoder encoder_r, double starting_position_of_robot, double robot_wheel_base_width){
		encoderLeft = encoder_l;
		encoderRight = encoder_r;
		position = starting_position_of_robot;
		robot_wheel_base_width_for_trig = robot_wheel_base_width;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true){
			encoder_val1 = encoderLeft.getRate();
			encoder_val2 = encoderRight.getRate();
			
			if (encoder_val1 < encoder_val2){
				encoder_diff = encoder_val2 - encoder_val1;
			}
			if (encoder_val2 <= encoder_val1){
				encoder_diff = encoder_val1 - encoder_val2;
			}
			
			position += Math.asin(encoder_diff/robot_wheel_base_width_for_trig);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public double getOrientation() {
		return this.position;
	}

}
