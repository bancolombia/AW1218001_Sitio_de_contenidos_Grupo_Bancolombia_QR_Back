package co.com.bancolombia.form.qr.controller;

import co.com.bancolombia.bancolombia_home_rest.error.JsonMapperException;
import co.com.bancolombia.bancolombia_home_rest.error.SqsMessageException;
import co.com.bancolombia.bancolombia_home_rest.structure.RequestOld;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;
import co.com.bancolombia.bancolombia_home_rest.structure.model.MetaRequestOld;
import co.com.bancolombia.form.qr.config.BucketSettings;
import co.com.bancolombia.form.qr.dto.EncodedPdf;
import co.com.bancolombia.form.qr.dto.ReportPdf;
import co.com.bancolombia.form.qr.dto.Template;
import co.com.bancolombia.form.qr.error.ApiQrCustomErrorException;
import co.com.bancolombia.form.qr.error.RequestException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode;
import co.com.bancolombia.form.qr.model.request.DataGetQr;
import co.com.bancolombia.form.qr.model.request.DataSendQr;
import co.com.bancolombia.form.qr.model.request.QrInformation;
import co.com.bancolombia.form.qr.service.IBucketService;
import co.com.bancolombia.form.qr.service.IMessageService;
import co.com.bancolombia.form.qr.service.IPdfService;
import co.com.bancolombia.form.qr.service.IQrService;
import co.com.bancolombia.form.qr.util.ImageConverter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.batik.transcoder.TranscoderException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = QrControllerImpl.class)
public class QrControllerImplGetQrTest {

	private final String key = "my-key";
	private final String bigText = "3zN8cFXE6TCacxAcjzV5EMcXnWVnI4N0yyda2cPC8pNF3sqNYMC9jejJmRB3oY9pir1O0qEpjdGa4r5BG20KX7L7AUWxZgRFWODkzLqkEaiSsFbvwYkSaoaXe6FtITFPV9i1dBOPyHPyyQ9gC83VFc20HQNDlZ1o9tPddnysAY2D8h0yd33d9tak7fcRsMcGnfm52u4RetLw1MCAhur1628wOJTnLs7yzipCiAPSEw7doqQLAeAQSBtd03dsdt98uyTXEfgB8NXhwd8SpvNFjSGrbBDMcGjKhraV8KMmMFW2S0uffQGTAuKosED0UFZS6sOuC2XHObD4TsCvc8s90rPKbMv6ohbRNole7OEcJmpQS8vhdNgy7D68JNNcTWTTxbScIi1E4va7KsgLa9bj8wa7Ocr8DjUOp1y0FAYovrKz0KdI6ylwVAhSsW2xegn1iyrLOrF4QTJVFlUtvRRKchXggEIpjoTmzCcbU8VdmZzskTkJNocTwNq6sa6TOnl15yT53Zz1upEh3ZmZTpq22l76UvkO7GoKxiYRY03Sq7c7Toxa0SZov6MEQDVXyY2W2lNwAFM57EXjZ9IKGTaIE05gayEaFkJ4qCV8cmHqcfB30xy0btqzuc1mrGXmqhN7nv1rWCOPYOpKDNFebRuNsj2AXkAMm83j3eknBLoJDuYrnDELnN3o2Rj9l8VQjSkjeZMUJ6iRZlCLqe1ft4LN8Orw4yvwEl8WnmMXjSGZRXrxKliOQx5cqklOGXPhHvnj7ipvt4f6p8yxulogOGMypxi2myz0enEdwBgpBaqIGYZ5PRjgRWtf3L9CpFTCCCOfzEn7JAW7pPgwq4QKnNwCr8QjO1cq0gCCKMuvhwqUnUmKpK1N7mjOkvZiYVngHbN03CYTWJjdMdU37lzGRLooiebtyi01ytpvAxGw2EKdsq9IINvhKusvGtUvFIt6eILeN7RApn41Jm3KOHhNPpWuRImQdHA1rra5cuMPCvop";	

	private MockHttpServletRequest headers;
	private RequestOld request;
	private MetaRequestOld metaRequest;
	private Map<String, Object> data;
	private DataGetQr dataGetQr;

	@MockBean
	private IQrService service;

	@MockBean
	private IBucketService bucketService;

	@MockBean
	private IPdfService pdfService;

	@MockBean
	private IMessageService<DataGetQr> validateRequestServiceGetQr;

	@MockBean
	private IMessageService<DataSendQr> validateRequestServiceSendQr;

	@MockBean
	private BucketSettings bucketSettings;

	@MockBean
	private ReportPdf reportPdfCockade;

	@MockBean
	private ImageConverter imageConverter;
	
	@MockBean
	private Template pdfTemplates;

	@Autowired
	@InjectMocks
	private QrControllerImpl controller;

	private DataQrCode dataQrCode;
	private EncodedPdf encodedPdf;
	private Map<String, EncodedPdf> templates;

	@Before()
	public void setUp() throws JsonParseException, JsonMappingException, IllegalArgumentException, IOException,
			TranscoderException {

		request = new RequestOld();

		headers = new MockHttpServletRequest();
		headers.addHeader("Accept", "application/vnd.bancolombia.v3+json");
		headers.addHeader("Content-Type", "application/vnd.bancolombia.v3+json");
		headers.addHeader("IP", "localhost");
		headers.addHeader("Recaptcha", "recaptcha");

		metaRequest = new MetaRequestOld();
		metaRequest.setApplication("Form QR");
		metaRequest.setApplicationId("FormQR");
		metaRequest.setChannel("Portal de Contenidos");
		metaRequest.setOperation("getQr");
		metaRequest.setUser("Portal");

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

		dataGetQr = new DataGetQr();
		dataQrCode = new DataQrCode();
		dataQrCode.setQrImage(bigText);

		encodedPdf = new EncodedPdf();
		encodedPdf.setBase64(Base64.getEncoder().encode(bigText.getBytes()).toString());
		encodedPdf.setTemplate(bigText.getBytes());
		
		EncodedPdf encodedPdf = new EncodedPdf();
		encodedPdf.setBase64(bigText);
		encodedPdf.setTemplate(bigText.getBytes());
		templates = new HashMap<String, EncodedPdf>();
		templates.put(key, encodedPdf);
	}

	@Test
	public void getQrOKTest() throws IOException, TranscoderException,
			KeyManagementException, NoSuchAlgorithmException, KeyStoreException, RequestException, ApiQrCustomErrorException, TrustStoreException, SqsMessageException, JsonMapperException {

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));

		request.setData(data);
		request.setMeta(metaRequest);

		ObjectMapper mapper = new ObjectMapper();
		dataGetQr = mapper.convertValue(request.getData(), DataGetQr.class);

		when(validateRequestServiceGetQr.getData(ArgumentMatchers.any(HttpServletRequest.class),
				ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(DataGetQr.class))).thenReturn(dataGetQr);

		when(bucketSettings.getCockadeSvgKey()).thenReturn(key);
		when(bucketSettings.getTalkativeSvgKey()).thenReturn(key);
		when(bucketSettings.getCockadeSvgKeyTwo()).thenReturn(key);

		when(service.getQRData(ArgumentMatchers.any(QrInformation.class)))
				.thenReturn(dataQrCode);
		when(bucketService.getTemplate(ArgumentMatchers.anyString()))
				.thenReturn(encodedPdf);

		when(pdfService.getPdf(ArgumentMatchers.any(ReportPdf.class), ArgumentMatchers.any()))
				.thenReturn(encodedPdf);

		when(imageConverter.svg2Png(ArgumentMatchers.anyString())).thenReturn(bigText.getBytes());
		
		when(pdfTemplates.getTemplates()).thenReturn(templates);

		ResponseEntity<Response> response = controller.getQr(request);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void getQrInvalidRequestTest() throws InterruptedException, ExecutionException, RequestException, SqsMessageException, JsonMapperException {

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));

		request.setData(data);
		request.setMeta(metaRequest);

		ObjectMapper mapper = new ObjectMapper();
		dataGetQr = mapper.convertValue(request.getData(), DataGetQr.class);

		RequestException requestException = new RequestException(new Response(), HttpStatus.BAD_REQUEST);

		when(validateRequestServiceGetQr.getData(ArgumentMatchers.any(HttpServletRequest.class),
				ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(DataGetQr.class))).thenThrow(requestException);

		ResponseEntity<Response> response = controller.getQr(request);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void getQrInvalidRequestWithResponseTest() throws RequestException, SqsMessageException, JsonMapperException {

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));

		request.setData(data);
		request.setMeta(metaRequest);

		ObjectMapper mapper = new ObjectMapper();
		dataGetQr = mapper.convertValue(request.getData(), DataGetQr.class);

		RequestException requestException = new RequestException(new Response(), HttpStatus.BAD_REQUEST);		

		when(validateRequestServiceGetQr.getData(ArgumentMatchers.any(HttpServletRequest.class),
				ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(DataGetQr.class))).thenThrow(requestException);

		ResponseEntity<Response> response = controller.getQr(request);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

}