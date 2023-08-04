package co.com.bancolombia.form.qr.config;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyStore;


@Configuration
public class TrustStoreConfig {

	private static Logger logger = LoggerFactory.getLogger(TrustStoreConfig.class);

	@Autowired
	private TrustStoreSetting trustStoreSetting;

	@Autowired
	private AmazonS3 amazonS3;

	@Bean
	public KeyStore trustStore() {

		return null;

	}

}
