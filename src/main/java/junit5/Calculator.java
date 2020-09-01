package junit5;

/**
 * Class Calculator.
 */
public class Calculator {

	/**
	 * // METODO ////////////////////////////////////////// ESPECIFICACIOM //////
	 * //////////////////////////////////////////////////// ////////////////////////
	 * int sumar (| Este método devuelve un int resultado de la suma de numero 1 y
	 * numero2 int numero1, | int numero2) |
	 * --------------------------------------------------
	 * -------------------------------------------------- -------------------- int
	 * restar (| Este método devuelve un int resultado de la resta de numero 1 y
	 * numero2 int numero1, | int numero2) |
	 * --------------------------------------------------
	 * -------------------------------------------------- --------------------
	 */

	/** The result. */
	private Integer result;

	public Integer add(final Integer n1, final Integer n2) {
		result = n1 + n2;
		return result;
	}

	public Integer subtract(final Integer n1, final Integer n2) {
		result = n1 - n2;
		return result;
	}

	public Integer divide(final Integer n1, final Integer n2) {
		result = n1 / n2;
		return result;
	}

	public Integer divideByZero(final Integer n1, final Integer n2) {
		if (n2 == 0) {
			throw new ArithmeticException("No se puede dividir por cero");
		}
		result = n1 + n2;
		return result;
	}

	public void longTaskOperation() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
