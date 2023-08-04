package co.com.bancolombia.form.qr.model.request;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Document.class)
public class DocumentTest {
	
	private final String documentNumber = "document-number";
	private final String documentType = "document-type";
	
	private Document actual;
	
	@Before
	public void setUp() {
		actual = new Document();
		actual.setDocumentNumber(documentNumber);
		actual.setDocumentType(documentType);
	}

	@Test
	public void toStringOkTest() {
		StringBuilder expected = new StringBuilder();

		expected.append("\n********** Data **********\n");
		expected.append(String.format("Document type: %s%n", actual.getDocumentType()));
		expected.append(String.format("Document number: %s%n", actual.getDocumentNumber()));
		expected.append("*****************************");

		assertEquals(expected.toString(), actual.toString());
	}

}
