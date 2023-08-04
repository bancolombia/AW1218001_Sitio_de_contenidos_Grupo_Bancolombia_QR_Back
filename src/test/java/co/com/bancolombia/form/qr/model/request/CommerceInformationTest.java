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
@ContextConfiguration(classes = CommerceInformation.class)
public class CommerceInformationTest {
	
	private final String name = "name-text";
	private final String documentNumber = "document-number";
	private final String documentType = "document-type";
	
	private CommerceInformation actual;
	private Document document;	
	
	@Before
	public void setUp() {
		
		document = new Document();
		document.setDocumentNumber(documentNumber);
		document.setDocumentType(documentType);
		
		actual = new CommerceInformation();
		actual.setName(name);
		actual.setDocument(document);
	}
	
	@Test
	public void toStringOkTest() {
		
		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("Name: %s%n", actual.getName()));
		expected.append(String.format("Document: %s%n", actual.getDocument()));
		expected.append("*****************************");
        
        assertEquals(expected.toString(), actual.toString());
	}
	

}
