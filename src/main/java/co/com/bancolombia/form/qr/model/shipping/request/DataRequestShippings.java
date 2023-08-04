package co.com.bancolombia.form.qr.model.shipping.request;

import java.util.List;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Data request shippings")
@Component
public class DataRequestShippings {
	
	@ApiModelProperty(value = "Data", example = "List data shipping", required = true)
	private List<DataRequestShipping> data;

	public List<DataRequestShipping> getData() {
		return data;
	}

	public void setData(List<DataRequestShipping> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("DataRequestShippings [data=%s]", getData());
	}

}
