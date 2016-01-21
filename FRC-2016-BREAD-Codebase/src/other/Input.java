package other;

public class Input {
	/**
	 * Takes an input and power and raises the
	 * input to the power perserving the negative.
	*/
	public double powInputFixed( double input, double power) {
		return (/*Power*/Math.pow(/*Make input not negetive*/Math.abs(input),power) * (/*If input is negative, make the whole thing negative*/(input < 0) ? -1:1)
	}
}
