package org.usfirst.frc.team5940.management;

import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class Components {
	//DRIVETRAIN
	//Inversions
	public static boolean lMotorInvert = true;
	public static boolean rMotorInvert = false;
	public static boolean lEncoderInvert = false;
	public static boolean rEncoderInvert = true;
	
	//Encoder CANTalons
	public static CANTalon rETalon = new CANTalon(2);
	public static CANTalon lETalon = new CANTalon(3);
	
	//Drive CANTalons
	public static CANTalon r1 = new CANTalon(1);
	public static CANTalon r2 = rETalon;
	public static CANTalon l1 = lETalon;
	public static CANTalon l2 = new CANTalon(4);
	
	//Drive MotorGroups
	public static MotorGroup lGroup = new CANTalonSimpleGroup(new CANTalon[]{l1, l2}, lMotorInvert);
	public static MotorGroup rGroup = new CANTalonSimpleGroup(new CANTalon[]{r1, r2}, rMotorInvert);
	
	//Solenoid Config
	public static int PCMCANPort = 6;
	public static int PCMUpPort = 1;//TODO test
	public static int PCMDownPort = 0;//TODO test
	
	//Shifting Solenoid
	public static DoubleSolenoid shifter = new DoubleSolenoid(PCMCANPort, PCMUpPort, PCMDownPort);
	
	//Drivetrain
	public static DualMGShiftingDrivetrain drivetrain = new DualMGShiftingDrivetrain(lGroup, rGroup, shifter, 0);
	
	
	//ROLLER
	//Inversions
	public static boolean rollerDetectorInvert = true;//TODO NOT USED IN COMPONENT SETUP, MUST BE USED IN CODE
	public static boolean rollerInvert = true;
	
	//Switch
	public static DigitalInput ballDetector = new DigitalInput(9);
	
	//Roller CANTalon
	public static CANTalon roller = new CANTalon(0);
	
	//Roller MotorGroup
	public static MotorGroup rollerGroup = new CANTalonSimpleGroup(new CANTalon[]{roller}, rollerInvert);
	
	//Detector corrector
	public static boolean getCorrectedDetector() {
		if(rollerDetectorInvert) return !ballDetector.get();
		return ballDetector.get();
	}
	
	//CONTROLLERS
	//Inversions
	public static boolean[] invertAxes = {false, true, false, false, false, true};//TODO test, NOT USED IN COMPONENT SETUP, MUST BE USED IN CODE
	
	//Port
	public static int controllerPort = 0;
	
	//Joystick
	public static Joystick controller = new Joystick(controllerPort);
	
	//Axis corrector
	public static double getCorrectedAxis(int axis) {
		boolean invert = false;
		if(axis < invertAxes.length) invert = invertAxes[axis];
		double out = controller.getRawAxis(axis);
		if(invert) out = -out;
		return out;
	}
	
	
	//CAMERA
	//TODO it
}
