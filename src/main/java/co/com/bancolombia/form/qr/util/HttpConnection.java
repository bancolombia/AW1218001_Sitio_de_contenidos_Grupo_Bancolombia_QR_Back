package co.com.bancolombia.form.qr.util;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.error.TrustStoreException;
import okhttp3.OkHttpClient;

@Component
public class HttpConnection {

	@Autowired
	private KeyStore trustStore;

	public OkHttpClient getSafeOkHttpClient()
			throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, TrustStoreException {

		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(trustStore);
		TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
		if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
			throw new TrustStoreException(
					Constant.APIQR_UNEXPECTED_DEFAULT_TRUST_MANAGER + Arrays.toString(trustManagers));
		}
		X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

		SSLContext sslContext = SSLContext.getInstance(Constant.APIQR_TLS_PROTOCOL);
		sslContext.init(null, new TrustManager[] { trustManager }, null);
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		return new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustManager)
				.connectTimeout(Constant.APIQR_CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
				.readTimeout(Constant.APIQR_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS).build();
	}

}
