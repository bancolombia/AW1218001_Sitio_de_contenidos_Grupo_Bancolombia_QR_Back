package co.com.bancolombia.form.qr.model.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.model.DeliveryInformation;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSendQrResponse.class)
public class DataSendQrResponseTest {
	
	private final String qrId = "qrId";
	private final String requestShippingId = "requestShippingId";
	
	@Mock
	private GeneratedFiles generatedFiles;
	
	@Mock
	private DeliveryInformation deliveryInformation;
	
	private DataSendQrResponse actual;

	@Before
	public void setUp() {
		actual = new DataSendQrResponse();
		actual.setDeliveryInformation(deliveryInformation);
		actual.setGeneratedFiles(generatedFiles);
		actual.setQrId(qrId);
		actual.setRequestShippingId(requestShippingId);
	}
	
	@Test
	public void toStringOkTest() {
		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("qrId: %s%n", actual.getQrId()));
		expected.append(String.format("requestShippingId: %s%n", actual.getRequestShippingId()));
		expected.append(String.format("Generated files: %s%n", actual.getGeneratedFiles()));
		expected.append(String.format("Delivery information: %s%n", actual.getDeliveryInformation()));
		expected.append("*****************************");
		
		assertEquals(expected.toString(), actual.toString());
	}

}
