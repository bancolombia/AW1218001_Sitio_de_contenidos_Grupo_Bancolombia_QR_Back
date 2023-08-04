package co.com.bancolombia.form.qr.model.request;

import co.com.bancolombia.form.qr.model.DeliveryInformation;
import co.com.bancolombia.form.qr.model.HabeasData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;


@ApiModel("Data Request")
public class DataSendQr {
    
	@ApiModelProperty(value = "Qr information", example = "Commerce activity desc:..., Destination product:..., Sale information:..., Commerce information:...", required = true)
	@Valid
	private QrInformation qrInformation;
	
	@ApiModelProperty(value = "Delivery information", example = "Qr quantity:..., Department:..., City:..., Address:..., Contact phone:..., Delivery time:...", required = true)
	@Valid
	private DeliveryInformation deliveryInformation;
	
	@ApiModelProperty(value = "Habeas data", example = "Agreement date time:...", required = true)
	@Valid
    private HabeasData habeasData;

	public QrInformation getQrInformation() {
		return qrInformation;
	}
	public DeliveryInformation getDeliveryInformation() {
		return deliveryInformation;
	}
	public HabeasData getHabeasData() {
		return habeasData;
	}

	public void setQrInformation(QrInformation qrInformation) {
		this.qrInformation = qrInformation;
	}
	public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}
	public void setHabeasData(HabeasData habeasData) {
		this.habeasData = habeasData;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Qr information: %s%n", getQrInformation()));
        sb.append(String.format("Delivery information: %s%n", getDeliveryInformation()));
        sb.append(String.format("Habeas data: %s%n", getHabeasData()));
        sb.append("*****************************");
        
        return sb.toString();
    }
	
}
