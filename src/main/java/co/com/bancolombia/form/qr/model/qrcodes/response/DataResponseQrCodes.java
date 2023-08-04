package co.com.bancolombia.form.qr.model.qrcodes.response;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import co.com.bancolombia.form.qr.model.Error;
import co.com.bancolombia.form.qr.model.Meta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Component
@Data
@ApiModel("Data response qr codes")

public class DataResponseQrCodes {
	
	@ApiModelProperty(value = "Meta", example = "_messageId:..., _version:..., _requestDate:..., _responseSize:..., _clientRequest:...", required = true)
	private Meta meta;
	
	@ApiModelProperty(value = "Data qr codes", example = "Header:..., Qr code:..., Qr id:..., Qr image:...", required = true)
	private List<DataQrCode> data;
	
	@ApiModelProperty(value = "Errors", example = "href:..., status:..., code:...., title:..., detail:...", required = true)
	private List<Error> errors;
	
	@ApiModelProperty(value = "Links", example = "https://api.us.apiconnect.ibmcloud.com/bancolombiabluemix-dev/testing/tec/v1/sales-services/servicing/payment-initiation/internal/qr-codes", required = true)
	private DataResponseQrLinks links;

	public List<DataQrCode> getData() {
		return data;
	}

	public void setData(List<DataQrCode> data){
		this.data = data;
	}

	public DataResponseQrLinks getLinks() {
		return links;
	}

	public void setLinks(DataResponseQrLinks links) {
		this.links = links;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
