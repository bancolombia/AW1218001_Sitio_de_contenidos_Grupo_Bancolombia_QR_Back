package co.com.bancolombia.form.qr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.dto.ReportPdf;

@Configuration
public class ReportPdfConfig {
	
	@Bean
	public ReportPdf reportPdfCockade() {
		
		ReportPdf reportPdf = new ReportPdf();
		reportPdf.setTitle(Constant.QR_REPORT_COCKADE_TITLE);
		reportPdf.setQrImagefixedPositionLeft(Constant.QR_REPORT_COCKADE_FIXED_POSITION_LEFT);
		reportPdf.setQrImagefixedPositionBottom(Constant.QR_REPORT_COCKADE_FIXED_POSITION_BOTTOM);
		reportPdf.setScaleToFitWidth(Constant.QR_REPORT_COCKADE_SCALE_TO_FIT_WIDTH);
		reportPdf.setScaleToFitHeight(Constant.QR_REPORT_COCKADE_SCALE_TO_FIT_HEIGHT);
		reportPdf.setPage(Constant.QR_REPORT_COCKADE_PAGE);
		
		return reportPdf;
	}
	
	@Bean            
	public ReportPdf reportPdfTalkative() {
		
		ReportPdf reportPdf = new ReportPdf();
		reportPdf.setTitle(Constant.QR_REPORT_TALKATIVE_TITLE);
		reportPdf.setQrImagefixedPositionLeft(Constant.QR_REPORT_TALKATIVE_FIXED_POSITION_LEFT);
		reportPdf.setQrImagefixedPositionBottom(Constant.QR_REPORT_TALKATIVE_FIXED_POSITION_BOTTOM);
		reportPdf.setScaleToFitWidth(Constant.QR_REPORT_TALKATIVE_SCALE_TO_FIT_WIDTH);
		reportPdf.setScaleToFitHeight(Constant.QR_REPORT_TALKATIVE_SCALE_TO_FIT_HEIGHT);
		reportPdf.setPage(Constant.QR_REPORT_TALKATIVE_PAGE);
		
		return reportPdf;
	}

}
