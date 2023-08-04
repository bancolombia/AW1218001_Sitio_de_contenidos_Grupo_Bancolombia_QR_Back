package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.dto.ReportPdf;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ReportPdfConfig.class)
public class ReportPdfConfigTest {

	@Autowired
	private ReportPdf reportPdfCockade;
	
	@Autowired
	private ReportPdf reportPdfTalkative;
	
	@Test
	public void notNullOkTest() {
		assertNotNull(reportPdfCockade);
		assertNotNull(reportPdfTalkative);
	}

}
