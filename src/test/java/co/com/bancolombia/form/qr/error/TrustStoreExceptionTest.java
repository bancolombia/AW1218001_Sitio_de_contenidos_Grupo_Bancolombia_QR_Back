package co.com.bancolombia.form.qr.error;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrustStoreException.class)
public class TrustStoreExceptionTest {

	private TrustStoreException actual;

	private final String MESSAGE = "Any message";

	@Test
	public void exceptionOkTest() {
		actual = new TrustStoreException();
		assertNull(actual.getMessage());
	}
	
	@Test
	public void exceptionOkMessageThrowableTest() {
		actual = new TrustStoreException(MESSAGE, new Throwable());
		assertEquals(MESSAGE, actual.getMessage());
	}
	
	@Test
	public void exceptionOkMessageTest() {
		actual = new TrustStoreException(MESSAGE);
		assertEquals(MESSAGE, actual.getMessage());
	}
	
	@Test
	public void apiQrCustomErrorExceptionOkThrowableTest() {
		actual = new TrustStoreException(new Throwable(MESSAGE));
		assertEquals(MESSAGE, actual.getCause().getMessage());
	}
}
