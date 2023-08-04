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
import co.com.bancolombia.form.qr.model.generateqr.request.RenewRequest;
import co.com.bancolombia.form.qr.model.generateqr.response.RenewResponse;
import co.com.bancolombia.form.qr.service.IGenerateQr;
import co.com.bancolombia.form.qr.service.IMessageService;
import co.com.bancolombia.form.qr.service.IPdfService;
import co.com.bancolombia.form.qr.util.ImageConverter;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GenerateControllerImpl.class)
public class QrControllerImplGenerateQrTest {
    private final String key = "my-key";
    private final String bigText = "3zN8cFXE6TCacxAcjzV5EMcXnWVnI4N0yyda2cPC8pNF3sqNYMC9jejJmRB3oY9pir1O0qEpjdGa4r5BG20KX7L7AUWxZgRFWODkzLqkEaiSsFbvwYkSaoaXe6FtITFPV9i1dBOPyHPyyQ9gC83VFc20HQNDlZ1o9tPddnysAY2D8h0yd33d9tak7fcRsMcGnfm52u4RetLw1MCAhur1628wOJTnLs7yzipCiAPSEw7doqQLAeAQSBtd03dsdt98uyTXEfgB8NXhwd8SpvNFjSGrbBDMcGjKhraV8KMmMFW2S0uffQGTAuKosED0UFZS6sOuC2XHObD4TsCvc8s90rPKbMv6ohbRNole7OEcJmpQS8vhdNgy7D68JNNcTWTTxbScIi1E4va7KsgLa9bj8wa7Ocr8DjUOp1y0FAYovrKz0KdI6ylwVAhSsW2xegn1iyrLOrF4QTJVFlUtvRRKchXggEIpjoTmzCcbU8VdmZzskTkJNocTwNq6sa6TOnl15yT53Zz1upEh3ZmZTpq22l76UvkO7GoKxiYRY03Sq7c7Toxa0SZov6MEQDVXyY2W2lNwAFM57EXjZ9IKGTaIE05gayEaFkJ4qCV8cmHqcfB30xy0btqzuc1mrGXmqhN7nv1rWCOPYOpKDNFebRuNsj2AXkAMm83j3eknBLoJDuYrnDELnN3o2Rj9l8VQjSkjeZMUJ6iRZlCLqe1ft4LN8Orw4yvwEl8WnmMXjSGZRXrxKliOQx5cqklOGXPhHvnj7ipvt4f6p8yxulogOGMypxi2myz0enEdwBgpBaqIGYZ5PRjgRWtf3L9CpFTCCCOfzEn7JAW7pPgwq4QKnNwCr8QjO1cq0gCCKMuvhwqUnUmKpK1N7mjOkvZiYVngHbN03CYTWJjdMdU37lzGRLooiebtyi01ytpvAxGw2EKdsq9IINvhKusvGtUvFIt6eILeN7RApn41Jm3KOHhNPpWuRImQdHA1rra5cuMPCvop";

    private MockHttpServletRequest headers;
    private RequestOld request;
    private MetaRequestOld metaRequest;
    private Map<String, Object> data;
    private RenewRequest dataGetQr;

    @MockBean
    private IGenerateQr service;

    @MockBean
    private IMessageService<RenewRequest> renewRequestIMessageService;

    @MockBean
    private BucketSettings bucketSettings;

    @MockBean
    private ImageConverter imageConverter;

    @MockBean
    private Template pdfTemplates;

    @MockBean
    private ReportPdf reportPdfTalkative;

    @MockBean
    private IPdfService pdfService;


    @Autowired
    @InjectMocks
    private GenerateControllerImpl controller;

    private RenewResponse dataQrCode;
    private EncodedPdf encodedPdf;
    private Map<String, EncodedPdf> templates;

    @Before()
    public void setUp() throws IllegalArgumentException, IOException,
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
        sb.append(" {\n" +
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

        var dataRequest  = new ObjectMapper().readValue(sb.toString(),
                RenewRequest.class);
        TypeReference mapType = new TypeReference<Map<String, Object>>() {
        };
        data = (Map<String, Object>) new ObjectMapper().convertValue(dataRequest, mapType);

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
    public void getGenerateOKTest() throws IOException, KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, RequestException, ApiQrCustomErrorException, TrustStoreException,
            SqsMessageException, JsonMapperException, TranscoderException {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));
        request.setData(data);
        request.setMeta(metaRequest);
        ObjectMapper mapper = new ObjectMapper();
        dataGetQr = mapper.convertValue(request.getData(), RenewRequest.class);
        dataGetQr.getRequestInfo().setRequestType("Generacion");
        dataQrCode = new RenewResponse();
        var responsesData = new co.com.bancolombia.form.qr.model.generateqr.response.Response();
        responsesData.setImage(bigText);
        responsesData.setIdQR("123fofemvwbwnbw");
        var list = Collections.singletonList(responsesData);
        dataQrCode.setData(list);
        when(bucketSettings.getCockadeSvgKey()).thenReturn(key);
        when(bucketSettings.getCockadeSvgKeyTwo()).thenReturn(key);
        when(bucketSettings.getTalkativeSvgKey()).thenReturn(key);
        when(pdfTemplates.getTemplates()).thenReturn(templates);
        when(pdfService.getPdf(ArgumentMatchers.any(ReportPdf.class), ArgumentMatchers.any()))
                .thenReturn(encodedPdf);
        when(imageConverter.svg2Png(ArgumentMatchers.anyString())).thenReturn(bigText.getBytes());
        when(renewRequestIMessageService.getData(ArgumentMatchers.any(HttpServletRequest.class),
                ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(RenewRequest.class))).thenReturn(dataGetQr);
        when(service.renewQr(ArgumentMatchers.any(RenewRequest.class)))
                .thenReturn(dataQrCode);
        ResponseEntity<Response> response = controller.generateQr(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getGenerateNotPhysicalOKTest() throws IOException, KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, RequestException, ApiQrCustomErrorException, TrustStoreException,
            SqsMessageException, JsonMapperException, TranscoderException {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));
        request.setData(data);
        request.setMeta(metaRequest);
        ObjectMapper mapper = new ObjectMapper();
        dataGetQr = mapper.convertValue(request.getData(), RenewRequest.class);
        dataGetQr.getRequestInfo().setRequestType("Generacion");
        dataGetQr.getQrShipping().setPhysicalRequest(false);
        dataQrCode = new RenewResponse();
        var responsesData = new co.com.bancolombia.form.qr.model.generateqr.response.Response();
        responsesData.setImage(bigText);
        responsesData.setIdQR("123fofemvwbwnbw");
        var list = Collections.singletonList(responsesData);
        dataQrCode.setData(list);
        when(bucketSettings.getCockadeSvgKey()).thenReturn(key);
        when(bucketSettings.getCockadeSvgKeyTwo()).thenReturn(key);
        when(bucketSettings.getTalkativeSvgKey()).thenReturn(key);
        when(pdfTemplates.getTemplates()).thenReturn(templates);
        when(pdfService.getPdf(ArgumentMatchers.any(ReportPdf.class), ArgumentMatchers.any()))
                .thenReturn(encodedPdf);
        when(imageConverter.svg2Png(ArgumentMatchers.anyString())).thenReturn(bigText.getBytes());
        when(renewRequestIMessageService.getData(ArgumentMatchers.any(HttpServletRequest.class),
                ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(RenewRequest.class))).thenReturn(dataGetQr);
        when(service.renewQr(ArgumentMatchers.any(RenewRequest.class)))
                .thenReturn(dataQrCode);
        ResponseEntity<Response> response = controller.generateQr(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getMigrateOKTest() throws IOException, KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, RequestException, ApiQrCustomErrorException, TrustStoreException,
            SqsMessageException, JsonMapperException {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));
        dataQrCode = new RenewResponse();
        request.setData(data);
        request.setMeta(metaRequest);

        ObjectMapper mapper = new ObjectMapper();
        dataGetQr = mapper.convertValue(request.getData(), RenewRequest.class);

        when(renewRequestIMessageService.getData(ArgumentMatchers.any(HttpServletRequest.class),
                ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(RenewRequest.class))).thenReturn(dataGetQr);

        co.com.bancolombia.form.qr.model.generateqr.response.Response responses = new co.com.bancolombia.form.qr.model.generateqr.response.Response();
        List<String> list = Arrays.asList("fogebbjbjbqj", "VNVnvrvrvgnv");
        responses.setIdRequest(list);
        List<co.com.bancolombia.form.qr.model.generateqr.response.Response> listResponse = Collections.singletonList(responses);
        dataQrCode.setData(listResponse);

        when(service.renewQr(ArgumentMatchers.any(RenewRequest.class)))
                .thenReturn(dataQrCode);
        ResponseEntity<Response> response = controller.generateQr(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getGenerateInvalidRequestTest() throws RequestException, SqsMessageException, JsonMapperException {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));

        request.setData(data);
        request.setMeta(metaRequest);

        ObjectMapper mapper = new ObjectMapper();
        dataGetQr = mapper.convertValue(request.getData(), RenewRequest.class);

        RequestException requestException = new RequestException(new Response(), HttpStatus.BAD_REQUEST);

        when(renewRequestIMessageService.getData(ArgumentMatchers.any(HttpServletRequest.class),
                ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(RenewRequest.class))).thenThrow(requestException);

        ResponseEntity<Response> response = controller.generateQr(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getGenerateJsonErrorTest() throws RequestException, SqsMessageException, JsonMapperException {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(headers));

        request.setData(data);
        request.setMeta(metaRequest);

        ObjectMapper mapper = new ObjectMapper();
        dataGetQr = mapper.convertValue(request.getData(), RenewRequest.class);

        JsonMapperException requestException = new JsonMapperException("ERROR");

        when(renewRequestIMessageService.getData(ArgumentMatchers.any(HttpServletRequest.class),
                ArgumentMatchers.any(RequestOld.class), ArgumentMatchers.eq(RenewRequest.class))).thenThrow(requestException);

        ResponseEntity<Response> response = controller.generateQr(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}