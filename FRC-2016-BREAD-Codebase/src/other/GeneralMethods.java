package other;

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
	 * @return result The result
	*/
	public static double boundToUnitVector(double in) {
		//Check if above 1 or below -1
		if (in < -1) in = -1;
		if (in > 1) in = 1;
		//Returns result
		return in;
	}
	/**Sets a float array to two numbers, scaling as such, used in arcade driving
	 * @param firstNumber The first number
	 * @param secondNumber The second number
	 * @param maxNumber The maximum number, doesn't do anything
	 * @return numbers the number
	*/
	public static float[] lowerToNumber(float firstNumber, float secondNumber, float maxNumber) {
		//Makes a float array to use
		float[] numbers = new float[2];
		//Checks if the first number are greater than one or the first number is greater than or equal to the second number
		if (firstNumber > 1 && firstNumber >= secondNumber) {
			//Devides both numbers by the first number
			firstNumber /= firstNumber;
			secondNumber /= firstNumber;
		}
		//Checks if the second number is greater than 1
		else if (secondNumber > 1) {
			//Devides both numbers by the second number
			firstNumber /= secondNumber;
			secondNumber /= secondNumber;
		}
		//Sets the floats
		Array.setFloat(numbers, 0, firstNumber);
		Array.setFloat(numbers, 1, secondNumber);
		return numbers;
	}
}
