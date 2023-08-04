package co.com.bancolombia.form.qr.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import co.com.bancolombia.form.qr.util.SecretUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.config.ApiQrSettings;
import co.com.bancolombia.form.qr.error.ApiQrCustomErrorException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.qrcodes.request.DataQrCode;
import co.com.bancolombia.form.qr.model.qrcodes.request.DataRequestQrCodes;
import co.com.bancolombia.form.qr.model.qrcodes.request.Qr;
import co.com.bancolombia.form.qr.model.qrcodes.response.DataResponseQrCodes;
import co.com.bancolombia.form.qr.model.request.QrInformation;
import co.com.bancolombia.form.qr.model.shipping.request.DataRequestShipping;
import co.com.bancolombia.form.qr.model.shipping.request.DataRequestShippings;
import co.com.bancolombia.form.qr.model.shipping.request.RequestShipping;
import co.com.bancolombia.form.qr.model.shipping.response.DataResponseShippings;
import co.com.bancolombia.form.qr.util.HttpConnection;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



@Service
public class QrServiceImpl implements IQrService {

	private static Logger logger = LoggerFactory.getLogger(QrServiceImpl.class);

	@Autowired
	private ApiQrSettings settings;
	@Autowired
	private DataRequestQrCodes dataRequestQrCodes;
	@Autowired
	private DataQrCode dataQrCode;
	@Autowired
	private Qr qr;
	@Autowired
	private DataRequestShipping dataShipping;
	@Autowired
	private DataRequestShippings dataRequestShippings;
	@Autowired
	private HttpConnection connection;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private SecretUtil clientSecrets;

	@Override
	public co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode getQRData(QrInformation qrInformation)
			throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			ApiQrCustomErrorException, TrustStoreException {

		List<DataQrCode> data = new ArrayList<>();

		qr.setChannel(Constant.APIQR_CHANNEL);
		qr.setQrInformation(qrInformation);
		dataQrCode.setQr(qr);

		data.add(dataQrCode);
		dataRequestQrCodes.setData(data);

		logger.info(Constant.APIQR_LOG_REQUEST_API, mapper.writeValueAsString(dataRequestQrCodes));

		MediaType mediaType = MediaType.parse(Constant.APPLICATION_JSON_UTF8_VALUE);
		RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(dataRequestQrCodes));
		logger.info(Constant.APIQR_LOG_CONSUME_API, settings.getFullPathQrCodes());
		Request request = new Request.Builder().url(settings.getFullPathQrCodes())
				.method(org.springframework.http.HttpMethod.POST.name(), body)
				.addHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE,
						org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
				.addHeader(Constant.APIQR_HEADER_CLIENT_ID_KEY, "")
				.addHeader(Constant.APIQR_HEADER_CLIENT_SECRET_KEY, "").build();

		Response response = connection.getSafeOkHttpClient().newCall(request).execute();
		String responseBody = response.body().string();
		
		if (HttpStatus.valueOf(response.code()).is2xxSuccessful()) {
			DataResponseQrCodes entity = mapper.readValue(responseBody, DataResponseQrCodes.class);
			logger.info(Constant.APIQR_LOG_RESPONSE_API, entity);
			return entity.getData().get(0);
		} else {
			throw new ApiQrCustomErrorException(Constant.APIQR_UNEXPECTED_RESPONSE_FROM_REMOTE_SERVER + responseBody);
		}

	}

	@Override
	public co.com.bancolombia.form.qr.model.shipping.response.DataResponseShipping sendQRData(
			RequestShipping requestShipping)
			throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ApiQrCustomErrorException, TrustStoreException {

		List<DataRequestShipping> data = new ArrayList<>();

		requestShipping.getHabeasData().setVersion(Constant.APIQR_HABEAS_DATA_VERSION);

		requestShipping.setHabeasData(requestShipping.getHabeasData());

		dataShipping.setRequestShipping(requestShipping);

		data.add(dataShipping);
		dataRequestShippings.setData(data);

		logger.info(Constant.APIQR_LOG_REQUEST_API, mapper.writeValueAsString(dataRequestShippings));

		OkHttpClient client = connection.getSafeOkHttpClient();

		MediaType mediaType = MediaType.parse(Constant.APPLICATION_JSON_UTF8_VALUE);
		RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(dataRequestShippings));
		logger.info(Constant.APIQR_LOG_CONSUME_API, settings.getFullPathRequestShipping());
		Request request = new Request.Builder().url(settings.getFullPathRequestShipping())
				.method(org.springframework.http.HttpMethod.POST.name(), body)
				.addHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE,
						org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
				.addHeader(Constant.APIQR_HEADER_CLIENT_ID_KEY, "")
				.addHeader(Constant.APIQR_HEADER_CLIENT_SECRET_KEY, "").build();

		Response response = client.newCall(request).execute();
		String responseBody = response.body().string();
		
		if (HttpStatus.valueOf(response.code()).is2xxSuccessful()) {
			DataResponseShippings entity = mapper.readValue(responseBody, DataResponseShippings.class);
			logger.info(Constant.APIQR_LOG_RESPONSE_API, entity);
			return entity.getData().get(0);
		} else {
			throw new ApiQrCustomErrorException(Constant.APIQR_UNEXPECTED_RESPONSE_FROM_REMOTE_SERVER + responseBody);
		}

	}

}
