package co.com.bancolombia.form.qr.service;

import java.io.IOException;

import org.apache.batik.transcoder.TranscoderException;

import co.com.bancolombia.form.qr.dto.EncodedPdf;
import co.com.bancolombia.form.qr.dto.ReportPdf;


public interface IPdfService {

	public EncodedPdf getPdf(ReportPdf reportPdf, String flag) throws IOException, TranscoderException;

}
