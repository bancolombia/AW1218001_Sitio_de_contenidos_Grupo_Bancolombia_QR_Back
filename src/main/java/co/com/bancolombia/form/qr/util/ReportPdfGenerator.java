package co.com.bancolombia.form.qr.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.dto.EncodedPdf;
import co.com.bancolombia.form.qr.dto.ReportPdf;


@Component
public class ReportPdfGenerator {
	
	public EncodedPdf generate(ReportPdf reportPdf) throws IOException {
		
		FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
		
		InputStream inputStreamBackgroundBancolombiaQR = new ByteArrayInputStream(reportPdf.getBackGroundImage());
		
	    PdfDocument pdfDoc = new PdfDocument(
	    		new PdfReader(inputStreamBackgroundBancolombiaQR),
	    		new PdfWriter(outputStream, new WriterProperties().addUAXmpMetadata().setPdfVersion(PdfVersion.PDF_1_7))
	    		);
		
        Document document = new Document(pdfDoc, PageSize.LETTER);
        document.setMargins(0, 0, 0, 0);
        
        pdfDoc.getCatalog().setViewerPreferences(new PdfViewerPreferences().setDisplayDocTitle(true));
        pdfDoc.getCatalog().setLang(new PdfString(Constant.QR_LANGUAGE_ES_CO));
        PdfDocumentInfo info = pdfDoc.getDocumentInfo();
        info.setTitle(reportPdf.getTitle());
        
        ImageData imageDataQr = ImageDataFactory.create(reportPdf.getQrImage());
        Image imageQr = new Image(imageDataQr);
        imageQr.setFixedPosition(reportPdf.getPage(), reportPdf.getQrImagefixedPositionLeft(), reportPdf.getQrImagefixedPositionBottom());
        imageQr.scaleAbsolute(reportPdf.getScaleToFitWidth(), reportPdf.getScaleToFitHeight());
        document.add(imageQr);        
        
        document.flush();
        document.close();

        EncodedPdf response = new EncodedPdf();
        response.setBase64(Base64.getEncoder().encodeToString(outputStream.toByteArray()));
        
        outputStream.flush();
        outputStream.close();
        
        return response;
	}

}
