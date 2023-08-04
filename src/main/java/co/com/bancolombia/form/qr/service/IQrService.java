package co.com.bancolombia.form.qr.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import co.com.bancolombia.form.qr.error.ApiQrCustomErrorException;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import co.com.bancolombia.form.qr.model.qrcodes.response.DataQrCode;
import co.com.bancolombia.form.qr.model.request.QrInformation;
import co.com.bancolombia.form.qr.model.shipping.request.RequestShipping;
import co.com.bancolombia.form.qr.model.shipping.response.DataResponseShipping;


public interface IQrService {
	
    public DataQrCode getQRData(QrInformation qrInformation) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ApiQrCustomErrorException, TrustStoreException;
       
    public DataResponseShipping sendQRData(RequestShipping requestShipping) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ApiQrCustomErrorException, TrustStoreException;

}
