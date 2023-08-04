package co.com.bancolombia.form.qr.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import co.com.bancolombia.form.qr.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Generated files")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class GeneratedFiles {
	
	@ApiModelProperty(value = "Qr PNG Image: It's a file representation using base 64 string", example = "PD94bWwgdmVyc2lvbj0iMS4wIiB...", required = true)
	private String qrImage;
	
	@ApiModelProperty(value = "Qr PDF Cockade: It's a file representation using base 64 string", example = "PD94bWwgdmVyc2lvbj0iMS4wIiB...", required = true)
	private String qrPdfCockade;
	
	@ApiModelProperty(value = "Qr PDF Talkative: It's a file representation using base 64 string", example = "PD94bWwgdmVyc2lvbj0iMS4wIiB...", required = true)
	private String qrPdfTalkative;
	
	public String getQrImage() {
		return qrImage;
	}
	public String getQrPdfCockade() {
		return qrPdfCockade;
	}
	public String getQrPdfTalkative() {
		return qrPdfTalkative;
	}
	
	public void setQrImage(String qrImage) {
		this.qrImage = qrImage;
	}
	public void setQrPdfCockade(String qrPdfCockade) {
		this.qrPdfCockade = qrPdfCockade;
	}
	public void setQrPdfTalkative(String qrPdfTalkative) {
		this.qrPdfTalkative = qrPdfTalkative;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Qr image: %s%n", ((getQrImage() == null || getQrImage().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)));
        sb.append(String.format("Qr pdf cockade: %s%n", ((getQrPdfCockade() == null || getQrPdfCockade().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)));
        sb.append(String.format("Qr pdf talkative: %s%n", ((getQrPdfTalkative() == null || getQrPdfTalkative().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)));
        sb.append("*****************************");
        
        return sb.toString();
    }
	
}
