package org.usfirst.frc.team5940.management;

import org.usfirst.frc.team5940.motorcontrol.DualMGShiftingSpeedyDrivetrain;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSimpleGroup;
import org.usfirst.frc.team5940.motorcontrol.groups.CANTalonSpeedyGroup;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;
import org.usfirst.frc.team5940.other.ConfigurableJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
/**
 * This class calls all of the component variables on the robot.  
 * @author Team 5940 
 *
 */
public class Components {
	//DRIVETRAIN
	//Inversions
	public static boolean lMotorInvert = true;
	public static boolean rMotorInvert = false;
	public static boolean lEncoderInvert = false;
	public static boolean rEncoderInvert = true;
	
	public static float lLowMotorScaleFactor = -10;
	public static float lHighMotorScaleFactor = -20;
	public static float rLowMotorScaleFactor = 10;
	public static float rHighMotorScaleFactor = 20;
	
	//Encoder CANTalons
	public static CANTalon rETalon = new CANTalon(2);
	public static CANTalon lETalon = new CANTalon(3);
	
	//Encoder pulses/ft
	public static float encPulsesPerFloatLow = 1;//TODO Needs to be calculated or tested
	public static float encPulsesPerFloatHigh = 1;//TODO Needs to be calculated or tested
	
	//Drive CANTalons
	public static CANTalon r1 = new CANTalon(1);
	public static CANTalon r2 = rETalon;
	public static CANTalon l1 = lETalon;
	public static CANTalon l2 = new CANTalon(4);
	
	public static void setupTalons() {//TODO call
		r1.changeControlMode(TalonControlMode.Follower);
		r1.set(2);
		l2.changeControlMode(TalonControlMode.Follower);
		l2.set(3);
		
		float lPF = encPulsesPerFloatLow;
		float rPF = encPulsesPerFloatLow;
		if(lEncoderInvert) lPF *= -1;
		if(rEncoderInvert) rPF *= -1;
		lETalon.configEncoderCodesPerRev((int) lPF);
		rETalon.configEncoderCodesPerRev((int) rPF);
	}
	
	//Drive MotorGroups
	public static CANTalonSpeedyGroup lGroup = new CANTalonSpeedyGroup(new CANTalon[]{l1}, lMotorInvert, lLowMotorScaleFactor);
	public static CANTalonSpeedyGroup rGroup = new CANTalonSpeedyGroup(new CANTalon[]{r2}, rMotorInvert, rLowMotorScaleFactor);
//	public static MotorGroup lGroup = new CANTalonSimpleGroup(new CANTalon[]{l1}, lMotorInvert);
//	public static MotorGroup rGroup = new CANTalonSimpleGroup(new CANTalon[]{r2}, rMotorInvert);
	
	//Solenoid Config
	public static int PCMCANPort = 6;
	public static int PCMUpPort = 1;//TODO test
	public static int PCMDownPort = 0;//TODO test
	
	//Shifting Solenoid
	public static DoubleSolenoid shifter = new DoubleSolenoid(PCMCANPort, PCMDownPort, PCMUpPort);
	
	//Drivetrain
	public static DualMGShiftingSpeedyDrivetrain drivetrain = new DualMGShiftingSpeedyDrivetrain(lGroup, rGroup, shifter, 0, lLowMotorScaleFactor, lHighMotorScaleFactor, rLowMotorScaleFactor, rHighMotorScaleFactor);//TODO Needs to be changes to ShiftingSpeedy when encoder scaling tested
	
	
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
	public static boolean[] invertAxes = {false, true, false, false, false, true};//TODO NOT USED IN COMPONENT SETUP, MUST BE USED IN CODE UNLESS getCorrectedAxis USED
	
	//Port
	public static int controllerPort = 0;
	
	//Joystick
	static double exp = 3;
	public static Joystick controller = new ConfigurableJoystick(controllerPort, new double[][]{/*{-0.05, 0.05}, {-0.05, 0.05}, {-0.05, 0.05}, {-0.05, 0.05}, {-0.15, 0.15}, {-0.05, 0.05}*/}, /*new double[]{}*/new double[]{exp, exp, exp, exp, exp, exp});
	
	//Axis corrector
	public static double getCorrectedAxis(int axis) {
		boolean invert = false;
		if(axis < invertAxes.length) invert = invertAxes[axis];
		double out = controller.getRawAxis(axis);
		if(invert) out = -out;
		return out;
	}
	
	
	//AUTO SELECTOR SWITCHES
	//DigitalInputs
	public static DigitalInput sel0 = new DigitalInput(0);
	public static DigitalInput sel1 = new DigitalInput(1);
	public static DigitalInput sel2 = new DigitalInput(2);
	public static DigitalInput sel3 = new DigitalInput(3);
	public static DigitalInput sel4 = new DigitalInput(4);
	public static DigitalInput sel5 = new DigitalInput(5);
	
	
	//CAMERA
	public static String cameraID = "cam0";
}
