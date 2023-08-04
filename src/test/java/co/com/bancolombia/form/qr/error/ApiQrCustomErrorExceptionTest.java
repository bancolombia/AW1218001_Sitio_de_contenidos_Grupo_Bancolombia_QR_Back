package co.com.bancolombia.form.qr.error;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApiQrCustomErrorException.class)
public class ApiQrCustomErrorExceptionTest {
	
	private ApiQrCustomErrorException actual;

	private final String MESSAGE = "Any message";

	@Test
	public void exceptionOkTest() {
		actual = new ApiQrCustomErrorException();
		assertNull(actual.getMessage());
	}
	
	@Test
	public void exceptionOkMessageThrowableTest() {
		actual = new ApiQrCustomErrorException(MESSAGE, new Throwable());
		assertEquals(MESSAGE, actual.getMessage());
	}
	
	@Test
	public void exceptionOkMessageTest() {
		actual = new ApiQrCustomErrorException(MESSAGE);
		assertEquals(MESSAGE, actual.getMessage());
	}
	
	@Test
	public void exceptionOkThrowableTest() {
		actual = new ApiQrCustomErrorException(new Throwable(MESSAGE));
		assertEquals(MESSAGE, actual.getCause().getMessage());
	}

}
