package co.com.bancolombia.form.qr.model.shipping.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.model.Header;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataResponseShipping.class)
public class DataResponseShippingTest {
	
	private final String address = "address";
	private final String city = "city";
	private final String contactPhone = "contactPhone";
	private final String deliveryTime = "deliveryTime";
	private final String department = "department";
	private final String idQR = "idQR";
	private final String idrequestShipping = "idrequestShipping";
	private final int qrQuantity = 1;
	
	@Autowired
	private DataResponseShipping actual;
	
	@Mock
	private Header header;
	
	@Before
	public void setUp() {
		actual.setAddress(address);
		actual.setCity(city);
		actual.setContactPhone(contactPhone);
		actual.setDeliveryTime(deliveryTime);
		actual.setDepartment(department);
		actual.setHeader(header);
		actual.setIdQR(idQR);
		actual.setIdrequestShipping(idrequestShipping);
		actual.setQrQuantity(qrQuantity);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(String.format(
				"DataShipping [header=%s, idrequestShipping=%s, idQR=%s, qrQuantity=%s, address=%s, department=%s, city=%s, deliveryTime=%s, contactPhone=%s]",
				actual.getHeader(), actual.getIdrequestShipping(), actual.getIdQR(), actual.getQrQuantity(), actual.getAddress(), actual.getDepartment(),
				actual.getCity(), actual.getDeliveryTime(), actual.getContactPhone()), actual.toString());
	}

}
