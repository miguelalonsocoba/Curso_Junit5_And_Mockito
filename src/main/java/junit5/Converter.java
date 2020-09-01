package junit5;

/**
 * Clase que se encargara de convertir grados celsius a grados fahrenheit.
 * 
 * @author ACER 141119
 *
 */
public class Converter {

	public float toFahrenheit(float degree) {
		return (degree * 9 / 5) + 32;
	}
}
