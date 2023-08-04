package co.com.bancolombia.form.qr.model.shipping.response;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.bancolombia.form.qr.model.Error;
import co.com.bancolombia.form.qr.model.Meta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Data response shipping")
@Component
public class DataResponseShippings {
	
	@ApiModelProperty(value = "Meta", example = "_messageId:..., _version:..., _requestDate:..., _responseSize:..., _clientRequest:...", required = true)
	private Meta meta;
	
	@ApiModelProperty(value = "Data shipping", example = "Header:..., Id request shipping:..., Id QR:..., Qr cuantity:..., Address:...", required = true)
	private List<DataResponseShipping> data;
	
	@ApiModelProperty(value = "Errors", example = "href:..., status:..., code:...., title:..., detail:...", required = true)
	private List<Error> errors;

	public Meta getMeta() {
		return meta;
	}
	public List<DataResponseShipping> getData() {
		return data;
	}
	public List<Error> getErrors() {
		return errors;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public void setData(List<DataResponseShipping> data) {
		this.data = data;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return String.format("DataResponseShipping [meta=%s, data=%s, errors=%s]", getMeta(), getData(), getErrors());
	}

}
