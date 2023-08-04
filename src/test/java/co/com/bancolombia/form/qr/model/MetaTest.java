package co.com.bancolombia.form.qr.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Meta.class)
public class MetaTest {
	
	private final String clientRequest = "clientRequest";
	private final String messageId = "messageId";
	private final String requestDate = "requestDate";
	private final String responseSize = "responseSize";
	private final String version = "version";
	
	private Meta actual;
	
	@Before
	public void setUp() {
		actual = new Meta();
		actual.setClientRequest(clientRequest);
		actual.setMessageId(messageId);
		actual.setRequestDate(requestDate);
		actual.setResponseSize(responseSize);
		actual.setVersion(version);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(String.format("Meta [messageId=%s, version=%s, requestDate=%s, responseSize=%s, clientRequest=%s]", actual.getMessageId(), actual.getVersion(), actual.getRequestDate(), actual.getResponseSize(), actual.getClientRequest()), actual.toString());
	}

}
