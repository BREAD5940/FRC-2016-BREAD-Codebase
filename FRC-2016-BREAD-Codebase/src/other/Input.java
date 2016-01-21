package other;

public class Input {
	public double powInputFixed( double input, double power) {
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
}
