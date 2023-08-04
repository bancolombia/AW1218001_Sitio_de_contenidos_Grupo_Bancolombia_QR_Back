package co.com.bancolombia.form.qr.config;

import co.com.bancolombia.form.qr.Constant;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.WebIdentityTokenCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

	@Value("${spring.profiles.active:}")
	private String environment;
	@Value("${aws.cli.accessKey:}")
	private String accessKey;
	@Value("${aws.cli.secretKey:}")
	private String secretKey;
	@Value("${aws.region:}")
	private String region;
	@Value("${aws.host:}")
	private String s3host;


	@Bean
	public AmazonS3 amazonS3() {

		if (environment.equals(Constant.LOCAL_ENV)) {
			return AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(true)
					.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3host, region))
					.withCredentials(new AWSStaticCredentialsProvider(
							new BasicAWSCredentials(accessKey, secretKey)))
					.build();
		} else {
			return AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new WebIdentityTokenCredentialsProvider()).build();
		}
	}
	
}
