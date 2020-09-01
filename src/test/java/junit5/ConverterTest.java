package junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConverterTest {
	
	private Converter converter;

	/**
	 * Sirve para inicializar recursos.
	 */
	@BeforeEach
	public void setUp() {
		converter = new Converter();
		System.out.println("@BeforeEch - setUp()");
	}

	/**
	 * Sirve para demoler o cerrar recursos.
	 */
	@AfterEach
	public void tearDown() {
		converter = null;
		System.out.println("@AfterEach - tearDown()");
	}
	
	// Se maneja un delta = 0.5 para que tome una diferencia en el resultado.
	@Test
	public void toFahrenheitTest() {
		assertEquals(-9.4, converter.toFahrenheit(-23), 0.5);
	}
	
}
