package org.usfirst.frc.team5940.camera;

import org.usfirst.frc.team5940.states.State;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the NIVision class to do vision processing. 
 * The image is acquired from the USB Webcam, then a circle is overlayed on it. 
 * The NIVision class supplies dozens of methods for different types of processing. 
 * The resulting image can then be sent to the FRC PC Dashboard with setImage()
 */
public class SwitchingCameraServerInit extends State {
    public SwitchingCameraServerInit(RobotBase robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	int session;
    Image frame;
    boolean camEnabled = false;
    Joystick controller = new Joystick(0);

    public void robotInit() {

        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam1",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
    }
    
    
    public void camUpdater() {
    	NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);


            NIVision.IMAQdxGrab(session, frame, 1);
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            
            if(controller.getRawButton(2)||controller.getRawButton(3)) {
            	camEnabled = false;
            }
            Timer.delay(0.005);		// wait for a motor update time
        }

	@Override
	protected void init() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam1",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);

		
	}

	@Override
	protected void update() {
		//switch camera
		if(controller.getRawButton(2)) {
			NIVision.IMAQdxCloseCamera(session);
			session = NIVision.IMAQdxOpenCamera("cam0",//right
	                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	        NIVision.IMAQdxConfigureGrab(session);
		}else if(controller.getRawButton(3)) {
			NIVision.IMAQdxCloseCamera(session);
			session = NIVision.IMAQdxOpenCamera("cam1",
	                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	        NIVision.IMAQdxConfigureGrab(session);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		camEnabled = true;
		camUpdater();
		
	}
}
