package co.com.bancolombia.form.qr.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Data response")
public class DataGetQrResponse {
	
	@ApiModelProperty(value = "Qr id", example = "68adafce-4c86-4acd-888f-d23c61458d50", required = true)
	private String qrId;
	
	@ApiModelProperty(value = "Generated files", example = "Qr PNG Image, Qr PDF Cockade, Qr PDF Talkative", required = true)
	private GeneratedFiles generatedFiles;

	public String getQrId() {
		return qrId;
	}
	public GeneratedFiles getGeneratedFiles() {
		return generatedFiles;
	}

	public void setQrId(String qrId) {
		this.qrId = qrId;
	}
	public void setGeneratedFiles(GeneratedFiles generatedFiles) {
		this.generatedFiles = generatedFiles;
	}	
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Qr id: %s%n", getQrId()));
        sb.append(String.format("Generated files: %s%n", getGeneratedFiles()));
        sb.append("*****************************");
        
        return sb.toString();
    }
	
}
