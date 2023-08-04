package co.com.bancolombia.form.qr.service;

import co.com.bancolombia.bancolombia_home_rest.HomeRest;
import co.com.bancolombia.bancolombia_home_rest.error.SqsMessageException;
import co.com.bancolombia.bancolombia_home_rest.model.Config;
import co.com.bancolombia.bancolombia_home_rest.structure.RequestOld;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;
import co.com.bancolombia.bancolombia_home_rest.structure.model.MetaRequestOld;
import co.com.bancolombia.bancolombia_home_utilities.HomeUtilities;
import co.com.bancolombia.form.qr.model.request.DataGetQr;
import co.com.bancolombia.form.qr.util.SecretUtil;
import co.com.bancolombia.form.qr.util.Utilities;
import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MessageServiceImpl.class)
public class MessageServiceImplGetDataTest {
	
	private static final String message = "message";

	@MockBean
	private RestOperations restTemplate;
	
	@MockBean
	private SecretUtil clientSecrets;
	
	@MockBean
	private Config config;

	@MockBean
	private AmazonSQS amazonSqs;
	
	@MockBean
	private Utilities utilities;

	private HomeUtilities homeUtilities;
	private HomeRest home;
	
	@InjectMocks
	@Autowired
	private MessageServiceImpl service;
	
	@PostConstruct
	public void init() {
		home = mock(HomeRest.class);
		homeUtilities = mock(HomeUtilities.class);
	}
	
	private MockHttpServletRequest headers;
	private RequestOld request;
	private Map<String, Object> data;
	private MetaRequestOld meta;
	
	@Before
	public void setUp() throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException {
		
		request = new RequestOld();
		
		headers = new MockHttpServletRequest();
		headers.addHeader("Accept", "application/vnd.bancolombia.v3+json");
		headers.addHeader("Content-Type", "application/vnd.bancolombia.v3+json");
		headers.addHeader("IP", "localhost");
		headers.addHeader("Recaptcha", "recaptcha");
		
		meta = new MetaRequestOld();
		meta.setApplication("Form QR");
		meta.setApplicationId("FormQR");
		meta.setChannel("Portal de Contenidos");
		meta.setOperation("getQr");
		meta.setUser("Portal");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\r\n");
		sb.append("    \"qrInformation\": {\r\n");
		sb.append("        \"commerceActivity\": \"Otros\",\r\n");
		sb.append("        \"commerceActivityDesc\": \"Comida y restaurantes\",\r\n");
		sb.append("        \"destinationProduct\": {\r\n");
		sb.append("            \"productType\": \"S\",\r\n");
		sb.append("            \"productNumber\": \"13131313131\"\r\n");
		sb.append("        },\r\n");
		sb.append("        \"saleInformation\": {\r\n");
		sb.append("            \"reference\": \"QR - Papas El Mompri\",\r\n");
		sb.append("            \"currency\": \"COP\"\r\n");
		sb.append("        },\r\n");
		sb.append("        \"commerceInformation\": {\r\n");
		sb.append("            \"name\": \"Prueba generacion\"\r\n");
		sb.append("        }\r\n");
		sb.append("    }\r\n");
		sb.append("}");

		data = new ObjectMapper().convertValue(new ObjectMapper().readValue(sb.toString(), DataGetQr.class), Map.class);
	}
	
	@Test
	public void generateResponseOkTest() throws SqsMessageException {
		request.setData(data);
		request.setMeta(meta);
		Response expected = new Response();
		when(home.generateReponseAudit(ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.any(HttpServletRequest.class), ArgumentMatchers.anyMap(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(expected);
		service.setHome(home);
		Response actual = service.generateResponse(headers, request, data, message);
		assertEquals(expected, actual);
	}
	
	@Test
	public void generateResponseSqsMessageExceptionTest() throws SqsMessageException {
		request.setData(data);
		request.setMeta(meta);		
		when(home.generateReponseAudit(ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.any(HttpServletRequest.class), ArgumentMatchers.anyMap(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenThrow(new SqsMessageException());
		service.setHome(home);
		Response expected = new Response();
		when(utilities.getEmptyResponse(ArgumentMatchers.any(HomeRest.class), ArgumentMatchers.any(HttpStatus.class))).thenReturn(expected);
		Response actual = service.generateResponse(headers, request, data, message);
		assertEquals(expected, actual);
	}

}
