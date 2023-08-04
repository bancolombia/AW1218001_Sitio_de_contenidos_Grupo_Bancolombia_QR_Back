package co.com.bancolombia.form.qr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import co.com.bancolombia.bancolombia_home_utilities.Constant;

@Configuration
public class CaptchaConfig {
	
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(Constant.GOOGLE_CONNECTION_TIMEOUT);
		factory.setReadTimeout(Constant.GOOGLE_READ_TIMEOUT);
		return factory;
	}

	@Bean
	public RestOperations restTemplate() {
		return new RestTemplate(this.clientHttpRequestFactory());
	}
}
