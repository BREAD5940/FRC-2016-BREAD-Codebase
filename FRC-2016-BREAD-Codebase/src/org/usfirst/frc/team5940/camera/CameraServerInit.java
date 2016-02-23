package org.usfirst.frc.team5940.camera;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.State;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.OverlayTextOptions;
import com.ni.vision.NIVision.Point;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class CameraServerInit extends State {
	float val = 0;
	
	/**
	 * Constructor
	 * @param robot RobotBase for super
	 */
    public CameraServerInit(RobotBase robot) {
		super(robot);
	}
    
    int session;
    Image frame;
    
    /**
     * Sets up camera session, frame, and grabbing
     */
	@Override
	protected void init() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
		
	}

	
	/**
	 * Contains all the normal code in Intermediate vision with drawing and overlays changed. Note, only works in operator control.
	 */
	@Override
	protected void update() {
		NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

        while (robot.isOperatorControl() && robot.isEnabled()) {

            NIVision.IMAQdxGrab(session, frame, 1);
            //NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
            
            //NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, new Point(50, 50), new Point(100, 100), Float.MAX_VALUE);
            /*
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);*/
            
            drawAiming();
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            Timer.delay(0.005);		// wait for a motor update time
        }
        NIVision.IMAQdxStopAcquisition(session);
		
	}
	
	/**
	 * Overlays a triangle that helps with aiming and changes color based on ball detection.
	 */
	private void drawAiming() {
		float color = Float.MAX_VALUE;
		if(Components.getCorrectedDetector()) color = 255;
		
		NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, new Point(140, 480), new Point(320, 215), color);
		NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, new Point(640-140, 480), new Point(320, 215), color);
	}


}
