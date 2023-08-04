package co.com.bancolombia.form.qr.controller;

import co.com.bancolombia.bancolombia_home_rest.error.JsonMapperException;
import co.com.bancolombia.bancolombia_home_rest.error.SqsMessageException;
import co.com.bancolombia.bancolombia_home_rest.structure.RequestOld;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;
import co.com.bancolombia.bancolombia_home_rest.util.JsonMapper;
import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.config.BucketSettings;
import co.com.bancolombia.form.qr.dto.ReportPdf;
import co.com.bancolombia.form.qr.dto.Template;
import co.com.bancolombia.form.qr.error.RequestException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.generateqr.request.RenewRequest;
import co.com.bancolombia.form.qr.model.generateqr.response.MigrationResponse;
import co.com.bancolombia.form.qr.model.generateqr.response.RenewResponse;
import co.com.bancolombia.form.qr.model.response.DataGetQrResponse;
import co.com.bancolombia.form.qr.model.response.GeneratedFiles;
import co.com.bancolombia.form.qr.service.IGenerateQr;
import co.com.bancolombia.form.qr.service.IMessageService;
import co.com.bancolombia.form.qr.service.IPdfService;
import co.com.bancolombia.form.qr.util.ImageConverter;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.batik.transcoder.TranscoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "${allowed.cross.origins}", methods = {RequestMethod.GET, RequestMethod.POST,
        RequestMethod.OPTIONS}, allowCredentials = "${allow.credentials}", maxAge = 3600)
public class GenerateControllerImpl implements IGenerateController {

    private static Logger logger = LoggerFactory.getLogger(GenerateControllerImpl.class);

    @Autowired
    private HttpServletRequest headers;

    @Autowired
    private IGenerateQr renewQr;

    @Autowired
    private BucketSettings bucketSettings;

    @Autowired
    private ReportPdf reportPdfCockade;

    @Autowired
    private ImageConverter imageConverter;

    @Autowired
    private Template pdfTemplates;

    @Autowired
    private ReportPdf reportPdfTalkative;

    @Autowired
    private IPdfService pdfService;

    @Autowired
    private IMessageService<RenewRequest> validateRequestServiceRenewQr;

    private JsonMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new JsonMapper();
    }

    @Override
    public ResponseEntity<Response> generateQr(RequestOld request) {
        try {
            var dataRequest = validateRequestServiceRenewQr.getData(headers, request, RenewRequest.class);
            var dataQrCode = renewQr.renewQr(dataRequest);
            if(dataRequest.getRequestInfo().getRequestType().equals("Generacion")) {
                DataGetQrResponse dataResponse = new DataGetQrResponse();
                byte[] qrImage = imageConverter.svg2Png(dataQrCode.getData().get(0).getImage());
                GeneratedFiles generatedFiles = new GeneratedFiles();
                reportPdfCockade.setQrImage(qrImage);
                reportPdfCockade.setBackGroundImage(
                        pdfTemplates.getTemplates().get(bucketSettings.getCockadeSvgKey()).getTemplate());
                if(Boolean.FALSE.equals(dataRequest.getQrShipping().getPhysicalRequest())) {
                    reportPdfTalkative.setQrImage(qrImage);
                    reportPdfTalkative.setBackGroundImage(
                            pdfTemplates.getTemplates().get(bucketSettings.getTalkativeSvgKey()).getTemplate());
                    generatedFiles.setQrPdfTalkative(pdfService.getPdf(reportPdfTalkative, "true").getBase64());
                }
                reportPdfCockade.setBackGroundImageTwo(
                        pdfTemplates.getTemplates().get(bucketSettings.getCockadeSvgKeyTwo()).getTemplate());
                generatedFiles.setQrImage(Base64.getEncoder().encodeToString(qrImage));
                generatedFiles.setQrPdfCockade(pdfService.getPdf(reportPdfCockade, "false").getBase64());
                dataResponse.setQrId(dataQrCode.getData().get(0).getIdQR());
                dataResponse.setGeneratedFiles(generatedFiles);
                return new ResponseEntity<>(validateRequestServiceRenewQr.generateResponse(headers, request,
                        mapper.getMapClass(dataResponse), ""), HttpStatus.OK);
            }
            return new ResponseEntity<>(validateRequestServiceRenewQr.generateResponse(headers, request,
                    mapper.getMapClass(getMigrationResponse(dataQrCode)), ""), HttpStatus.OK);
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | JsonMapperException
                | IOException | TrustStoreException | SqsMessageException | TranscoderException e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("status", false);
            errorData.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorData.put("message", Constant.APIQR_UNEXPECTED_RESPONSE_FROM_REMOTE_SERVER);
            Response response = validateRequestServiceRenewQr.generateResponse(headers, request, null,
                    Constant.MESSAGE_INTERNAL_SERVER_ERROR + " " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RequestException e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("status", e.getMessage() != null && Constant.CODE.equals(e.getMessage()));
            errorData.put("code", HttpStatus.BAD_REQUEST.value());
            errorData.put("message", Constant.APIQR_UNEXPECTED_RESPONSE_FROM_REMOTE_SERVER);
            var response = validateRequestServiceRenewQr.generateResponse(headers, request,
                    errorData, Constant.MESSAGE_INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private MigrationResponse getMigrationResponse(RenewResponse renewResponse){
        MigrationResponse migrationResponse = new MigrationResponse();
        migrationResponse.setIdRequest(renewResponse.getData().get(0).getIdRequest());
        return migrationResponse;
    }
}
