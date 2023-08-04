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

import co.com.bancolombia.form.qr.model.DeliveryInformation;
import co.com.bancolombia.form.qr.model.HabeasData;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RequestShipping.class)
public class RequestShippingTest {

	private final String channel = "channel";
	private final String qrId = "qrId";
	private final String type = "type";
	
	@Mock
	private List<DeliveryInformation> deliveryInformation;
	
	@Mock
	private HabeasData habeasData;
	
	private RequestShipping actual;
	
	@Before
	public void setUp() {
		actual = new RequestShipping();
		actual.setChannel(channel);
		actual.setQrId(qrId);
		actual.setType(type);
		actual.setDeliveryInformation(deliveryInformation);
		actual.setHabeasData(habeasData);
	}
	
	@Test
	public void test() {
		assertEquals(String.format("RequestShipping [channel=%s, qrId=%s, type=%s, deliveryInformation=%s, habeasData=%s]", actual.getChannel(), actual.getQrId(), actual.getType(), actual.getDeliveryInformation(), actual.getHabeasData()), actual.toString());
	}

}
