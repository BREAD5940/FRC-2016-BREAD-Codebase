package org.usfirst.frc.team5940.motorcontrol;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.junit.Test;

import edu.wpi.first.wpilibj.CANTalon;

@SuppressWarnings("unused")
public class arcadeTest {

	@Test
	public void test() {
		float[] actual = DualMGDrivetrain.calculateStupidDrive(0, 1, 0.5, 0.75);
		float[] expected = new float[2];
		Array.setFloat(expected, 0, (float) -0.2);
		Array.setFloat(expected, 1, (float) 0.2);
		org.junit.Assert.assertArrayEquals("Did not return as expected", expected, actual, 0);
		float[] actual2 = DualMGDrivetrain.calculateArcade(0, 1, 0.5);
		float[] expected2 = new float[2];
		Array.setFloat(expected2, 0, (float) -0.1);
		Array.setFloat(expected2, 1, (float) 0.1);
		org.junit.Assert.assertArrayEquals("Did not return as expected", expected2, actual2, 0);
	}

}
