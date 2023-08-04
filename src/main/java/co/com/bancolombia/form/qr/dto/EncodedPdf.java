package co.com.bancolombia.form.qr.dto;

import org.springframework.stereotype.Component;

import co.com.bancolombia.form.qr.Constant;

@Component
public class EncodedPdf {

	private String base64;
	private byte[] template;

	public String getBase64() {
		return base64;
	}
	public byte[] getTemplate() {
		return template;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public void setTemplate(byte[] template) {
		this.template = template;
	}
	
	@Override
	public String toString() {
		return String.format("EncodedPdf [base64=%s, template=%s]", ((getBase64() == null || getBase64().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY), ((getTemplate() == null || getTemplate().length == 0) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY));
	}

}
