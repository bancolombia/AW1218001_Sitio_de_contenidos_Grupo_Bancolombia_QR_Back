package co.com.bancolombia.form.qr.model.shipping.request;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.bancolombia.form.qr.model.DeliveryInformation;
import co.com.bancolombia.form.qr.model.HabeasData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Request Shipping")
@Component
public class RequestShipping {
	
	@ApiModelProperty(value = "Channel", example = "Landind Page", required = true)
	private String channel;
	
	@ApiModelProperty(value = "qrId", example = "eea4aad1-2ce3-4acd-8148-0dd3a7f71767", required = true)
	private String qrId;
	
	@ApiModelProperty(value = "type", example = "Nuevo", required = true)
	private String type;
	
	@ApiModelProperty(value = "Delivery information", example = "The delivery information values", required = true)
	private List<DeliveryInformation> deliveryInformation;
	
	@ApiModelProperty(value = "Habeas data", example = "The habeas data values", required = true)
	private HabeasData habeasData;
	
	public String getChannel() {
		return channel;
	}
	public String getQrId() {
		return qrId;
	}
	public String getType() {
		return type;
	}
	public List<DeliveryInformation> getDeliveryInformation() {
		return deliveryInformation;
	}
	public HabeasData getHabeasData() {
		return habeasData;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setQrId(String qrId) {
		this.qrId = qrId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDeliveryInformation(List<DeliveryInformation> deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}
	public void setHabeasData(HabeasData habeasData) {
		this.habeasData = habeasData;
	}
	@Override
	public String toString() {
		return String.format("RequestShipping [channel=%s, qrId=%s, type=%s, deliveryInformation=%s, habeasData=%s]", getChannel(), getQrId(), getType(), getDeliveryInformation(), getHabeasData());
	}	

}
