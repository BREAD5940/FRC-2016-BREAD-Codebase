package org.usfirst.frc.team5940.other.copy;

import java.lang.reflect.Array;

public class GeneralMethods {
	/**Puts something to a power while saving the negative
	 * @param input The input
	 * @param power Power to put it to
	 * @return result THe result
	*/
	public static double powInputFixed(double input, double power) {
		boolean negative = false;
		//Save negativity
		if(input<0){
			negative=true;
			input *=-1;
		}
		//Remove negativity and do power
		input=Math.pow(input, power);
		//Add back in negativity
		if(negative)
			input*=-1;
		return input;
	}
	/**Bounds something to a unit vector, removing excess over 1 or -1.
	 * @param in The amount in
	 * @return result THe result
	*/
	public static double boundToUnitVector(double in) {
		//Check if above 1 or below -1
		if (in < -1) in = -1;
		if (in > 1) in = 1;
		//Returns result
		return in;
	}
	
	public static float[] lowerToNumber(float firstNumber, float secondNumber, float maxNumber) {
		float[] numbers = new float[2];
		if (firstNumber > 1 && firstNumber >= secondNumber) {
			firstNumber /= firstNumber;
			secondNumber /= firstNumber;
		}
		else if (secondNumber > 1) {
			firstNumber /= secondNumber;
			secondNumber /= secondNumber;
		}
		Array.setFloat(numbers, 0, firstNumber);
		Array.setFloat(numbers, 1, secondNumber);
		return numbers;
	}
}
