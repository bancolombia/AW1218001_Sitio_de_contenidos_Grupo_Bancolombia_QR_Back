package co.com.bancolombia.form.qr.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@ApiModel("Destination product")
public class DestinationProduct {
	
	@ApiModelProperty(value = "Product type: S=SAVINGS, D=DEPOSIT", example = "S", required = true)
	@Pattern(regexp = "^S$|^D$", message = "Produc type must be S=SAVINGS, D=DEPOSIT")
	@NotBlank(message = "Please provide a Product type")
	private String productType;
	
	@ApiModelProperty(value = "Product number", example = "S", required = true)
	@Size(min = 11, max = 17, message = "Product number must be between 17 and 11 characters")
	@NotBlank(message = "Please provide a Product number")
	private String productNumber;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Product type: %s%n", getProductType()));
        sb.append(String.format("Product number: %s%n", getProductNumber()));
        sb.append("*****************************");
        
        return sb.toString();
    }

}
