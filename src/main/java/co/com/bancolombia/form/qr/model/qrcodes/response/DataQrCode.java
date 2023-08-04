package co.com.bancolombia.form.qr.model.qrcodes.response;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.model.Header;
import co.com.bancolombia.form.qr.util.Utilities;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Data qr code")
public class DataQrCode {
	
	private Utilities utilities;
	
	public DataQrCode() {
		utilities = new Utilities();
	}
	
	@ApiModelProperty(value = "Header", example = "_messageId:..., _version:..., _requestDate:..., _responseSize:..., _clientRequest:...", required = true)
	private Header header;
	
	@ApiModelProperty(value = "Qr code", example = "yq6JIzGbbIpwXcKqTtfDZW/TzOCqjrSkH4j5BQWMEhtB+FvkpOSNUNR5nT5rnA2VP...", required = true)
	private String qrCode;
	
	@ApiModelProperty(value = "Qr id", example = "3577aca8-8bee-47a1-8726-825b73ea5f25", required = true)
	private String qrId;
	
	@ApiModelProperty(value = "Qr image", example = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnM...", required = true)
	private String qrImage;

	public Header getHeader() {
		return header;
	}
	public String getQrCode() {
		return qrCode;
	}
	public String getQrId() {
		return qrId;
	}
	public String getQrImage() {
		return qrImage;
	}
	
	public void setHeader(Header header) {
		this.header = header;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public void setQrId(String qrId) {
		this.qrId = qrId;
	}
	public void setQrImage(String qrImage) {
		this.qrImage = qrImage;
	}

	@Override
	public String toString() {
		return String.format("DataResponseQrCode [header=%s, qrCode=%s, qrId=%s, qrImage=%s]", getHeader(), getQrCode(), getQrId(), ((getQrImage() == null || getQrImage().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY));
	}

}
