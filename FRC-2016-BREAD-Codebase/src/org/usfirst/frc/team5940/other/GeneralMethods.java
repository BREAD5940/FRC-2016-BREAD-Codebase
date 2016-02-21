package org.usfirst.frc.team5940.other;

import java.lang.reflect.Array;

public class GeneralMethods {
	/**
	 * Bounds something to a unit vector, removing excess over 1 or -1.
	 * 
	 * @param in
	 *            The amount in
	 * @return result THe result
	 */
	public static double boundToUnitVector(double in) {
		// Check if above 1 or below -1
		if (in < -1)
			in = -1;
		if (in > 1)
			in = 1;
		// Returns result
		return in;
	}

	public static float[] lowerToNumber(float firstNumber, float secondNumber) {
		float[] numbers = new float[2];
		if (firstNumber > 1 && firstNumber >= secondNumber || firstNumber < -1 && firstNumber <= secondNumber) {
			firstNumber /= Math.abs(firstNumber);
			secondNumber /= Math.abs(firstNumber);
		} else if (secondNumber > 1 || secondNumber < -1) {
			firstNumber /= Math.abs(secondNumber);
			secondNumber /= Math.abs(secondNumber);
		}
		Array.setFloat(numbers, 0, firstNumber);
		Array.setFloat(numbers, 1, secondNumber);
		return numbers;
	}
}
