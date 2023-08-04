package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SecretsManagerProperty.class)
public class SecretsManagerSettingsTest {
	
	public static String TEXT = "text";
	
	private SecretsManagerProperty actual;
	
	@Before
	public void setUp() {
		actual = new SecretsManagerProperty();
		actual.setEndPoint(TEXT);
		actual.setSecretName(TEXT);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(TEXT, actual.getEndPoint());
		assertEquals(TEXT, actual.getSecretName());
	}

}
