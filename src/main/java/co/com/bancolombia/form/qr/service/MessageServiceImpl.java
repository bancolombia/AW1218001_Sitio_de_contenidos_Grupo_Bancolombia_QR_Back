package co.com.bancolombia.form.qr.service;

import co.com.bancolombia.bancolombia_home_rest.HomeRest;
import co.com.bancolombia.bancolombia_home_rest.error.JsonMapperException;
import co.com.bancolombia.bancolombia_home_rest.error.SqsMessageException;
import co.com.bancolombia.bancolombia_home_rest.model.Config;
import co.com.bancolombia.bancolombia_home_rest.structure.RequestOld;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;
import co.com.bancolombia.bancolombia_home_utilities.HomeUtilities;
import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.error.RequestException;
import co.com.bancolombia.form.qr.util.SecretUtil;
import co.com.bancolombia.form.qr.util.Utilities;
import com.amazonaws.services.sqs.AmazonSQS;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Map;
import java.util.Set;

@Service
public class MessageServiceImpl<T> implements IMessageService<T> {
	
	private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private SecretUtil clientSecrets;
	
	private HomeUtilities homeUtilities;
	
	@Autowired
	private Config config;

	@Autowired
	private AmazonSQS amazonSqs;
	
	@Autowired
	private Utilities utilities;

	private HomeRest home;
	
	@PostConstruct
	public void init() {
		config.setAmazonSqs(amazonSqs);
		setHome(new HomeRest(config));
		homeUtilities = new HomeUtilities();
	}
	
	public T getData(HttpServletRequest headers, RequestOld request, Class<?> dataClass) throws RequestException, SqsMessageException, JsonMapperException {
		boolean isValidRequest = false;
		isValidRequest = getHome().validateRequest(request, headers);
		if (!isValidRequest) {
			logger.error(Constant.MESSAGE_NO_ACCEPT_REQUEST + ": {}", request);
			logger.error(Constant.MESSAGE_NO_ACCEPT_REQUEST + ": {}", headers);
			throw new RequestException(utilities.getEmptyResponse(getHome(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
	 	UtilitiesResponse utilitiesResponse = homeUtilities.recaptchaValidation(headers.getHeader(Constant.HEADER_RECAPTCHA),
				new CaptchaConfig("", restTemplate, headers));
		if (utilitiesResponse.getHttpStatus() != HttpStatus.OK) {
			logger.error(Constant.LOG_KEY_VALUE, "Recaptcha validation status", utilitiesResponse.getHttpStatus());
			throw new RequestException(utilities.getEmptyResponse(getHome(), utilitiesResponse.getHttpStatus()), utilitiesResponse.getHttpStatus());
		}
		T data = null;
		try {
			data = getHome().getData(request, dataClass);
		} catch (JsonMapperException e) {
			logger.error(Constant.LOG_KEY_VALUE, "Error extracting data", e);
			throw new RequestException(utilities.getEmptyResponse(getHome(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(data);
		if (!violations.isEmpty()) {
			StringBuilder violationMessage = new StringBuilder();
			for (ConstraintViolation<T> violation : violations) {
				logger.error("Error in validation: {} = {}", violation.getMessage(), violation.getInvalidValue());
				violationMessage
						.append(String.format("%s - %s", violation.getMessage(), violation.getInvalidValue()));
			}
			Response response = getHome().generateReponseAudit(request, headers, null,
						HttpStatus.BAD_REQUEST.toString(),
						Constant.MESSAGE_ERROR_IN_PROCESS_DATA + " " + violationMessage.toString());
			HttpStatus status = HttpStatus.BAD_REQUEST;
			throw new RequestException(response, status);
		}
		return data;
	}

	public Response generateResponse(HttpServletRequest headers, RequestOld request, Map<String, Object> data, String message) {
		try {
			return getHome().generateReponseAudit(request, headers, data, HttpStatus.OK.toString(), message);
		} catch (SqsMessageException e) {
			logger.error(Constant.LOG_KEY_VALUE, "Error generating response", e);
			return utilities.getEmptyResponse(getHome(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public HomeRest getHome() {
		return home;
	}

	public void setHome(HomeRest home) {
		this.home = home;
	}

}
