package co.com.bancolombia.form.qr.model.qrcodes.request;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataRequestQrCodes.class)
public class DataRequestQrCodesTest {
	
	private DataQrCode dataQrCode;
	
	private List<DataQrCode> data;
	
	private DataRequestQrCodes actual;
	
	private Qr qr;
	
	@Before
	public void setUp() {
		
		qr = new Qr();
		
		dataQrCode = new DataQrCode();
		dataQrCode.setQr(qr);
		
		data = new ArrayList<DataQrCode>();
		data.add(dataQrCode);
		
		actual = new DataRequestQrCodes();
		actual.setData(data);
	}

	@Test
	public void toStringOkTest() {
		
		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("Data: %s%n", actual.getData()));
		expected.append("*****************************");
		
		assertEquals(expected.toString(), actual.toString());
	}

}
