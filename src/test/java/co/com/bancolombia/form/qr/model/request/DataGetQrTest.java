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
@ContextConfiguration(classes = DataGetQr.class)
public class DataGetQrTest {
	
	private static final String commerceActivityDesc = "COMMERCE-ACTIVITY-DESC";
	private static final String name = "NAME";
	private static final String productNumber = "PRODUCT-NUMBER";
	private static final String productType = "PRODUCT-TYPE";
	private static final String currency = "CURRENCY";

	private QrInformation qrInformation;
	private CommerceInformation commerceInformation;
	private DestinationProduct destinationProduct;
	private SaleInformation saleInformation;

	private DataGetQr actual;

	@Before
	public void setUp() {

		saleInformation = new SaleInformation();
		saleInformation.setCurrency(currency);

		destinationProduct = new DestinationProduct();
		destinationProduct.setProductNumber(productNumber);
		destinationProduct.setProductType(productType);

		commerceInformation = new CommerceInformation();
		commerceInformation.setName(name);

		qrInformation = new QrInformation();
		qrInformation.setCommerceActivityDesc(commerceActivityDesc);
		qrInformation.setCommerceInformation(commerceInformation);
		qrInformation.setDestinationProduct(destinationProduct);
		qrInformation.setSaleInformation(saleInformation);

		actual = new DataGetQr();
		actual.setQrInformation(qrInformation);
	}

	@Test
	public void toStringOkTest() {

		StringBuilder expected = new StringBuilder();

		expected.append("\n********** Data **********\n");
		expected.append(String.format("Qr information: %s%n", actual.getQrInformation()));
		expected.append("*****************************");

		assertEquals(expected.toString(), actual.toString());
	}

}
