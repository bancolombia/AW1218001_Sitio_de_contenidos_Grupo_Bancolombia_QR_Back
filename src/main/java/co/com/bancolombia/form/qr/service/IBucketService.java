package co.com.bancolombia.form.qr.service;

import java.io.IOException;

import co.com.bancolombia.form.qr.dto.EncodedPdf;

public interface IBucketService {

	public EncodedPdf getTemplate(String key) throws IOException;
	
}
