package org.usfirst.frc.team5940.motorcontrol;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CANTalonDrive implements MotorGroup {
	
	CANTalon cT1;
    CANTalon cT2;
    CANTalon cT3;
    CANTalon cT4;
    float motorsOut;
    boolean enabled = true;

	public CANTalonDrive(int m1, int m2, int m3, int m4) {
		cT1 = new CANTalon(m1);
		cT2 = new CANTalon(m2);
		cT3 = new CANTalon(m3);
		cT4 = new CANTalon(m4);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "CANTalonDrive";
	}

	@Override
	public void setValue(float motorsOut) {
		// TODO Auto-generated method stub
		cT1.set(motorsOut);
    	cT2.set(motorsOut);
    	cT3.set(motorsOut);
    	cT4.set(motorsOut);
	}

	@Override
	public float getSetValue() {
		// TODO Auto-generated method stub
		return motorsOut;
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGearsAmount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getGear() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void setGear(int gear) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		this.enabled = enabled;
		cT1.setSafetyEnabled(!enabled);
		cT2.setSafetyEnabled(!enabled);
		cT3.setSafetyEnabled(!enabled);
		cT4.setSafetyEnabled(!enabled);
	}

	@Override
	public boolean getEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean canMeasureSpeed() {
		// TODO Auto-generated method stub
		return false;
	}

}
