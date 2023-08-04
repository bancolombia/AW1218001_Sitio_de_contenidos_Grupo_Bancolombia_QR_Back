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
import co.com.bancolombia.form.qr.error.ApiQrCustomErrorException;
import co.com.bancolombia.form.qr.error.RequestException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.DeliveryInformation;
import co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode;
import co.com.bancolombia.form.qr.model.request.DataGetQr;
import co.com.bancolombia.form.qr.model.request.DataSendQr;
import co.com.bancolombia.form.qr.model.response.DataGetQrResponse;
import co.com.bancolombia.form.qr.model.response.DataSendQrResponse;
import co.com.bancolombia.form.qr.model.response.GeneratedFiles;
import co.com.bancolombia.form.qr.model.shipping.request.RequestShipping;
import co.com.bancolombia.form.qr.model.shipping.response.DataResponseShipping;
import co.com.bancolombia.form.qr.service.IMessageService;
import co.com.bancolombia.form.qr.service.IPdfService;
import co.com.bancolombia.form.qr.service.IQrService;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "${allowed.cross.origins}", methods = {RequestMethod.GET, RequestMethod.POST,
        RequestMethod.OPTIONS}, allowCredentials = "${allow.credentials}", maxAge = 3600)
public class QrControllerImpl implements IQrController {

    private static Logger logger = LoggerFactory.getLogger(QrControllerImpl.class);

    @Autowired
    private HttpServletRequest headers;

    @Autowired
    private IQrService service;

    @Autowired
    private IPdfService pdfService;

    @Autowired
    private IMessageService<DataGetQr> validateRequestServiceGetQr;

    @Autowired
    private IMessageService<DataSendQr> validateRequestServiceSendQr;

    @Autowired
    private BucketSettings bucketSettings;

    @Autowired
    private ReportPdf reportPdfCockade;

    @Autowired
    private ReportPdf reportPdfTalkative;

    @Autowired
    private ImageConverter imageConverter;

    @Autowired
    private Template pdfTemplates;

    private JsonMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new JsonMapper();
    }

    @Override
    public ResponseEntity<Response> getQr(RequestOld request) {

        logger.info("Request - Generate QR");

        try {
            DataGetQr data = new DataGetQr();
            data = validateRequestServiceGetQr.getData(headers, request, DataGetQr.class);
            DataGetQrResponse dataResponse = new DataGetQrResponse();
            DataQrCode dataQrCode = service.getQRData(data.getQrInformation());
            byte[] qrImage = imageConverter.svg2Png(dataQrCode.getQrImage());
            reportPdfCockade.setQrImage(qrImage);
            reportPdfCockade.setBackGroundImage(
                    pdfTemplates.getTemplates().get(bucketSettings.getCockadeSvgKey()).getTemplate());
            reportPdfTalkative.setQrImage(qrImage);
            reportPdfTalkative.setBackGroundImage(
                    pdfTemplates.getTemplates().get(bucketSettings.getTalkativeSvgKey()).getTemplate());
            reportPdfTalkative.setBackGroundImageTwo(
                    pdfTemplates.getTemplates().get(bucketSettings.getCockadeSvgKeyTwo()).getTemplate());
            GeneratedFiles generatedFiles = new GeneratedFiles();
            generatedFiles.setQrImage(Base64.getEncoder().encodeToString(qrImage));
            generatedFiles.setQrPdfCockade(pdfService.getPdf(reportPdfCockade, "false").getBase64());
            generatedFiles.setQrPdfTalkative(pdfService.getPdf(reportPdfTalkative, "true").getBase64());
            dataResponse.setQrId(dataQrCode.getQrId());
            dataResponse.setGeneratedFiles(generatedFiles);
            logger.info("Response - Generated QR");
            return new ResponseEntity<>(validateRequestServiceGetQr.generateResponse(headers, request,
                    mapper.getMapClass(dataResponse), ""), HttpStatus.OK);
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | JsonMapperException
                | IOException | TranscoderException | ApiQrCustomErrorException
                | TrustStoreException | SqsMessageException e) {
            logger.error(Constant.MESSAGE_INTERNAL_SERVER_ERROR_DETAIL, e);
            Response response = validateRequestServiceGetQr.generateResponse(headers, request, null,
                    Constant.MESSAGE_INTERNAL_SERVER_ERROR + " " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RequestException e) {
            logger.error(Constant.MESSAGE_NO_ACCEPT_REQUEST, e);
            return new ResponseEntity<>(e.getResponse(), e.getStatus());
        }

    }

    @Override
    public ResponseEntity<Response> sendQr(RequestOld request) {
        logger.info("Request - Send QR");
        try {
            DataSendQr data = new DataSendQr();
            data = validateRequestServiceSendQr.getData(headers, request, DataSendQr.class);
            DataSendQrResponse dataResponse = new DataSendQrResponse();
            DataQrCode dataQrCode = service.getQRData(data.getQrInformation());
            byte[] qrImage = imageConverter.svg2Png(dataQrCode.getQrImage());
            RequestShipping requestShipping = new RequestShipping();
            requestShipping.setChannel(Constant.APIQR_CHANNEL);
            requestShipping.setQrId(dataQrCode.getQrId());
            requestShipping.setType(Constant.APIQR_TYPE);
            List<DeliveryInformation> deliveryInformations = new ArrayList<>();
            deliveryInformations.add(data.getDeliveryInformation());
            requestShipping.setDeliveryInformation(deliveryInformations);
            requestShipping.setHabeasData(data.getHabeasData());
            reportPdfCockade.setQrImage(qrImage);
            reportPdfCockade.setBackGroundImage(
                    pdfTemplates.getTemplates().get(bucketSettings.getCockadeSvgKey()).getTemplate());
            reportPdfTalkative.setQrImage(qrImage);
            reportPdfTalkative.setBackGroundImage(
                    pdfTemplates.getTemplates().get(bucketSettings.getTalkativeSvgKey()).getTemplate());
            reportPdfTalkative.setBackGroundImageTwo(
                    pdfTemplates.getTemplates().get(bucketSettings.getCockadeSvgKeyTwo()).getTemplate());
            DataResponseShipping dataResponseShipping = service.sendQRData(requestShipping);
            GeneratedFiles generatedFiles = new GeneratedFiles();
            generatedFiles.setQrImage(Base64.getEncoder().encodeToString(qrImage));
            generatedFiles.setQrPdfCockade(pdfService.getPdf(reportPdfCockade, "false").getBase64());
            generatedFiles.setQrPdfTalkative(pdfService.getPdf(reportPdfTalkative, "true").getBase64());
            dataResponse.setGeneratedFiles(generatedFiles);
            dataResponse.setQrId(dataResponseShipping.getIdQR());
            dataResponse.setRequestShippingId(dataResponseShipping.getIdrequestShipping());
            DeliveryInformation deliveryInformation = new DeliveryInformation();
            deliveryInformation.setAddress(dataResponseShipping.getAddress());
            deliveryInformation.setCity(dataResponseShipping.getCity());
            deliveryInformation.setContactPhone(dataResponseShipping.getContactPhone());
            deliveryInformation.setDeliveryTime(dataResponseShipping.getDeliveryTime());
            deliveryInformation.setDepartment(dataResponseShipping.getDepartment());
            deliveryInformation.setQrQuantity(dataResponseShipping.getQrQuantity());
            dataResponse.setDeliveryInformation(deliveryInformation);
            logger.info("Response - Generated and Send QR");
            return new ResponseEntity<>(validateRequestServiceGetQr.generateResponse(headers, request,
                    mapper.getMapClass(dataResponse), ""), HttpStatus.OK);
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | JsonMapperException
                | IOException | TranscoderException | ApiQrCustomErrorException
                | TrustStoreException | SqsMessageException e) {
            logger.error(Constant.MESSAGE_INTERNAL_SERVER_ERROR_DETAIL, e);
            Response response = validateRequestServiceGetQr.generateResponse(headers, request, null,
                    Constant.MESSAGE_INTERNAL_SERVER_ERROR + " " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RequestException e) {
            logger.error(Constant.MESSAGE_NO_ACCEPT_REQUEST, e);
            return new ResponseEntity<>(e.getResponse(), e.getStatus());
        }
    }

}
