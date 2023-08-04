package co.com.bancolombia.form.qr.util;

import static org.junit.Assert.*;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Luis Felipe Barrera Mora - lufbarre@bancolombia.com.co
 *
 */
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.Key;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.config.TrustStoreSetting;
import co.com.bancolombia.form.qr.error.TrustStoreException;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HttpConnection.class)
public class HttpConnectionTest {
	
	@MockBean
	private TrustStoreSetting trustStoreSetting;
	
	@MockBean 
	private KeyStore trustStore;
	
	@InjectMocks
	@Autowired
	private HttpConnection actual;
	
	@Before
	public void setUp() throws NoSuchAlgorithmException, CertificateException, IOException {
		KeyStoreSpi trustStoreSpiMock = mock(KeyStoreSpi.class);
		trustStore = new KeyStore(trustStoreSpiMock, null, "test"){ };
		trustStore.load(null);  // this is important to put the internal state "
	}

	@Test
	public void allowSafeConnectionTrueOkTest() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, TrustStoreException {
		
		when(trustStore.getKey(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mock(Key.class));
		
		when(trustStoreSetting.getTrustStoreType()).thenReturn("JKS");
		assertNotNull(actual.getSafeOkHttpClient());
	}

}
