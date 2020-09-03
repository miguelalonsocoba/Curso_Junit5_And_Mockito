package mockito;

/**
 * Class ValidNumber.
 */
public class ValidNumber {

	/**
	 * Consturctor por defecto.
	 */
	public ValidNumber() {
		// Contructro por Defecto
	}

	/**
	 * Comprueba que el objeto se de tipo Integer.
	 * 
	 * @param o Object
	 * @return boolean
	 */
	public boolean check(Object o) {
		if (o instanceof Integer) {
			if ((Integer) o < 10 && (Integer) o >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
