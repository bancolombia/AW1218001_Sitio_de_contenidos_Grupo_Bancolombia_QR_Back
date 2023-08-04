package co.com.bancolombia.form.qr.service;

import co.com.bancolombia.form.qr.error.RequestException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.generateqr.request.RenewRequest;
import co.com.bancolombia.form.qr.model.generateqr.response.RenewResponse;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public interface IGenerateQr {

    RenewResponse renewQr(RenewRequest request) throws IOException, KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, TrustStoreException, RequestException;
}
