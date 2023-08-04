package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SQSConfig.class)
@TestPropertySource(properties = { "spring.profiles.active=" })
public class SQSConfigTest {
	
	@Autowired
	private SQSConfig sqsConfig;

	@Test
	public void sqsConfigLocalTest() {
		assertNotNull(sqsConfig);
	}

}
