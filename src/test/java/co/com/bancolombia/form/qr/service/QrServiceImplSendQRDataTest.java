package co.com.bancolombia.form.qr.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import co.com.bancolombia.form.qr.util.SecretUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bancolombia.form.qr.config.ApiQrSettings;
import co.com.bancolombia.form.qr.dto.Secrets;
import co.com.bancolombia.form.qr.error.ApiQrCustomErrorException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.qrcodes.request.DataQrCode;
import co.com.bancolombia.form.qr.model.qrcodes.request.DataRequestQrCodes;
import co.com.bancolombia.form.qr.model.qrcodes.request.Qr;
import co.com.bancolombia.form.qr.model.shipping.request.DataRequestShipping;
import co.com.bancolombia.form.qr.model.shipping.request.DataRequestShippings;
import co.com.bancolombia.form.qr.model.shipping.request.RequestShipping;
import co.com.bancolombia.form.qr.model.shipping.response.DataResponseShippings;
import co.com.bancolombia.form.qr.util.HttpConnection;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = QrServiceImpl.class)
public class QrServiceImplSendQRDataTest {

	private final String message = "message";
	private final String fullPathQrCodes = "http://localhost:8080/full-path-qrcodes/operation";
	private final String clientId = "clientId";
	private final String clientSecret = "clientSecret";

	@MockBean
	private ApiQrSettings settings;
	@MockBean
	private DataRequestQrCodes dataRequestQrCodes;
	@MockBean
	private DataQrCode dataQrCode;
	@MockBean
	private Qr qr;
	@MockBean
	private DataRequestShipping dataShipping;
	@MockBean
	private DataRequestShippings dataRequestShippings;
	@MockBean
	private HttpConnection connection;
	@MockBean
	private ObjectMapper mapper;
	@MockBean
	private SecretUtil clientSecrets;

	@InjectMocks
	@Autowired
	private QrServiceImpl service;

	@Mock
	private OkHttpClient client;

	private RequestShipping requestShipping;

	private Secrets secrets;

	private DataResponseShippings dataResponseShippings;
	private List<co.com.bancolombia.form.qr.model.shipping.response.DataResponseShipping> dataShippings;
	private co.com.bancolombia.form.qr.model.shipping.response.DataResponseShipping responseDataResponseShipping;

	@Before
	public void setUp() throws JsonParseException, JsonMappingException, IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("{\r\n");
		sb.append("    \"channel\": \"Portal de Contenidos\",\r\n");
		sb.append("    \"qrId\": \"0798ab0c-a7e2-4984-aa5b-9ce24680d6f8\",\r\n");
		sb.append("    \"type\": \"Nuevo\",\r\n");
		sb.append("    \"deliveryInformation\": [\r\n");
		sb.append("        {\r\n");
		sb.append("            \"qrCuantity\": 5,\r\n");
		sb.append("            \"department\": \"Antioquia\",\r\n");
		sb.append("            \"city\": \"Medellin\",\r\n");
		sb.append("            \"address\": \"Calle Falsa 123\",\r\n");
		sb.append("            \"contactPhone\": \"32244323243\",\r\n");
		sb.append("            \"deliveryTime\": \"8am - 12m\"\r\n");
		sb.append("        }\r\n");
		sb.append("    ],\r\n");
		sb.append("    \"habeasData\": {\r\n");
		sb.append("        \"agreementDateTime\": \"2019-08-21T21:40:23\",\r\n");
		sb.append("        \"version\": \"V2.6\"\r\n");
		sb.append("    }\r\n");
		sb.append("}\r\n");

		requestShipping = new ObjectMapper().readValue(sb.toString(), RequestShipping.class);
		secrets = new Secrets();
		dataResponseShippings = new DataResponseShippings();
		dataShippings = new ArrayList<co.com.bancolombia.form.qr.model.shipping.response.DataResponseShipping>();
		responseDataResponseShipping = new co.com.bancolombia.form.qr.model.shipping.response.DataResponseShipping();
		dataShippings.add(responseDataResponseShipping);
		dataResponseShippings.setData(dataShippings);
		secrets.setClientSecret(clientSecret);
		secrets.setClientId(clientId);
		when(clientSecrets.getSecret()).thenReturn(secrets);
	}

	@Test
	public void sendQRDataOkTest()
			throws KeyManagementException, NoSuchAlgorithmException, IOException, KeyStoreException, ApiQrCustomErrorException, TrustStoreException {

		OkHttpClient client = mock(OkHttpClient.class);
		when(connection.getSafeOkHttpClient()).thenReturn(client);

		MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);
		Request mockRequest = new Request.Builder().url(fullPathQrCodes).build();
		Response mockResponse = new Response.Builder().request(mockRequest).protocol(Protocol.HTTP_2).code(200) // status
																												// code
				.message("").body(okhttp3.ResponseBody.create(mediaType, "{}")).build();

		Call call = mock(Call.class);
		when(call.execute()).thenReturn(mockResponse);
		when(client.newCall(ArgumentMatchers.any(Request.class))).thenReturn(call);

		when(mapper.writeValueAsString(ArgumentMatchers.any(DataRequestShippings.class))).thenReturn(message);

		when(settings.getFullPathRequestShipping()).thenReturn(fullPathQrCodes);

		when(mapper.readValue("{}", DataResponseShippings.class)).thenReturn(dataResponseShippings);

		assertNotNull(service.sendQRData(requestShipping));
	}

	@Test
	public void sendQRDataApiQrCustomErrorExceptionTest()
			throws KeyManagementException, NoSuchAlgorithmException, IOException, KeyStoreException, TrustStoreException {

		OkHttpClient client = mock(OkHttpClient.class);
		when(connection.getSafeOkHttpClient()).thenReturn(client);

		MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);
		Request mockRequest = new Request.Builder().url(fullPathQrCodes).build();
		Response mockResponse = new Response.Builder().request(mockRequest).protocol(Protocol.HTTP_2).code(500) // status
																												// code
				.message("").body(okhttp3.ResponseBody.create(mediaType, "{}")).build();

		Call call = mock(Call.class);
		when(call.execute()).thenReturn(mockResponse);
		when(client.newCall(ArgumentMatchers.any(Request.class))).thenReturn(call);

		when(mapper.writeValueAsString(ArgumentMatchers.any(DataRequestShippings.class))).thenReturn(message);

		when(settings.getFullPathRequestShipping()).thenReturn(fullPathQrCodes);

		ApiQrCustomErrorException exception = null;
		try {
			service.sendQRData(requestShipping);
		} catch (ApiQrCustomErrorException t) {
			exception = t;
		}
		assertNotNull(exception);
	}

}
