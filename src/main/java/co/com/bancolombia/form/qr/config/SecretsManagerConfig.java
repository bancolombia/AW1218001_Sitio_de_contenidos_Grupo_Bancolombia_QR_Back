package co.com.bancolombia.form.qr.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.WebIdentityTokenCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretsManagerConfig {

    @Value("${spring.profiles.active:}")
    private String environment;

    @Value("${aws.endPoint:}")
    private String endPoint;
    @Value("${aws.secretsmanager.secretName:}")
    private String secretName;

    @Value("${aws.cli.accessKey:}")
    private String accessKey;
    @Value("${aws.cli.secretKey:}")
    private String secretKey;
    @Value("${aws.region:}")
    private String region;

    @Bean(name = "clientSecrets")
    public AWSSecretsManager clientSecrets() {

        if (environment == null || environment.isBlank()) {
            return null;
        }

        if (environment.equals("local")) {
            return AWSSecretsManagerClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                            endPoint, region))
                    .withCredentials(new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(accessKey, secretKey)))
                    .build();
        } else {
            return AWSSecretsManagerClientBuilder.standard().withRegion(Regions.US_EAST_1)
                    .withCredentials(new WebIdentityTokenCredentialsProvider()).build();
        }
    }

}
