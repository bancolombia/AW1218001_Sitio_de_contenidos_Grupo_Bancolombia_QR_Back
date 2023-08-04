package co.com.bancolombia.form.qr.model.qrcodes.request;

import co.com.bancolombia.form.qr.model.request.QrInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@ApiModel("Qr")
@Component
@Data
public class Qr {
	
	@ApiModelProperty(value = "Channel", example = "Landind Page", required = true)
	@Size(min = 3, max = 100, message = "Channel must be between 3 and 100 characters")
	@NotBlank(message = "Please provide Channel")
	private String channel;
	
	@ApiModelProperty(value = "Qr information", example = "Commerce activity:..., Commerce activity desc:..., Destination product:..., Sale information:..., Commerce information:...", required = true)
	private QrInformation qrInformation;

	public String getChannel() {
		return channel;
	}
	public QrInformation getQrInformation() {
		return qrInformation;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setQrInformation(QrInformation qrInformation) {
		this.qrInformation = qrInformation;
	}

	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Channel: %s%n", getChannel()));
        sb.append(String.format("Qr information: %s%n", getQrInformation()));
        sb.append("*****************************");
        
        return sb.toString();
    }

}
