package co.com.bancolombia.form.qr.model.shipping.request;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Data shipping")
@Component
public class DataRequestShipping {
	
	@ApiModelProperty(value = "Request shipping", example = "Data request shipping", required = true)
	private RequestShipping requestShipping;

	public RequestShipping getRequestShipping() {
		return requestShipping;
	}

	public void setRequestShipping(RequestShipping requestShipping) {
		this.requestShipping = requestShipping;
	}

	@Override
	public String toString() {
		return String.format("DataShipping [requestShipping=%s]", getRequestShipping());
	}

}
