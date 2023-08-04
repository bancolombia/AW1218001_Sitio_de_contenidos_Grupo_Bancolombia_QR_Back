package co.com.bancolombia.form.qr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "request.security")
@Configuration
public class TrustStoreSetting {

	private String bucketName;
	private String trustStoreName;
	private String trustStoreKeypass;
	private String trustStoreType;

	public String getBucketName() {
		if (bucketName != null) {
			return bucketName.trim();
		} else {
			return null;
		}
	}
	public String getTrustStoreName() {
		if (trustStoreName != null) {
			return trustStoreName.trim();
		} else {
			return null;
		}
	}
	public String getTrustStoreKeypass() {
		return trustStoreKeypass;
	}
	public String getTrustStoreType() {
		return trustStoreType;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public void setTrustStoreName(String trustStoreName) {
		this.trustStoreName = trustStoreName;
	}
	public void setTrustStoreKeypass(String trustStoreKeypass) {
		this.trustStoreKeypass = trustStoreKeypass;
	}
	public void setTrustStoreType(String trustStoreType) {
		this.trustStoreType = trustStoreType;
	}

	@Override
	public String toString() {
		return String.format(
				"TrustStoreSetting [bucketName=%s, trustStoreName=%s, trustStoreKeypass=%s, trustStoreType=%s]",
				getBucketName(), getTrustStoreName(), getTrustStoreKeypass(), getTrustStoreType());
	}

}
