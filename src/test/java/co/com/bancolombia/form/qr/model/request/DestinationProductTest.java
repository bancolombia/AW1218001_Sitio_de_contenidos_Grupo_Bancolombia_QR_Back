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
@ContextConfiguration(classes = DestinationProduct.class)
public class DestinationProductTest {
	
	private static final String productNumber = "PRODUCT-NUMBER";
	private static final String productType = "PRODUCT-TYPE";
	
	private DestinationProduct actual;
	
	@Before
	public void setUp() {
		actual = new DestinationProduct();
		actual.setProductNumber(productNumber);
		actual.setProductType(productType);
	}

	@Test
	public void toStringOkTest() {
		
		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("Product type: %s%n", actual.getProductType()));
		expected.append(String.format("Product number: %s%n", actual.getProductNumber()));
		expected.append("*****************************");
        
		assertEquals(expected.toString(), actual.toString());
    }

}
