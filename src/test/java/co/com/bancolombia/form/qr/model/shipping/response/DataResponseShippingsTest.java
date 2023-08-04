package co.com.bancolombia.form.qr.model.shipping.response;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.model.Error;
import co.com.bancolombia.form.qr.model.Meta;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataResponseShippings.class)  
public class DataResponseShippingsTest {
	
	@Mock
	private List<DataResponseShipping> data;
	
	@Mock
	private List<Error> errors;
	
	@Mock
	private Meta meta;
	
	@Autowired
	private DataResponseShippings actual;
	
	@Before
	public void setUp() {
		actual.setData(data);
		actual.setErrors(errors);
		actual.setMeta(meta);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(String.format("DataResponseShipping [meta=%s, data=%s, errors=%s]", actual.getMeta(), actual.getData(), actual.getErrors()), actual.toString());
	}

}
