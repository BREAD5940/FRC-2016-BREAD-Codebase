package org.usfirst.frc.team5940.camera;

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
    int session;
    Image frame;
    int working = 0;

	public CameraServerInit(RobotBase robot) {
		super(robot);
	}

	@Override
	protected void init() {
		SmartDashboard.putBoolean("cam", false);
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
	}

	@Override
	protected void update() {
    	SmartDashboard.putNumber("Cameraw2", 42);
		NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

        while (!Thread.interrupted()) {
        	SmartDashboard.putNumber("Cameraw2", working++);
        	
        	
            NIVision.IMAQdxGrab(session, frame, 1);
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
            		DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
            
            NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, new Point(50, 50), new Point(100, 100), Float.MAX_VALUE);
            /*
            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);*/
            
            CameraServer.getInstance().setImage(frame);

            /** robot code here! **/
            Timer.delay(0.005);		// wait for a motor update time
        }
        NIVision.IMAQdxStopAcquisition(session);
		
	}


}
