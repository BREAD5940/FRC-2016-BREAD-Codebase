package other;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneralMethodsTest {

	@Test
	public void test() {
		double expected1 = -4;
		double expected2 = -1;
		double actual1 = GeneralMethods.powInputFixed(-2, 2);
		double actual2 = GeneralMethods.boundToUnitVector(-2);
		assertEquals(expected1, actual1, 0);
		assertEquals(expected2, actual2, 0);
	}

}
