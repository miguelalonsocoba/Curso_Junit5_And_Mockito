package junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * TestClass CalculatorTest.
 */
public class CalculatorTest {

	private Calculator calculator;
	private Calculator calculatorNull;
	private static Calculator calculatorStatic;

	/*
	 * //METODO////////////////////////////////////////ESPECIFICACIOM///////////////
	 * /////////////////////////////////////////////////////////////// int sumar(
	 * |Este método devuelve un int resultado de la suma de numero 1 y numero2 int
	 * numero1, | int numero2) |
	 * -----------------------------------------------------------------------------
	 * ------------------------------------------- int restar( |Este método devuelve
	 * un int resultado de la resta de numero 1 y numero2 int numero1, | int
	 * numero2) |
	 * 
	 * 
	 * Método a Probar | Entrada | Salida Esperada sumar(int a, int b) |a = 10, b=20
	 * |30 sumar(int a, int b) |a = 7, b=4 |11 restar(int a, int b) |a = 7, b=4 |3
	 * restar(int a, int b) |a = 10, b=20 |-10
	 */

	// Se ejecuta solo una ves al principio de la clase.
	@BeforeAll
	public static void beforeAllTest() {
		calculatorStatic = new Calculator();
		System.out.println("@BeforeAll -> beforeAllTest()");
	}

	/**
	 * Sirve para inicializar recursos.
	 */
	@BeforeEach
	public void setUp() {
		calculator = new Calculator();
		System.out.println("@BeforeEch - setUp()");
	}

	/**
	 * Sirve para demoler o cerrar recursos.
	 */
	@AfterEach
	public void tearDown() {
		calculator = null;
		System.out.println("@AfterEach - tearDown()");
	}

	@AfterAll
	public static void afterAllTest() {
		calculatorStatic = null;
		System.out.println("@AfterAll -> afterAllTest()");
	}

	@Test
	public void calculatorNotNullTest() {
		assertNotNull(calculatorStatic, "Calculator debe ser not null");
		assertNotNull(calculator, "Calculator debe ser not null");
		System.out.println("@Test - calculatorNotNullTest()");
	}

	@Test
	public void calculatorNullTest() {
		assertNull(calculatorNull);
		System.out.println("@Test - calculatorNullTest");
	}

	@Test
	public void addAssertTest() {
		// 1.- SetUp
		Calculator calculatorAssert = new Calculator();
		Integer resultadoEsperado = 30;
		Integer resultadoActual;

		// 2.- Action
		resultadoActual = calculatorAssert.add(10, 20);

		// 3.- Assert
		assertEquals(resultadoEsperado, resultadoActual);
		System.out.println("@Test - addAssertTest()");
	}

	@Test
	public void addTest() {
		assertEquals(30, calculator.add(10, 20));
		System.out.println("@Test - addTest()");
	}

	@Test
	public void assertTypesTest() {
//		assertTrue(1==1);

		Calculator calculator = new Calculator();
		Calculator calculator2 = new Calculator();
		Calculator calculator3 = calculator;

		assertSame(calculator3, calculator);
		assertNotSame(calculator, calculator2);

		assertEquals("Hola", "Hola");
//		assertEquals("Message", "Messag", "Ha fallado el metodo String");

		// Permite en el tercer parametro manejar un rango de diferencia (delta).
		assertEquals(1, 1.4, 0.5);
		// assertEquals(1, 1.6, 0.5);
	}

	@Test
	public void add_ValidInput_Valid_Excpected_Test() {
		assertEquals(11, calculator.add(7, 4));
	}

	@Test
	public void subtract_ValidInput_Valid_Excpected_Test() {
		assertEquals(11, calculator.subtract(15, 4));
	}

	@Test
	public void subtract_ValidInput_ValidNegativeResultExcpected_Test() {
		assertEquals(-10, calculator.subtract(10, 20));
	}

	@Test
	public void divide_ValidInput_ValidResultExcpected_Test() {
		assertEquals(2, calculator.divide(10, 5));
	}

	@Test
	public void divide_InValidInput_Test() {
		fail("Fallo detectaado manualmente. - No se puede dividir por cero");
		calculator.divide(10, 0);
	}

	@Test
	public void divide_InvalidInput_ExpectedException_Test() {
		assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0), "No se puede dividir por cero.");
	}

	@Disabled("Disable until bug 23 be resolved.")
	@Test
	public void divide_InvalidInput_Test() {
		assertEquals(2, calculator.divide(5, 0));
	}

	@Test
	@DisplayName("Metodo Dividir -> Funciona") // Agrega un nombre al metodo Test.
	public void divide_ValidInput_ValidResultExcpected_Name_Test() {
		assertEquals(2, calculator.divide(10, 5));
	}

	// Se ejecutan todos los assert aunque falle uno de los assert.
	@Test
	public void add_Assert_All_Test() {
		assertAll(() -> assertEquals(31, calculator.add(11, 20)), () -> assertEquals(20, calculator.add(10, 20)),
				() -> assertEquals(2, calculator.add(1, 1)));
	}

	// Engloba todos los test que son de un solo metodo.
	@Nested
	class AddTest {
		@Test
		public void add_Positive_Test() {
			assertEquals(30, calculator.add(15, 15));
		}

		@Test
		public void add_Negative_Test() {
			assertEquals(-30, calculator.add(-15, -15));
		}

		@Test
		public void add_Zero_Test() {
			assertEquals(0, calculator.add(0, 0));
		}
	}

	/**
	 * Ejemplo en nuestra división queremos hacer 5 pruebas. Positivo / Positivo =
	 * Positivo Positivo / Negativo = Negativo Negativo / Positivo = Negativo
	 * Negativo / Negativo = Positivo Positivo / 0 = Exception Método a Probar |
	 * Entrada | Salida Esperada dividir(int a, int b) | a = 6, b = 2 | 3
	 * dividir(int a, int b) | a = 6, b = -2 | -3 dividir(int a, int b) | a = -6, b
	 * = 2 | -3 dividir(int a, int b) | a = -6, b = -2 | 3 dividir(int a, int b) | a
	 * = -6, b = 0 | Exception
	 */

	@ParameterizedTest(name = "{index} => a= {0}, b= {1}, sum= {2}")
	@MethodSource(value = "addProviderData")
	public void addParameterizedTest(int a, int b, int sum) {
		assertEquals(sum, calculator.divide(a, b));
	}

	private static Stream<Arguments> addProviderData() {
		return Stream.of(Arguments.of(6, 2, 3), Arguments.of(6, -2, -3), Arguments.of(-6, 2, -3),
				Arguments.of(-6, -2, 3));

	}

	// Testear tiempos de respuesta...
	@Test
	public void timeOutTest() {
		assertTimeout(Duration.ofMillis(2000), () -> calculator.longTaskOperation());
	}

}
