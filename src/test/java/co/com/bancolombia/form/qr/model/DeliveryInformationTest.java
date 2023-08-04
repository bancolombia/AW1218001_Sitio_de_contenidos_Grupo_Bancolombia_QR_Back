package co.com.bancolombia.form.qr.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DeliveryInformation.class)
public class DeliveryInformationTest {

	private final String address = "address";
	private final String city = "city";
	private final String contactPhone = "contactPhone";
	private final String deliveryTime = "deliveryTime";
	private final String department = "department";
	private final int qrQuantity = 1;

	private DeliveryInformation actual;

	@Before
	public void setUp() {
		actual = new DeliveryInformation();
		actual.setAddress(address);
		actual.setCity(city);
		actual.setContactPhone(contactPhone);
		actual.setDeliveryTime(deliveryTime);
		actual.setDepartment(department);
		actual.setQrQuantity(qrQuantity);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(String.format(
				"DeliveryInformation [qrQuantity=%s, department=%s, city=%s, address=%s, contactPhone=%s, deliveryTime=%s]",
				actual.getQrQuantity(), actual.getDepartment(), actual.getCity(), actual.getAddress(),
				actual.getContactPhone(), actual.getDeliveryTime()), actual.toString());
	}

}
