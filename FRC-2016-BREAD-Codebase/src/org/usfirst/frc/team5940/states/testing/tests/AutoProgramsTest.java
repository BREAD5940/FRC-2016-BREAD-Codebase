package org.usfirst.frc.team5940.states.testing.tests;

import org.usfirst.frc.team5940.management.Components;
import org.usfirst.frc.team5940.states.auto.Breach;
import org.usfirst.frc.team5940.states.auto.BreachAndComeBack;
import org.usfirst.frc.team5940.states.auto.BreachAndPass;
import org.usfirst.frc.team5940.states.auto.BreachAndPassAndComeBack;
import org.usfirst.frc.team5940.states.auto.DoubleBreach;
import org.usfirst.frc.team5940.states.auto.DoubleBreachAndPass;
import org.usfirst.frc.team5940.states.testing.Test;
import org.usfirst.frc.team5940.states.testing.TestingStandardState;

public class AutoProgramsTest implements Test {

	@Override
	public String getName() {
		return "Auto Programs";
	}

	@Override
	public void runTest() {
		Thread t;
		
		TestingStandardState.updateStatus("Starting Breach");
		t = new Thread(new Breach(Components.robot));
		t.start();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestingStandardState.updateStatus("15 Seconds Up!!!");
		while(!t.interrupted());
		TestingStandardState.pause(1000);
		
		TestingStandardState.updateStatus("Starting Breach and Come Back");
		t = new Thread(new BreachAndComeBack(Components.robot));
		t.start();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestingStandardState.updateStatus("15 Seconds Up!!!");
		while(!t.interrupted());
		TestingStandardState.pause(1000);
		
		TestingStandardState.updateStatus("Starting Breach and Pass");
		t = new Thread(new BreachAndPass(Components.robot));
		t.start();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestingStandardState.updateStatus("15 Seconds Up!!!");
		while(!t.interrupted());
		TestingStandardState.pause(1000);
		
		TestingStandardState.updateStatus("Starting Breach and Pass and Come Back");
		t = new Thread(new BreachAndPassAndComeBack(Components.robot));
		t.start();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestingStandardState.updateStatus("15 Seconds Up!!!");
		while(!t.interrupted());
		TestingStandardState.pause(1000);
		
		TestingStandardState.updateStatus("Starting Double Breach");
		t = new Thread(new DoubleBreach(Components.robot));
		t.start();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestingStandardState.updateStatus("15 Seconds Up!!!");
		while(!t.interrupted());
		TestingStandardState.pause(1000);
		
		TestingStandardState.updateStatus("Starting Double Breach and Pass");
		t = new Thread(new DoubleBreachAndPass(Components.robot));
		t.start();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestingStandardState.updateStatus("15 Seconds Up!!!");
		while(!t.interrupted());
		TestingStandardState.pause(1000);
	}
}
