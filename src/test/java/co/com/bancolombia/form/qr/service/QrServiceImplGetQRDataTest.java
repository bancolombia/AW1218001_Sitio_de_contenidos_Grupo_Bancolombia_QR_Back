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
import co.com.bancolombia.form.qr.model.qrcodes.response.DataResponseQrCodes;
import co.com.bancolombia.form.qr.model.request.QrInformation;
import co.com.bancolombia.form.qr.model.shipping.request.DataRequestShipping;
import co.com.bancolombia.form.qr.model.shipping.request.DataRequestShippings;
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
public class QrServiceImplGetQRDataTest {

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

	private QrInformation qrInformation;

	private DataResponseQrCodes dataResponseQrCodes;
	private List<co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode> dataQrCodes;
	private co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode responseDataQrCode;
	private Secrets secrets;

	@Before
	public void setUp() throws JsonParseException, JsonMappingException, IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("    {\r\n");
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

		qrInformation = new ObjectMapper().readValue(sb.toString(), QrInformation.class);

		dataResponseQrCodes = new DataResponseQrCodes();
		dataQrCodes = new ArrayList<co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode>();
		responseDataQrCode = new co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode();
		dataQrCodes.add(responseDataQrCode);
		dataResponseQrCodes.setData(dataQrCodes);
		secrets = new Secrets();
		secrets.setClientId(clientId);
		secrets.setClientSecret(clientSecret);
		when(clientSecrets.getSecret()).thenReturn(secrets);
	}

	@Test
	public void getQRDataOkTest()
			throws KeyManagementException, NoSuchAlgorithmException, IOException, KeyStoreException, ApiQrCustomErrorException, TrustStoreException {

		OkHttpClient client = mock(OkHttpClient.class);
		when(connection.getSafeOkHttpClient()).thenReturn(client);

		MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);
		Request mockRequest = new Request.Builder().url(fullPathQrCodes).build();
		Response mockResponse = new Response.Builder().request(mockRequest).protocol(Protocol.HTTP_2).code(200)
				.message("").body(okhttp3.ResponseBody.create(mediaType, "{}")).build();

		Call call = mock(Call.class);
		when(call.execute()).thenReturn(mockResponse);
		when(client.newCall(ArgumentMatchers.any(Request.class))).thenReturn(call);

		when(mapper.writeValueAsString(ArgumentMatchers.any(DataRequestQrCodes.class))).thenReturn(message);

		when(settings.getFullPathQrCodes()).thenReturn(fullPathQrCodes);

		when(mapper.readValue("{}", DataResponseQrCodes.class)).thenReturn(dataResponseQrCodes);

		assertNotNull(service.getQRData(qrInformation));
	}

	@Test
	public void getQRDataApiQrCustomErrorExceptionTest()
			throws KeyManagementException, NoSuchAlgorithmException, IOException, KeyStoreException, TrustStoreException {

		OkHttpClient client = mock(OkHttpClient.class);
		when(connection.getSafeOkHttpClient()).thenReturn(client);

		MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE);
		Request mockRequest = new Request.Builder().url(fullPathQrCodes).build();
		Response mockResponse = new Response.Builder().request(mockRequest).protocol(Protocol.HTTP_2).code(500)
				.message("").body(okhttp3.ResponseBody.create(mediaType, "{}")).build();

		Call call = mock(Call.class);
		when(call.execute()).thenReturn(mockResponse);
		when(client.newCall(ArgumentMatchers.any(Request.class))).thenReturn(call);

		when(mapper.writeValueAsString(ArgumentMatchers.any(DataRequestQrCodes.class))).thenReturn(message);

		when(settings.getFullPathQrCodes()).thenReturn(fullPathQrCodes);

		ApiQrCustomErrorException exception = null;
		try {
			service.getQRData(qrInformation);
		} catch (ApiQrCustomErrorException t) {
			exception = t;
		}
		assertNotNull(exception);
	}

}
