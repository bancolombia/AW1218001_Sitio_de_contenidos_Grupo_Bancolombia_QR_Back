package co.com.bancolombia.form.qr.service;

import co.com.bancolombia.form.qr.dto.EncodedPdf;
import co.com.bancolombia.form.qr.dto.ReportPdf;
import co.com.bancolombia.form.qr.util.GeneratePdfOpen;
import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class PdfServiceImpl implements IPdfService {
	
	@Autowired
	private GeneratePdfOpen reportPdfGenerator;

	@Override
	public EncodedPdf getPdf(ReportPdf reportPdf, String flag) throws IOException, TranscoderException {
		return reportPdfGenerator.generatePdf(reportPdf, flag);
	}
}
