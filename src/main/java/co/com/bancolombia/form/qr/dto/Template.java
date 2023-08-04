package co.com.bancolombia.form.qr.dto;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Template {
	
	private Map<String, EncodedPdf> templates;

	public Map<String, EncodedPdf> getTemplates() {
		return templates;
	}

	public void setTemplates(Map<String, EncodedPdf> templates) {
		this.templates = templates;
	}

}
