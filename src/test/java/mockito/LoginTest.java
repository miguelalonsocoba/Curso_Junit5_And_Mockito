package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class LoginTest {

	@InjectMocks
	private Login login;

	@Mock
	private WebService webService;
	
	@Captor
	private ArgumentCaptor<Callback> callbackArgumentCaptor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	// Se testea el metodo doLogin del objeto login.
	@Test
	public void doLoginOkTest() {
		doAnswer(new Answer() { // Se trabaja la respuesta que debolvera el objeto mockeado. Se recupera los argumentos reales del metodo
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
				String user = (String) invocationOnMock.getArguments()[0];
				assertEquals("Alberto", user);
				String password = (String) invocationOnMock.getArguments()[1];
				assertEquals("12345", password);
				Callback callback = (Callback) invocationOnMock.getArguments()[2];
				callback.onSuccess("OK");
				return null;
			}
		}).when(webService).login(anyString(), anyString(), any(Callback.class));

		login.doLogin();// Se llama al metodo.
		verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));// Se verifica que se llama al
																							// metodo login del objeto
																							// webServices una vez
		assertEquals(login.isLogin, true);
	}
	
	// Se testea el metodo doLogin del objeto login.
		@Test
		public void doLoginErrorTest() {
			doAnswer(new Answer() { // Se trabaja la respuesta que debolvera el objeto mockeado. Se recupera los argumentos reales del metodo
				@Override
				public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
					String user = (String) invocationOnMock.getArguments()[0];
					assertEquals("Alberto", user);
					String password = (String) invocationOnMock.getArguments()[1];
					assertEquals("12345", password);
					Callback callback = (Callback) invocationOnMock.getArguments()[2];
					callback.onFail("Error");
					return null;
				}
			}).when(webService).login(anyString(), anyString(), any(Callback.class));

			login.doLogin();// Se llama al metodo.
			verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));// Se verifica que se llama al
																								// metodo login del objeto
																								// webServices una vez
			assertEquals(login.isLogin, false);
		}
		
		@Test
		public void doLoginCaptorTest() {
			login.doLogin();
			verify(webService, times(1)).login(anyString(), anyString(), callbackArgumentCaptor.capture());
			assertEquals(login.isLogin, false);
			
			Callback callback = callbackArgumentCaptor.getValue();
			callback.onSuccess("Ok");
			assertEquals(login.isLogin, true);
			
			callback.onFail("Error");
			assertEquals(login.isLogin, false);
		}

}
