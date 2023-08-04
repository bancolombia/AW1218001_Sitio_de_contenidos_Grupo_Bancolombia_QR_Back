package co.com.bancolombia.form.qr.util;


import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.config.SecretsManagerProperty;
import co.com.bancolombia.form.qr.dto.Secrets;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SecretUtil {

    private final AWSSecretsManager clientSecrets;
    private final SecretsManagerProperty secretsManagerProperty;

    public SecretUtil(AWSSecretsManager clientSecrets, SecretsManagerProperty secretsManagerProperty) {
        this.clientSecrets = clientSecrets;
        this.secretsManagerProperty = secretsManagerProperty;
    }

    public Secrets getSecret() {

        GetSecretValueRequest getSecretValueRequest =
                new GetSecretValueRequest().withSecretId(secretsManagerProperty.getSecretName())
                        .withVersionStage(Constant.AWS_SECRETSM_AWSCURRENT);

            GetSecretValueResult getSecretValueResult = clientSecrets.getSecretValue(getSecretValueRequest);

            if (null != getSecretValueResult && null != getSecretValueResult.getSecretString()) {

                JSONObject jsonObj = new JSONObject(getSecretValueResult.getSecretString());

                Secrets secret = new Secrets();
                return secret;
            }

        return null;
    }
}
