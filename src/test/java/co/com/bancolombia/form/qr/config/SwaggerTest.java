package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.*;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Swagger.class)
public class SwaggerTest {

	@InjectMocks
	@Autowired
	private Swagger swaggerConfig;

	@Test
	public void nullNotTest() {
		OpenAPI actual = swaggerConfig.customOpenAPI();
		assertNotNull(actual);
	}

}
