package co.com.bancolombia.form.qr.model.response;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataGetQrResponse.class)
public class DataGetQrResponseTest {

	private final String qrId = "qrId";
	
	@Mock
	private GeneratedFiles generatedFiles;

	private DataGetQrResponse actual;

	@Before
	public void setUp() {		
		actual = new DataGetQrResponse();
		actual.setQrId(qrId);
		actual.setGeneratedFiles(generatedFiles);
	}

	@Test
	public void toStringOkTest() {

		StringBuilder expected = new StringBuilder();

		expected.append("\n********** Data **********\n");
		expected.append(String.format("Qr id: %s%n", actual.getQrId()));
		expected.append(String.format("Generated files: %s%n", actual.getGeneratedFiles()));
		expected.append("*****************************");

		assertEquals(expected.toString(), actual.toString());
	}

}
