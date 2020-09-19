package mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WebServiceTest {

	@InjectMocks
	private WebService webService;

	@Mock
	private Callback callback;

	@BeforeEach
	public void setUp() {
		webService = new WebService();
		MockitoAnnotations.initMocks(this);// Instanicar todos los objetos con la etiqueta callback
	}

	@Test
	public void checkLoginTestOk() {
		assertTrue(webService.checkLogin("Alberto", "1234"));
	}

	@Test
	public void checkLoginErrorTest() {
		assertFalse(webService.checkLogin("Juan", "12345"));
	}

	@Test
	public void loginTest() {
		webService.login("Alberto", "1234", callback);
		verify(callback).onSuccess("Usuario correcto");
	}
	
	@Test
	public void loginErrorTest() {
		webService.login("Juan", "12we", callback);
		verify(callback).onFail("Error");
	}

}
