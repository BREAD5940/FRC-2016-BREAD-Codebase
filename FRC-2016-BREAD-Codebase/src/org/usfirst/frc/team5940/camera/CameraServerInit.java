package org.usfirst.frc.team5940.camera;

import org.usfirst.frc.team5940.states.State;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotBase;

public class CameraServerInit extends State {

	public CameraServerInit(RobotBase robot) {
		super(robot);
		
		
	}
	//TODO check if losing refence to this stops server.
	CameraServer usbcamera;
	@Override
	protected void init() {
		// TODO Auto-generated method stub

		usbcamera = CameraServer.getInstance();
		usbcamera.setQuality(50);
		usbcamera.setSize(1000);
		usbcamera.startAutomaticCapture("cam0");
	}

	@Override
	protected void update() {
		
	}

}
