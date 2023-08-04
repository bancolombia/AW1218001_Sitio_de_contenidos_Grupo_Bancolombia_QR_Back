package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


public class S3ConfigLocalTest {

	private final String endPoint = "endPoint";
	private final String accessKey = "accessKey";
	private final String region = "region";
	private final String secretKey = "secretKey";
	
	@Autowired
	@MockBean
	private AwsCliSetting awsCliSetting;
	
	@Autowired
	@MockBean
	private BucketSettings bucketSettings;

	@Autowired
	private AmazonS3 amazonS3;
	
	@InjectMocks
	@Autowired
	private S3Config s3Config;
	
	@Before
	public void setUp() {  
		//doNothing().when(AmazonS3ClientBuilder.standard());
		when(bucketSettings.getEndPoint()).thenReturn(endPoint);
		when(awsCliSetting.getRegion()).thenReturn(region);
		when(awsCliSetting.getAccessKey()).thenReturn(accessKey);
		when(awsCliSetting.getSecretKey()).thenReturn(secretKey);		
	}

	@Test
	public void notNullOkTest() {
		
		PowerMockito.mockStatic(AmazonS3ClientBuilder.class);
		doNothing().when(AmazonS3ClientBuilder.standard());
        //field.set(Foo.class, mock(B.class));

		//assertNotNull(amazonS3);
	}

}
