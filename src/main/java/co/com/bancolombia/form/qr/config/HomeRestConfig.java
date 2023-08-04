package co.com.bancolombia.form.qr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.bancolombia.bancolombia_home_rest.model.Config;

@Configuration
public class HomeRestConfig {

	@Value("${aws.host}")
	private String sqsHost;

	@Value("${aws.sqs.awsaccount}")
	private String sqsAwsAccount;

	@Value("${aws.sqs.queue}")
	private String sqsQueue;

	@Value("${aws.region}")
	private String sqsRegion;

	@Value("${aws.sqs.message.group}")
	private String sqsMessageGroup;

	@Bean
	public Config config() {
		return new Config(getSqsHost(), getSqsAwsAccount(), getSqsQueue(), getSqsRegion(), getSqsMessageGroup());
	}

	@Override
	public String toString() {
		return String.format(
				"HomeRestConfig [sqsHost=%s, sqsAwsAccount=%s, sqsQueue=%s, sqsRegion=%s, sqsMessageGroup=%s",
				getSqsHost(), getSqsAwsAccount(), getSqsQueue(), getSqsRegion(), getSqsMessageGroup());
	}

	public String getSqsHost() {
		return sqsHost;
	}

	public String getSqsAwsAccount() {
		return sqsAwsAccount;
	}

	public String getSqsQueue() {
		return sqsQueue;
	}

	public String getSqsRegion() {
		return sqsRegion;
	}

	public String getSqsMessageGroup() {
		return sqsMessageGroup;
	}

	public void setSqsHost(String sqsHost) {
		this.sqsHost = sqsHost;
	}

	public void setSqsAwsAccount(String sqsAwsAccount) {
		this.sqsAwsAccount = sqsAwsAccount;
	}

	public void setSqsQueue(String sqsQueue) {
		this.sqsQueue = sqsQueue;
	}

	public void setSqsRegion(String sqsRegion) {
		this.sqsRegion = sqsRegion;
	}

	public void setSqsMessageGroup(String sqsMessageGroup) {
		this.sqsMessageGroup = sqsMessageGroup;
	}

}
