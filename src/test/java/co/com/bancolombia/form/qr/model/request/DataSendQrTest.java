package co.com.bancolombia.form.qr.model.request;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.model.DeliveryInformation;
import co.com.bancolombia.form.qr.model.HabeasData;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSendQr.class)
public class DataSendQrTest {

	private static final String commerceActivityDesc = "COMMERCE-ACTIVITY-DESC";
	private static final String name = "NAME";
	private static final String productNumber = "PRODUCT-NUMBER";
	private static final String productType = "PRODUCT-TYPE";
	private static final String currency = "CURRENCY";
	
	private static final String address = "ADDRESS";
	private static final String city = "CITY";
	private static final String contactPhone = "CONTACT-PHONE";
	private static final String deliveryTime = "DELIVERY-TIME";
	private static final String department = "DEPARTMENT";
	private static final int qrQuantity = 1;
	
	private static final String agreementDateTime = "AGREEMENT-DATE-TIME";
	private static final String version = "version";

	private QrInformation qrInformation;
	private CommerceInformation commerceInformation;
	private DestinationProduct destinationProduct;
	private SaleInformation saleInformation;	
	private DeliveryInformation deliveryInformation;
	private HabeasData habeasData;

	private DataSendQr actual;

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
		
		deliveryInformation = new DeliveryInformation();
		deliveryInformation.setAddress(address);
		deliveryInformation.setCity(city);
		deliveryInformation.setContactPhone(contactPhone);
		deliveryInformation.setDeliveryTime(deliveryTime);
		deliveryInformation.setDepartment(department);
		deliveryInformation.setQrQuantity(qrQuantity);
		
		habeasData = new HabeasData();
		habeasData.setAgreementDateTime(agreementDateTime);
		habeasData.setVersion(version);

		actual = new DataSendQr();
		actual.setQrInformation(qrInformation);
		actual.setDeliveryInformation(deliveryInformation);
		actual.setHabeasData(habeasData);
	}

	@Test
	public void toStringOkTest() {

		StringBuilder expected = new StringBuilder();
		
		expected.append("\n********** Data **********\n");
		expected.append(String.format("Qr information: %s%n", actual.getQrInformation()));
		expected.append(String.format("Delivery information: %s%n", actual.getDeliveryInformation()));
		expected.append(String.format("Habeas data: %s%n", actual.getHabeasData()));
		expected.append("*****************************");		

		assertEquals(expected.toString(), actual.toString());
	}

}
