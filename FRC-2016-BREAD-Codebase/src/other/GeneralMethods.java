package other;

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
}
