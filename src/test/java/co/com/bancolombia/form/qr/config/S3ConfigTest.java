package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = S3Config.class)
@TestPropertySource(properties = { "spring.profiles.active=" })
public class S3ConfigTest {

	
    @MockBean
	private BucketSettings bucketSettings;
	
    @InjectMocks
    @Autowired
	private S3Config s3Config;

	@Test
	public void notNullOkTest() {
		assertNotNull(s3Config);
	}

}
