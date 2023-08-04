package co.com.bancolombia.form.qr.model.qrcodes.request;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.model.request.CommerceInformation;
import co.com.bancolombia.form.qr.model.request.DestinationProduct;
import co.com.bancolombia.form.qr.model.request.QrInformation;
import co.com.bancolombia.form.qr.model.request.SaleInformation;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Qr.class)
public class QrTest {
	
	private static final String commerceActivityDesc = "COMMERCE-ACTIVITY-DESC";
	private static final String productNumber = "PRODUCT-NUMBER";
	private static final String productType = "PRODUCT-TYPE";
	private static final String name = "NAME";
	private static final String currency = "CURRENCY";
	
	private CommerceInformation commerceInformation;
	private DestinationProduct destinationProduct;
	private SaleInformation saleInformation;
	
	private final String channel = "channel";
	private QrInformation qrInformation;
	
	private Qr actual;
	
	@Before
	public void setUp() {
		
		qrInformation = new QrInformation();
		
		commerceInformation = new CommerceInformation();
		commerceInformation.setName(name);
		
		destinationProduct = new DestinationProduct();
		destinationProduct.setProductNumber(productNumber);
		destinationProduct.setProductType(productType);
		
		saleInformation = new SaleInformation();
		saleInformation.setCurrency(currency);
		
		qrInformation.setCommerceActivityDesc(commerceActivityDesc);
		qrInformation.setCommerceInformation(commerceInformation);
		qrInformation.setDestinationProduct(destinationProduct);
		qrInformation.setSaleInformation(saleInformation);
		
		actual = new Qr();
		actual.setChannel(channel);
		actual.setQrInformation(qrInformation);
	}

	@Test
	public void toStringOkTest() {
		
		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("Channel: %s%n", actual.getChannel()));
		expected.append(String.format("Qr information: %s%n", actual.getQrInformation()));
		expected.append("*****************************");
        
        assertEquals(expected.toString(), actual.toString());
		
	}

}
