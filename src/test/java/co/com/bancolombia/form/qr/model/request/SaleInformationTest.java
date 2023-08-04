package co.com.bancolombia.form.qr.model.request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SaleInformation.class)
public class SaleInformationTest {
	
	private static final String reference = "reference";
	private static final String currency = "currency";
	
	private SaleInformation actual;
	
	@Before
	public void setUp() {
		actual = new SaleInformation();
		actual.setReference(reference);
		actual.setCurrency(currency);
	}

	@Test
	public void toStringOkTest() {
		
		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("Reference: %s%n", actual.getReference()));
		expected.append(String.format("Currency: %s%n", actual.getCurrency()));
		expected.append("*****************************");
        
        assertEquals(expected.toString(), actual.toString());
	}

}
