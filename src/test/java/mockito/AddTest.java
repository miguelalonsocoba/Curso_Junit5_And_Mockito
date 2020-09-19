package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AddTest {

	@InjectMocks
	private Add add;

	@Mock
	private ValidNumber validNumber;

	@Mock
	private Print print;

	@Captor
	private ArgumentCaptor<Integer> captor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addTestOk() {
		when(validNumber.check(3)).thenReturn(true);
		boolean checkNumber = validNumber.check(3);
		assertEquals(true, checkNumber, "El valor es true");

		when(validNumber.check("a")).thenReturn(false);
		checkNumber = validNumber.check("a");
		assertEquals(false, checkNumber, "The response is false");
	}

	@Test
	public void addMockExceptionTest() {
		when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero."));
		Exception exception = null;
		try {
			validNumber.checkZero(0);
		} catch (ArithmeticException e) {
			exception = e;
		}
		assertNotNull(exception, "The object is not null...");
	}

	/**
	 * Se ejecuta un metodo real al ejecutar el test.
	 */
	@Test
	public void addRealMethodTest() {
		when(validNumber.check(3)).thenCallRealMethod();
		assertEquals(true, validNumber.check(3));

		when(validNumber.check(15)).thenCallRealMethod();
		assertEquals(false, validNumber.check(15));

		when(validNumber.check("3")).thenCallRealMethod();
		assertEquals(false, validNumber.check("3"));
	}

	/**
	 * Se utiliza mockito para devolver una respuesta Then-Answer.
	 */
	@Test
	public void addDoubleToIntThenAnswerTest() {
		Answer<Integer> answer = new Answer<Integer>() {

			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return 7;
			}
		};

		when(validNumber.doubleToInt(7.7)).thenAnswer(answer);

		assertEquals(14, add.addInt(7.7));
	}

	// Patron de Pruebas para Mock.
	/**
	 * 1.- Arrange - Organizar 2.- Actuacion - Actuación. 3.- Assert - afirmación.
	 *
	 * ---------------------------
	 *
	 * 1.- Given. 2.- When. 3.- Then.
	 */

	@Test
	public void patterTest() {
		// Arrange.
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);

		// Actuacion.
		int result = add.add(4, 5);

		// Assert.
		assertEquals(9, result, "The response is not 9.");

	}

	@Test
	public void testWithArgumentMatcher() {
		// 1.- Arrange - Organizar
		when(validNumber.check(ArgumentMatchers.anyInt())).thenReturn(true);

		// 2.- Actuacion - Actuación.
		int result = add.add(4, 6);

		// 3.- Assert - afirmación.
		assertEquals(10, result, "The result is not 10");
	}

	/**
	 * Verificar que validNumber.check(4), se ejecuta dos veces.
	 */
	@Test
	public void addPrintTest() {
		// 1.- Arrange - Organizar
		when(validNumber.check(4)).thenReturn(true);

		// 2.- Actuacion - Actuación.
		add.addPrint(4, 4);

		// 3.- Assert - afirmación.
//		Mockito.verify(validNumber.check(4));
		verify(validNumber, times(2)).check(4);

	}

	/**
	 * Verifica que validNumber.check(4) se ejecuta una sola vez.
	 */
	@Test
	public void addPrintTest2() {
		// 1.- Arrange - Organizar
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);

		// 2.- Actuacion - Actuación.
		add.addPrint(4, 5);

		// 3.- Assert - afirmación.
		verify(validNumber).check(4);

	}

	/**
	 * Verifica que validNumber.check(99) no se ejecuta ni una sola vez..
	 */
	@Test
	public void addPrintTest3() {
		// 1.- Arrange - Organizar
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);

		// 2.- Actuacion - Actuación.
		add.addPrint(4, 5);

		// 3.- Assert - afirmación.
		verify(validNumber, never()).check(99);

	}

	/**
	 * Verifica que validNumber.check(99) se ejecuta al menos una vez.
	 */
	@Test
	public void addPrintTest4() {
		// 1.- Arrange - Organizar
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);

		// 2.- Actuacion - Actuación.
		add.addPrint(4, 5);

		// 3.- Assert - afirmación.
		verify(validNumber, atLeast(1)).check(4);

	}

	/**
	 * Verifica que validNumber.check(4) se ejecuta como maximo el numeor que se
	 * indique.
	 */
	@Test
	public void addPrintTest5() {
		// 1.- Arrange - Organizar
		when(validNumber.check(4)).thenReturn(true);

		// 2.- Actuacion - Actuación.
		add.addPrint(4, 5);

		// 3.- Assert - afirmación.
		verify(validNumber, atMost(2)).check(4);

	}

	@Test
	public void addPrintVerifyTest() {
		// 1.- Arrange - Organizar.
		when(validNumber.check(4)).thenReturn(true);

		// 2.- Actuacion - Actuación.
		add.addPrint(4, 4);

		// 3.- Assert - Afirmación.
//		verify(print).showMessage(ArgumentMatchers.anyInt());
		verify(print).showMessage(8);
		verify(print, never()).showError();
	}

	@Test
	public void addPrintErrorTest() {
		when(validNumber.check(ArgumentMatchers.anyInt())).thenReturn(false);

		add.addPrint(4, 4);

		verify(print).showError();
		verify(print, never()).showMessage(ArgumentMatchers.anyInt());
	}

	@Test
	public void captorTest() {
		// 1.- Arrange - Organizar
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);

		// 2.- Actuacion - Actuación.
		add.addPrint(4, 5);

		// 3.- Assert - afirmación.
		verify(print).showMessage(captor.capture());
		assertEquals(captor.getValue().intValue(), 9);
	}

}
