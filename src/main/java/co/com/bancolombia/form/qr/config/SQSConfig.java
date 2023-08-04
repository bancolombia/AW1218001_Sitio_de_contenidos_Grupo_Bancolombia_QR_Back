package co.com.bancolombia.form.qr.config;

import co.com.bancolombia.form.qr.Constant;
import com.amazonaws.auth.WebIdentityTokenCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {
    
    private static Logger logger = LoggerFactory.getLogger(SQSConfig.class);

    @Value("${spring.profiles.active:}")
    private String environment;

    @Bean
    public AmazonSQS amazonSqs() {
        if (!environment.isEmpty() && environment.equals(Constant.LOCAL_ENV)) {
            logger.info(Constant.MESSAGE_SQS_LOCAL_CONNECTION, environment);
            return AmazonSQSClientBuilder.defaultClient();
        } else {
            logger.info(Constant.MESSAGE_SQS_AWS_CONNECTION);
            return AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1)
                    .withCredentials(new WebIdentityTokenCredentialsProvider()).build();
        }
    }
    
}
