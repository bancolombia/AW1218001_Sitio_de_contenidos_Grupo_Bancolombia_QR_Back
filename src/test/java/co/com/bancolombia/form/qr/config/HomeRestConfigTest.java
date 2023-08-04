package co.com.bancolombia.form.qr.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.bancolombia_home_rest.model.Config;
import co.com.bancolombia.form.qr.config.HomeRestConfig;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HomeRestConfig.class)
@TestPropertySource(properties = { "aws.host=sqs-host", "aws.sqs.awsaccount=sqs-aws-account",
		"aws.sqs.queue=sqs-queue", "aws.region=sqs-region", "aws.sqs.message-group=sqs-message-group" })
public class HomeRestConfigTest {

	private static final String SQSHOST = "sqs-host";
	private static final String SQSAWSACCOUNT = "sqs-aws-account";
	private static final String SQSQUEUE = "sqs-queue";
	private static final String SQSREGION = "sqs-region";
	private static final String SQSMESSAGEGROUP = "sqs-message-group";

	@MockBean
	private Config config;

	@InjectMocks
	@Autowired
	private HomeRestConfig homeRestConfig;

	@Before
	public void setUp() {
		homeRestConfig.setSqsHost(SQSHOST);
		homeRestConfig.setSqsAwsAccount(SQSAWSACCOUNT);
		homeRestConfig.setSqsQueue(SQSQUEUE);
		homeRestConfig.setSqsRegion(SQSREGION);
		homeRestConfig.setSqsMessageGroup(SQSMESSAGEGROUP);
	}

	@Test
	public void toStringTest() {
		String expected = String.format(
				"HomeRestConfig [sqsHost=%s, sqsAwsAccount=%s, sqsQueue=%s, sqsRegion=%s, sqsMessageGroup=%s", SQSHOST,
				SQSAWSACCOUNT, SQSQUEUE, SQSREGION, SQSMESSAGEGROUP);
		assertEquals(expected, homeRestConfig.toString());
	}
	
	@Test
	public void configTest() {
		
		HomeRestConfig config = new HomeRestConfig();
		config.setSqsHost(SQSHOST);
		config.setSqsAwsAccount(SQSAWSACCOUNT);
		config.setSqsQueue(SQSQUEUE);
		config.setSqsRegion(SQSREGION);
		config.setSqsMessageGroup(SQSMESSAGEGROUP);
		
		Config expected = new Config(SQSHOST, SQSAWSACCOUNT, SQSQUEUE, SQSREGION, SQSMESSAGEGROUP);
		assertEquals(expected.getSqsHost(), config.config().getSqsHost());
		assertEquals(expected.getSqsAwsAccount(), config.config().getSqsAwsAccount());
		assertEquals(expected.getSqsQueue(), config.config().getSqsQueue());
		assertEquals(expected.getSqsRegion(), config.config().getSqsRegion());
		assertEquals(expected.getSqsMessageGroup(), config.config().getSqsMessageGroup());
	}

}
