package co.com.bancolombia.form.qr.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;


@ApiModel("Habeas data")
@Component
public class HabeasData {
	
	@ApiModelProperty(value = "Agreement date time", example = "2019-08-21T21:40:23", required = true)
	@Size(min = 19, max = 19, message = "Agreement date time must be 19 characters")
	@NotBlank(message = "Please provide a Agreement date time")
	@Pattern(regexp = "^20\\d{2}\\-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])T(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$", message = "Agreement date time contains invalid characters")
	private String agreementDateTime;
	
	@ApiModelProperty(value = "version", example = "V2.6", required = true)
	private String version;
	
	public String getAgreementDateTime() {
		return agreementDateTime;
	}
	public String getVersion() {
		return version;
	}
	
	public void setAgreementDateTime(String agreementDateTime) {
		this.agreementDateTime = agreementDateTime;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return String.format("HabeasData [agreementDateTime=%s, version=%s]", getAgreementDateTime(), getVersion());
	}

}
