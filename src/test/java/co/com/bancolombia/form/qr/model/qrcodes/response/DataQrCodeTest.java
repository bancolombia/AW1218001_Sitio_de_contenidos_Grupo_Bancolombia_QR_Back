package co.com.bancolombia.form.qr.model.qrcodes.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.model.Header;
import co.com.bancolombia.form.qr.util.Utilities;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataQrCode.class)
public class DataQrCodeTest {

	private final String id = "id";
	private final String type = "type";

	private final String qrCode = "qrCode";
	private final String qrId = "qrId";
	private final String qrImage = "qrImage";

	private Header header;

	private DataQrCode actual;	

	@Before
	public void setUp() {
		header = new Header();
		header.setId(id);
		header.setType(type);

		actual = new DataQrCode();
		actual.setHeader(header);
		actual.setQrCode(qrCode);
		actual.setQrId(qrId);
		actual.setQrImage(qrImage);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(
				String.format("DataResponseQrCode [header=%s, qrCode=%s, qrId=%s, qrImage=%s]", actual.getHeader(),
						actual.getQrCode(), actual.getQrId(), ((actual.getQrImage() == null || actual.getQrImage().isEmpty())
								? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR
										: Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)),
				actual.toString());
	}

}
