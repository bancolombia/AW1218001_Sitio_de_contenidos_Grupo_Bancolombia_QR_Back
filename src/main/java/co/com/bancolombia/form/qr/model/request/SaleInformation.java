package co.com.bancolombia.form.qr.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@ApiModel("Sale information")
public class SaleInformation {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ApiModelProperty(value = "reference", example = "QR - Papas El Mompri", required = true)
	@Size(min = 3, max = 50, message = "Reference must be between 3 and 50 characters")
	@Pattern(regexp = "^[A-Za-z\\d\\-\\.\\,\\;\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*$", message = "Reference contains invalid characters")
	private String reference;
	
	@ApiModelProperty(value = "currency", example = "COP", required = true)
	@Size(min = 2, max = 3, message = "Currency must be between 2 and 3 characters")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Currency contains invalid characters")
	private String currency;

	public String getCurrency() {
		return currency;
	}
	public String getReference() {
		return reference;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Reference: %s%n", getReference()));
        sb.append(String.format("Currency: %s%n", getCurrency()));
        sb.append("*****************************");
        
        return sb.toString();
    }

}
