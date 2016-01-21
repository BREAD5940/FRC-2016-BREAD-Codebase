package other;

public class Input {
	public double powInputFixed( double input, double power) {
		return (Math.pow(Math.abs(input),power)* ((input < 0) ? -1:1)
	}
}
