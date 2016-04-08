package org.usfirst.frc.team5940.states.testing;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.State;
import org.usfirst.frc.team5940.states.auto.AutoSelector;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestingStandardState extends State {

	public static final String ALL_TESTS_NAME = "Systems Test";
	public static final Test[] registeredTests = {
		new AutoSelectorTest()
	};
	public static final float rightValue = 0.5f;
	public static final float leftValue = 0.5f;
	public static final float rollerValue = 0.5f;
	SendableChooser testChooser;
	
	public TestingStandardState(RobotBase robot) {
		super(robot);
		Components.setupTalons();
	}

	@Override
	protected void init() {
		testChooser = new SendableChooser();
		testChooser.addDefault("All of them", ALL_TESTS_NAME);
		
		for(int i = 0; i < registeredTests.length; i++) {
			Test curr = registeredTests[i];
			testChooser.addDefault(curr.getName(), curr.getName());
		}
		
		SmartDashboard.putData("Test Chooser", testChooser);
		
		
		this.updateStatus("Choose in 5...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		this.updateStatus("Choose in 4...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		this.updateStatus("Choose in 3...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		this.updateStatus("Choose in 2...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
		this.updateStatus("Choose in 1...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) { }
	}

	@Override
	protected void update() {
		String selected = (String) this.testChooser.getSelected();
		if(selected.equals(ALL_TESTS_NAME)) {
			for(int i = 0; i < registeredTests.length; i++) {
				runTest(registeredTests[i]);
				pause();
			}
		}else {
			Test chosen = null;
			for(int i = 0; i < registeredTests.length; i++) {
				if(selected.equals(registeredTests[i].getName())) chosen = registeredTests[i];
			}
			runTest(chosen);
			pause();
		}
		
		
	}
	
	static void runTest(Test test) {
		updateStatus("Running: " + test.getName());
		test.runTest();
	}
	
	static void pause() {
		updateStatus("Waiting...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
	
	public static void updateStatus(String status) {
		SmartDashboard.putString("Test Status", status);
	}
}
