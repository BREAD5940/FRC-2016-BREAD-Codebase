package other;

public class GeneralMethods {
	public static double powInputFixed(double input, double power) {
		boolean negative = false;
		
		if(input<0){
			negative=true;
			input *=-1;
		}
		
		input=Math.pow(input, power);
		
		if(negative)
			input*=-1;
		return input;
	}
	
	public static double boundToUnitVector(double in) {
		if(in < -1) in = -1;
		if(in > 1) in = 1;
		return in;
	}
}
