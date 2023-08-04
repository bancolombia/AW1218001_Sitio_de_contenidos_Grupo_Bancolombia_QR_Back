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
@ContextConfiguration(classes = QrInformation.class)
public class QrInformationTest {
	
	private static final String commerceActivity = "COMMERCE-ACTIVITY";
	private static final String commerceActivityDesc = "COMMERCE-ACTIVITY-DESC";
	private static final String productNumber = "PRODUCT-NUMBER";
	private static final String productType = "PRODUCT-TYPE";
	private static final String name = "NAME";
	private static final String currency = "CURRENCY";
	
	private CommerceInformation commerceInformation;
	private DestinationProduct destinationProduct;
	private SaleInformation saleInformation;

	private QrInformation actual;
	
	@Before
	public void setUp() {
		actual = new QrInformation();
		
		commerceInformation = new CommerceInformation();
		commerceInformation.setName(name);
		
		destinationProduct = new DestinationProduct();
		destinationProduct.setProductNumber(productNumber);
		destinationProduct.setProductType(productType);
		
		saleInformation = new SaleInformation();
		saleInformation.setCurrency(currency);
		
		actual = new QrInformation();
		actual.setCommerceActivity(commerceActivity);
		actual.setCommerceActivityDesc(commerceActivityDesc);
		actual.setCommerceInformation(commerceInformation);
		actual.setDestinationProduct(destinationProduct);
		actual.setSaleInformation(saleInformation);
	}

	@Test
	public void toStringOkTest() {
		
		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("Commerce activity: %s%n", actual.getCommerceActivity()));
		expected.append(String.format("Commerce activity desc: %s%n", actual.getCommerceActivityDesc()));
		expected.append(String.format("Destination product: %s%n", actual.getDestinationProduct()));
		expected.append(String.format("Sale information: %s%n", actual.getSaleInformation()));
		expected.append(String.format("Commerce information: %s%n", actual.getCommerceInformation()));
		expected.append("*****************************");
        
		assertEquals(expected.toString(), actual.toString());
    }
}
