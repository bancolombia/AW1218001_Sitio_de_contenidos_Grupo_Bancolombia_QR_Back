package co.com.bancolombia.form.qr.error;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RequestException.class)
public class RequestExceptionTest {

	private RequestException actual;

	private final String MESSAGE = "Any message";

	@Test
	public void exceptionOkTest() {
		actual = new RequestException();
		assertNull(actual.getMessage());
	}
	
	@Test
	public void exceptionOkMessageThrowableTest() {
		actual = new RequestException(MESSAGE, new Throwable());
		assertEquals(MESSAGE, actual.getMessage());
	}
	
	@Test
	public void exceptionOkMessageTest() {
		actual = new RequestException(MESSAGE);
		assertEquals(MESSAGE, actual.getMessage());
	}
	
	@Test
	public void exceptionOkThrowableTest() {
		actual = new RequestException(new Throwable(MESSAGE));
		assertEquals(MESSAGE, actual.getCause().getMessage());
	}

}
