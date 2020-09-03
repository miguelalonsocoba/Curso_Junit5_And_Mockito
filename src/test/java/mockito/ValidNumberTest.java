package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

}
