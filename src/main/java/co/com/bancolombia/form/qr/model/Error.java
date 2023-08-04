package co.com.bancolombia.form.qr.model;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;


@Component
public class Error {

	@ApiModelProperty(value = "href", example = "https://tools.ietf.org/html/rfc7231#section-6.6.1", required = true)
	private String href;
	@ApiModelProperty(value = "status", example = "500", required = true)
	private String status;
	@ApiModelProperty(value = "code", example = "SP500", required = true)
	private String code;
	@ApiModelProperty(value = "title", example = "Internal Server Error", required = true)
	private String title;
	@ApiModelProperty(value = "detail", example = "Error leyendo la peticion del servidor {\"status\":503,\"title\":\"Service Unavailable\"}", required = true)
	private String detail;
	
	public String getHref() {
		return href;
	}
	public String getStatus() {
		return status;
	}
	public String getCode() {
		return code;
	}
	public String getTitle() {
		return title;
	}
	public String getDetail() {
		return detail;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return String.format("Error [href=%s, status=%s, code=%s, title=%s, detail=%s]", getHref(), getStatus(), getCode(), getTitle(), getDetail());
	}
	
}
