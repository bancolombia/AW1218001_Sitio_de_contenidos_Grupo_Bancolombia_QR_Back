package co.com.bancolombia.form.qr.model.qrcodes.request;

import java.util.List;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Data request qr codes")
@Component
public class DataRequestQrCodes {
	
	@ApiModelProperty(value = "Data", example = "List data qr", required = true)
	private List<DataQrCode> data;

	public List<DataQrCode> getData() {
		return data;
	}

	public void setData(List<DataQrCode> data) {
		this.data = data;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Data: %s%n", getData()));
        sb.append("*****************************");
        
        return sb.toString();
    }

}
