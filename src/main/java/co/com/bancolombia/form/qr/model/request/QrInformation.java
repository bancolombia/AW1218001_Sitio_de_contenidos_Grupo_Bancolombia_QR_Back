package co.com.bancolombia.form.qr.model.request;

import co.com.bancolombia.form.qr.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@ApiModel("Qr information")
public class QrInformation {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ApiModelProperty(value = "Commerce activity", example = "Otros", required = false)
	@Size(min = 3, max = 140, message = "Commerce activity must be between 3 and 140 characters")
	@Pattern(regexp = Constant.REGEX_BLACK_LIST, message = "Commerce activity contains invalid characters")
	private String commerceActivity;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@ApiModelProperty(value = "Commerce activity desc", example = "Artesanias", required = true)
	@Size(min = 3, max = 140, message = "Commerce activity description must be between 3 and 140 characters")
	@Pattern(regexp = Constant.REGEX_BLACK_LIST, message = "Commerce activity description contains invalid characters")
	@NotBlank(message = "Please provide a Commerce activity description")
	private String commerceActivityDesc;
	
	@ApiModelProperty(value = "Destination product", example = "Product type:..., Product number:...", required = true)
	@Valid
	private DestinationProduct destinationProduct;

	@ApiModelProperty(value = "Sale information", example = "currency:...", required = true)
	@Valid
	private SaleInformation saleInformation; 
	
	@ApiModelProperty(value = "Commerce information", example = "name:...", required = true)
	@Valid
	private CommerceInformation commerceInformation;
	
	public String getCommerceActivity() {
		return commerceActivity;
	}
	public String getCommerceActivityDesc() {
		return commerceActivityDesc;
	}
	public DestinationProduct getDestinationProduct() {
		return destinationProduct;
	}
	public SaleInformation getSaleInformation() {
		return saleInformation;
	}
	public CommerceInformation getCommerceInformation() {
		return commerceInformation;
	}

	public void setCommerceActivity(String commerceActivity) {
		this.commerceActivity = commerceActivity;
	}
	public void setCommerceActivityDesc(String commerceActivityDesc) {
		this.commerceActivityDesc = commerceActivityDesc;
	}
	public void setDestinationProduct(DestinationProduct destinationProduct) {
		this.destinationProduct = destinationProduct;
	}
	public void setSaleInformation(SaleInformation saleInformation) {
		this.saleInformation = saleInformation;
	}
	public void setCommerceInformation(CommerceInformation commerceInformation) {
		this.commerceInformation = commerceInformation;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Commerce activity: %s%n", getCommerceActivity()));
        sb.append(String.format("Commerce activity desc: %s%n", getCommerceActivityDesc()));
        sb.append(String.format("Destination product: %s%n", getDestinationProduct()));
        sb.append(String.format("Sale information: %s%n", getSaleInformation()));
        sb.append(String.format("Commerce information: %s%n", getCommerceInformation()));
        sb.append("*****************************");
        
        return sb.toString();
    }

}
