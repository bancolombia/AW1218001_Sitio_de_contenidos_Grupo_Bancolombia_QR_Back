package co.com.bancolombia.form.qr.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.dto.EncodedPdf;
import co.com.bancolombia.form.qr.dto.Template;
import co.com.bancolombia.form.qr.service.IBucketService;

@Configuration
public class TemplateConfig {

	private static Logger logger = LoggerFactory.getLogger(TemplateConfig.class);
	
	@Autowired
	private BucketSettings bucketSettings;
	
	@Autowired
	private IBucketService bucketService;
	
	@Bean(name = "pdfTemplates")
	public Template pdfTemplates() throws IOException {
		logger.info(Constant.APIQR_S3_RETRIEVING_TEMPLATES_FROM_S3);
		Map<String, EncodedPdf> templates = new HashMap<String, EncodedPdf>();
		templates.put(bucketSettings.getCockadeSvgKey(), bucketService.getTemplate(bucketSettings.getCockadeSvgKey()));
		templates.put(bucketSettings.getTalkativeSvgKey(), bucketService.getTemplate(bucketSettings.getTalkativeSvgKey()));
		templates.put(bucketSettings.getCockadeSvgKeyTwo(), bucketService.getTemplate(bucketSettings.getCockadeSvgKeyTwo()));
		Template response = new Template();
		response.setTemplates(templates);
		return response;
	}

}
