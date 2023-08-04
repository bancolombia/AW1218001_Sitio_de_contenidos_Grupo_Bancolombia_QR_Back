package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BucketSettings.class)
public class BucketSettingsTest {

	private final String bucketName = "bucketName";
	private final String cockadeSvgKeyOne = "cockadeSvgKeyOne";
	private final String cockadeSvgKeyTwo = "cockadeSvgKeyTwo";
	private final String endPoint = "endPoint";
	private final String talkativeSvgKey = "talkativeSvgKey";

	private BucketSettings actual;

	@Before
	public void setUp() {
		actual = new BucketSettings();
		actual.setBucketName(bucketName);
		actual.setCockadeSvgKey(cockadeSvgKeyOne);
		actual.setCockadeSvgKeyTwo(cockadeSvgKeyTwo);
		actual.setEndPoint(endPoint);
		actual.setTalkativeSvgKey(talkativeSvgKey);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(String.format("BucketSettings [endPoint=%s, bucketName=%s, cockadeSvgKeyOne=%s, cockadeSvgKeyTwo=%s, talkativeSvgKey=%s]",
				actual.getEndPoint(), actual.getBucketName(), actual.getCockadeSvgKey(), actual.getCockadeSvgKeyTwo(),
						actual.getTalkativeSvgKey()),
				actual.toString());
	}
	
	@Test
	public void toStringOkNullValuesTest() {
		actual.setBucketName(null);
		actual.setCockadeSvgKey(null);
		actual.setCockadeSvgKeyTwo(null);
		actual.setTalkativeSvgKey(null);
		assertEquals(String.format("BucketSettings [endPoint=%s, bucketName=%s, cockadeSvgKeyOne=%s, cockadeSvgKeyTwo=%s, talkativeSvgKey=%s]",
				endPoint, null, null, null, null),
				actual.toString());
	}

}
