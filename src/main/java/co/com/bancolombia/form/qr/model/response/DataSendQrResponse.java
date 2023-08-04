package co.com.bancolombia.form.qr.model.response;

import co.com.bancolombia.form.qr.model.DeliveryInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Data response")
public class DataSendQrResponse {
	
	@ApiModelProperty(value = "Qr id", example = "68adafce-4c86-4acd-888f-d23c61458d50", required = true)
	private String qrId;
	
	@ApiModelProperty(value = "Request shipping id", example = "c049fd74-da74-4033-a897-5ad87f186201", required = true)
	private String requestShippingId;
	
	@ApiModelProperty(value = "Generated files", example = "Qr PNG Image, Qr PDF Cockade, Qr PDF Talkative", required = true)
	private GeneratedFiles generatedFiles;
	
	@ApiModelProperty(value = "Delivery information", example = "Delivery information request data", required = true)
	private DeliveryInformation deliveryInformation;
	
	public String getQrId() {
		return qrId;
	}
	public String getRequestShippingId() {
		return requestShippingId;
	}
	public GeneratedFiles getGeneratedFiles() {
		return generatedFiles;
	}
	public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}
	
	public void setQrId(String qrId) {
		this.qrId = qrId;
	}
	public void setRequestShippingId(String requestShippingId) {
		this.requestShippingId = requestShippingId;
	}
	public void setGeneratedFiles(GeneratedFiles generatedFiles) {
		this.generatedFiles = generatedFiles;
	}
	public DeliveryInformation getDeliveryInformation() {
		return deliveryInformation;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("qrId: %s%n", getQrId()));
		sb.append(String.format("requestShippingId: %s%n", getRequestShippingId()));
        sb.append(String.format("Generated files: %s%n", getGeneratedFiles()));
        sb.append(String.format("Delivery information: %s%n", getDeliveryInformation()));
        sb.append("*****************************");
        
        return sb.toString();
    }
	
}
