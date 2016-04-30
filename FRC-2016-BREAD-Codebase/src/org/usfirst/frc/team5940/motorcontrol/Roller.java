package org.usfirst.frc.team5940.motorcontrol;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.motorcontrol.groups.MotorGroup;

public class Roller {
	
	MotorGroup roller;
	
	public Roller(MotorGroup roller) {
		this.roller = roller;
	}
	
	public void setValue(float value) {
		this.roller.setValue(value);
	}
	
	public void pass() {
		while(Components.getCorrectedDetector()) {
			roller.setValue(1f);
			try { Thread.sleep(50); } catch (InterruptedException e) { }
		}
		
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
		}
		
		roller.setValue(0.15f);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		roller.setValue(0);
				
	}
}
