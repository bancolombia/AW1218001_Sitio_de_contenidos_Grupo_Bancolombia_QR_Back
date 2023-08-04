package co.com.bancolombia.form.qr.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Data Request")
public class DataGetQr {
    
	@ApiModelProperty(value = "Qr information", example = "Commerce activity desc:..., Destination product:..., Sale information:..., Commerce information:...", required = true)
    private QrInformation qrInformation;

	public QrInformation getQrInformation() {
		return qrInformation;
	}

	public void setQrInformation(QrInformation qrInformation) {
		this.qrInformation = qrInformation;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Qr information: %s%n", getQrInformation()));
        sb.append("*****************************");
        
        return sb.toString();
    }
	
}
