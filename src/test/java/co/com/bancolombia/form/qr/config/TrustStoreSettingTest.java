package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.config.TrustStoreSetting;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrustStoreSetting.class)
public class TrustStoreSettingTest {
	
	private String bucketName = "bucket-name";
	private String trustStoreName = "trust-store-name";
	private String trustStoreKeypass = "trust-store-keypass";
	private String trustStoreType = "trust-store-type";

	private TrustStoreSetting actual;

	@Before
	public void setUp() {
		actual = new TrustStoreSetting();
		actual.setBucketName(bucketName);
		actual.setTrustStoreName(trustStoreName);
		actual.setTrustStoreKeypass(trustStoreKeypass);
		actual.setTrustStoreType(trustStoreType);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(
				String.format("TrustStoreSetting [bucketName=%s, trustStoreName=%s, trustStoreKeypass=%s, trustStoreType=%s]",
						actual.getBucketName(), actual.getTrustStoreName(), actual.getTrustStoreKeypass(), actual.getTrustStoreType()),
				actual.toString());
	}

}
