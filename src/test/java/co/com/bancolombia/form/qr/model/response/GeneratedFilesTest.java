package co.com.bancolombia.form.qr.model.response;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.util.Utilities;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GeneratedFiles.class)
public class GeneratedFilesTest {

	private final String qrImage = "QR-IMAGE";
	private final String qrPdfCockade = "QR-PDF-COCKADE";
	private final String qrPdfTalkative = "QR-PDF-TALKATIVE";

	@Autowired
	private GeneratedFiles actual;

	@Before
	public void setUp() {		
		actual.setQrImage(qrImage);
		actual.setQrPdfCockade(qrPdfCockade);
		actual.setQrPdfTalkative(qrPdfTalkative);
	}

	@Test
	public void toStringOkTest() {
		StringBuilder expected = new StringBuilder();

		expected.append("\n********** Data **********\n");
		expected.append(String.format("Qr image: %s%n", ((actual.getQrImage() == null || actual.getQrImage().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)));
		expected.append(String.format("Qr pdf cockade: %s%n", ((actual.getQrPdfCockade() == null || actual.getQrPdfCockade().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)));
		expected.append(String.format("Qr pdf talkative: %s%n", ((actual.getQrPdfTalkative() == null || actual.getQrPdfTalkative().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)));
		expected.append("*****************************");
		
		assertEquals(expected.toString(), actual.toString());
	}

}
