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
@ContextConfiguration(classes = DataQrCode.class)
public class DataQrCodeTest {

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

	private Qr qr;

	private DataQrCode actual;

	@Before
	public void setUp() {

		commerceInformation = new CommerceInformation();
		commerceInformation.setName(name);

		destinationProduct = new DestinationProduct();
		destinationProduct.setProductNumber(productNumber);
		destinationProduct.setProductType(productType);

		saleInformation = new SaleInformation();
		saleInformation.setCurrency(currency);

		qrInformation = new QrInformation();
		qrInformation.setCommerceActivityDesc(commerceActivityDesc);
		qrInformation.setCommerceInformation(commerceInformation);
		qrInformation.setDestinationProduct(destinationProduct);
		qrInformation.setSaleInformation(saleInformation);

		qr = new Qr(); 
		qr.setChannel(channel);
		qr.setQrInformation(qrInformation);

		actual = new DataQrCode();
		actual.setQr(qr);
	}

	@Test
	public void toStringOkTest() {

		StringBuilder expected = new StringBuilder();

		expected.append("\n********** Data **********\n");
		expected.append(String.format("Qr: %s%n", actual.getQr()));
		expected.append("*****************************");

		assertEquals(expected.toString(), actual.toString());
	}

}
