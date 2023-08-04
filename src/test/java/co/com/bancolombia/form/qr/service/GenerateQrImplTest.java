package co.com.bancolombia.form.qr.service;

import co.com.bancolombia.form.qr.config.ApiQrSettings;
import co.com.bancolombia.form.qr.dto.Secrets;
import co.com.bancolombia.form.qr.error.RequestException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.generateqr.request.RenewRequest;
import co.com.bancolombia.form.qr.util.HttpConnection;
import co.com.bancolombia.form.qr.util.SecretUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
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

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GenerateQrImpl.class)
public class GenerateQrImplTest {

    private final String message = "message";
    private final String fullPathQrCodes = "http://localhost:8080/full-path-qrcodes/operation";
    private final String clientId = "clientId";
    private final String clientSecret = "clientSecret";

    @MockBean
    private ApiQrSettings settings;
    @MockBean
    private HttpConnection connection;

    @MockBean
    private SecretUtil clientSecrets;

    @InjectMocks
    @Autowired
    private GenerateQrImpl service;

    @Mock
    private OkHttpClient client;

    private RenewRequest renewRequest;
    private Secrets secrets;

    @Before
    public void setUp() throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("{\n" +
                "        \"requestInfo\": {\n" +
                "            \"requestType\": \"Migracion\",\n" +
                "            \"requestDate\": \"2018-07-16\",\n" +
                "            \"notificationEmail\": \"sedeCentro@gmail.com\",\n" +
                "            \"termsAndConditions\": [\n" +
                "                {\n" +
                "                    \"type\": \"Entre Cuentas\",\n" +
                "                    \"value\": \"Campo Referencia Libre\",\n" +
                "                    \"date\": \"2018-07-16T14:30:00\",\n" +
                "                    \"version\": \"V2.6\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"qr\": {\n" +
                "            \"destinationTrx\": {\n" +
                "                \"commerceDocument\": {\n" +
                "                    \"documentType\": \"CC\",\n" +
                "                    \"documentId\": \"12345678890\"\n" +
                "                },\n" +
                "                \"commerceName\": \"Papas el Mono\",\n" +
                "                \"commerceActivityName\": \"Tecnologia y Telecomunicaciones\",\n" +
                "                \"beneficiaryProductType\": \"S\",\n" +
                "                \"beneficiaryProductId\": \"40672400050\"\n" +
                "            },\n" +
                "            \"qrInformation\": {\n" +
                "                \"reference\": \"QR Papas El Mompri\"\n" +
                "            },\n" +
                "            \"creator\": {\n" +
                "                \"channel\": \"Portal Interno QR\",\n" +
                "                \"adviserId\": \"55122\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"qrShipping\": {\n" +
                "            \"physicalRequest\": true,\n" +
                "            \"contactInfo\": [\n" +
                "                {\n" +
                "                    \"quantity\": \"5\",\n" +
                "                    \"address\": \"Calle Falsa 123\",\n" +
                "                    \"department\": \"Antioquia\",\n" +
                "                    \"city\": \"Medellin\",\n" +
                "                    \"schedule\": \"8am - 12m, 12m - 6pm\",\n" +
                "                    \"contactPhoneNumber\": \"3026984421\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }");

        ObjectMapper objectMapper = new ObjectMapper();
        RenewRequest renew = objectMapper.readValue(sb.toString(), RenewRequest.class);
        renewRequest = renew;

        secrets = new Secrets();
        secrets.setClientIdV10(clientId);
        secrets.setClientSecretV10(clientSecret);
        when(clientSecrets.getSecret()).thenReturn(secrets);
    }

    @Test
    public void generateQRDataOkTest()
            throws KeyManagementException, NoSuchAlgorithmException, IOException, KeyStoreException,
            TrustStoreException, RequestException {

        OkHttpClient client = mock(OkHttpClient.class);
        when(connection.getSafeOkHttpClient()).thenReturn(client);

        MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        Request mockRequest = new Request.Builder().url(fullPathQrCodes).build();
        Response mockResponse = new Response.Builder().request(mockRequest).protocol(Protocol.HTTP_2).code(200)
                .message("").body(okhttp3.ResponseBody.create(mediaType, "{}")).build();

        Call call = mock(Call.class);
        when(call.execute()).thenReturn(mockResponse);
        when(client.newCall(ArgumentMatchers.any(Request.class))).thenReturn(call);

        when(settings.getFullPathRequestRenew()).thenReturn(fullPathQrCodes);

        assertNotNull(service.renewQr(renewRequest));
    }

    @Test
    public void generateQRExceptionTest()
            throws KeyManagementException, NoSuchAlgorithmException, IOException, KeyStoreException, TrustStoreException {

        OkHttpClient client = mock(OkHttpClient.class);
        when(connection.getSafeOkHttpClient()).thenReturn(client);

        MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        Request mockRequest = new Request.Builder().url(fullPathQrCodes).build();
        Response mockResponse = new Response.Builder().request(mockRequest).protocol(Protocol.HTTP_2).code(500)
                .message("").body(okhttp3.ResponseBody.create(mediaType, "{\"meta\":{\"_messageId\": \"c4e6bd04-5149-11e7-b114-b2f933d5fe66\",\"_version\": \"1.0\",\"_requestDateTime\": \"2023-05-09T15:41:25.204Z\",\"_responseSize\": 1,\"_clientRequest\":\"d6b6d52882cf1dbfbbdca43f246aee4f\"},\"errors\":[{\"href\": \"https://tools.ietf.org/html/rfc7231#section-6.6.1\",\"status\": \"400\",\"code\": \"BPQR-B0021\",\"title\": \"BadRequest\",\"detail\": \"EL COMERCIO NO TIENE QRs GENERADOS, El comercio no tiene QRs generados con el documento 25\"}]}")).build();

        Call call = mock(Call.class);
        when(call.execute()).thenReturn(mockResponse);
        when(client.newCall(ArgumentMatchers.any(Request.class))).thenReturn(call);

        when(settings.getFullPathRequestRenew()).thenReturn(fullPathQrCodes);

        RequestException exception = null;
        try {
            service.renewQr(renewRequest);
        } catch (RequestException t) {
            exception = t;
        }
        assertNotNull(exception);
    }

    @Test
    public void generateQRExceptionNullTest()
            throws KeyManagementException, NoSuchAlgorithmException, IOException, KeyStoreException, TrustStoreException {

        OkHttpClient client = mock(OkHttpClient.class);
        when(connection.getSafeOkHttpClient()).thenReturn(client);

        MediaType mediaType = MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        Request mockRequest = new Request.Builder().url(fullPathQrCodes).build();
        Response mockResponse = new Response.Builder().request(mockRequest).protocol(Protocol.HTTP_2).code(500)
                .message("").body(okhttp3.ResponseBody.create(mediaType, "{\"meta\":{\"_messageId\": \"c4e6bd04-5149-11e7-b114-b2f933d5fe66\",\"_version\": \"1.0\",\"_requestDateTime\": \"2023-05-09T15:41:25.204Z\",\"_responseSize\": 1,\"_clientRequest\":\"d6b6d52882cf1dbfbbdca43f246aee4f\"},\"errors\":[]}")).build();

        Call call = mock(Call.class);
        when(call.execute()).thenReturn(mockResponse);
        when(client.newCall(ArgumentMatchers.any(Request.class))).thenReturn(call);

        when(settings.getFullPathRequestRenew()).thenReturn(fullPathQrCodes);

        RequestException exception = null;
        try {
            service.renewQr(renewRequest);
        } catch (RequestException t) {
            exception = t;
        }
        assertNotNull(exception);
    }

}
