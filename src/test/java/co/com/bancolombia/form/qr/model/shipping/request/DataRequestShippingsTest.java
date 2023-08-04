package co.com.bancolombia.form.qr.model.shipping.request;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes =  DataRequestShippings.class)
public class DataRequestShippingsTest {

	@Mock
	private List<DataRequestShipping> data;
	
	private DataRequestShippings actual;
	
	@Before
	public void setUp() {
		actual = new DataRequestShippings();
		actual.setData(data);
	}
	
	@Test
	public void toStringOkTest() {
		assertEquals(String.format("DataRequestShippings [data=%s]", actual.getData()), actual.toString());
	}

}
