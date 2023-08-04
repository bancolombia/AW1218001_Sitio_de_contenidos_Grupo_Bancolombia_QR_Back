package co.com.bancolombia.form.qr.service;


import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.config.ApiQrSettings;
import co.com.bancolombia.form.qr.error.RequestException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.generateqr.request.RenewDataRequest;
import co.com.bancolombia.form.qr.model.generateqr.request.RenewRequest;
import co.com.bancolombia.form.qr.model.generateqr.response.RenewResponse;
import co.com.bancolombia.form.qr.model.response.ErrorResponse;
import co.com.bancolombia.form.qr.util.HttpConnection;
import co.com.bancolombia.form.qr.util.SecretUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Slf4j
@Service
public class GenerateQrImpl implements IGenerateQr {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateQrImpl.class);

    private final ApiQrSettings settings;
    private final HttpConnection connection;
    private final SecretUtil clientSecrets;

    public GenerateQrImpl(ApiQrSettings settings, HttpConnection connection,
                          SecretUtil clientSecrets) {
        this.settings = settings;
        this.connection = connection;
        this.clientSecrets = clientSecrets;
    }

    @Override
    public RenewResponse renewQr(RenewRequest request) throws IOException, KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException, TrustStoreException, RequestException {
        var dataRequest = Collections.singletonList(request);
        RenewDataRequest request1 = new RenewDataRequest();
        request1.setData(dataRequest);
        Gson gson = new Gson();
        MediaType mediaType = MediaType.parse(Constant.APPLICATION_JSON_UTF8_VALUE);
        RequestBody body = RequestBody.create(mediaType, gson.toJson(request1));
        LOGGER.info(Constant.APIQR_LOG_CONSUME_API, settings.getFullPathRequestRenew());
        Request requestData = new Request.Builder().url(settings.getFullPathRequestRenew())
                .method(org.springframework.http.HttpMethod.POST.name(), body)
                .addHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE,
                        org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                .addHeader(Constant.APIQR_CLIENT_ID, "")
                .addHeader(Constant.APIQR_CLIENT_SECRET, "").build();

        Response response = connection.getSafeOkHttpClient().newCall(requestData).execute();
        String responseBody = response.body().string();

        if (HttpStatus.valueOf(response.code()).is2xxSuccessful()) {
            RenewResponse entity = gson.fromJson(responseBody, RenewResponse.class);
            LOGGER.info(Constant.APIQR_LOG_RESPONSE_API, entity);
            return entity;
        }
        ErrorResponse entity = gson.fromJson(responseBody, ErrorResponse.class);
        if(!entity.getData().isEmpty() ){
            throw new RequestException(entity.getData().get(0).getCode());
        }
        throw new RequestException(Constant.APIQR_UNEXPECTED_RESPONSE_FROM_REMOTE_SERVER + entity);
    }
}
