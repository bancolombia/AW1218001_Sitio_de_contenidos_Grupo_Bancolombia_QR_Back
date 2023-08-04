package co.com.bancolombia.form.qr.model;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;


@Component
public class Header {
	
	@ApiModelProperty(value = "Type", example = "QR", required = true)
	private String type;
	
	@ApiModelProperty(value = "Id", example = "45aca2c6-9799-4144-bf81-a2f21cefe7e6", required = true)
	private String id;
	
	public String getType() {
		return type;
	}
	public String getId() {
		return id;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("Header [type=%s, id=%s]", getType(), getId());
	}

}
