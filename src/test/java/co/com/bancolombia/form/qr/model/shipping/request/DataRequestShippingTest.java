package co.com.bancolombia.form.qr.model.shipping.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataRequestShipping.class)
public class DataRequestShippingTest {
	
	private RequestShipping requestShipping; 
	
	private DataRequestShipping actual;

	@Before
	public void setUp() {
		
		requestShipping = new RequestShipping(); 
		
		actual = new DataRequestShipping();
		actual.setRequestShipping(requestShipping);
	}
	
	@Test
	public void toStringOkTest() {
		assertEquals(String.format("DataShipping [requestShipping=%s]", actual.getRequestShipping()), actual.toString());
	}

}
