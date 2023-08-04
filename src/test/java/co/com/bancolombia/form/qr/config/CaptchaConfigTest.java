package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CaptchaConfig.class)
public class CaptchaConfigTest {
	
	@Autowired
	private CaptchaConfig config;
	
	@Test
	public void test() {
		ClientHttpRequestFactory expected = config.clientHttpRequestFactory();
		assertNotNull(expected);
	}

}
