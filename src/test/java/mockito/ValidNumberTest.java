package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidNumberTest {

	private ValidNumber validNumber;

	/** Inicializa el Objeto. */
	@BeforeEach
	public void setUp() {
		validNumber = new ValidNumber();
	}

	@AfterEach
	public void tearDown() {
		validNumber = null;
	}

	@Test
	public void checkTrueTest() {
		assertEquals(true, validNumber.check(5));
	}

	@Test
	public void checkFalseTest() {
		assertEquals(false, validNumber.check(15));
	}

	@Test
	public void checkNoInstanceOfStringTest() {
		assertEquals(false, validNumber.check(34.9F));
	}

	@Test
	public void checkNoIntegerTest() {
		assertFalse(validNumber.check("Hola"));
	}

	@Test
	public void checkZeroTestOk() {
		assertEquals(true, validNumber.checkZero(56));
	}

	@Test
	public void checkZeroTestFalse() {
		assertEquals(false, validNumber.checkZero("Message"));
	}

	@Test
	public void checkZeroTestException() {
		assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
	}
	
	@Test
	public void doubleToIntOkTest() {
		assertEquals(3, validNumber.doubleToInt(3.0));
	}
	
	@Test
	public void doubleToIntErrorIntTest() {
		assertEquals(0, validNumber.doubleToInt(3));
	}
	
	@Test
	public void doubleToIntErrorStringTest() {
		assertEquals(0, validNumber.doubleToInt("value"));
	}
	
	@Test
	public void doubleToIntErrorBooleanTest() {
		assertEquals(0, validNumber.doubleToInt(true));
	}
	
	@Test
	public void doubleToIntErrorFloatTest() {
		assertEquals(0, validNumber.doubleToInt(3f));
	}

}
