package org.usfirst.frc.team5940.usefulStuff;

public class InputCuber {
	public double exp( double input, double power) {
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
