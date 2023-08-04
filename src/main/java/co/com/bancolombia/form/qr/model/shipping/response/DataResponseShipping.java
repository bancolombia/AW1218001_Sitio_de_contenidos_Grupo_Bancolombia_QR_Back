package co.com.bancolombia.form.qr.model.shipping.response;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.bancolombia.form.qr.model.Header;
import io.swagger.annotations.ApiModelProperty;


@Component
public class DataResponseShipping {

	@ApiModelProperty(value = "Header", example = "_messageId:..., _version:..., _requestDate:..., _responseSize:..., _clientRequest:...", required = true)
	private Header header;

	@ApiModelProperty(value = "Id request shipping", example = "c049fd74-da74-4033-a897-5ad87f186201", required = true)
	private String idrequestShipping;

	@ApiModelProperty(value = "Id QR", example = "68adafce-4c86-4acd-888f-d23c61458d50", required = true)
	private String idQR;

	@ApiModelProperty(value = "Qr quantity", example = "5", required = true)
	@JsonProperty("qrCuantity")
	private int qrQuantity;

	@ApiModelProperty(value = "Address", example = "Calle Falsa 123", required = true)
	private String address;

	@ApiModelProperty(value = "department", example = "Antioquia", required = true)
	private String department;

	@ApiModelProperty(value = "city", example = "Medellin", required = true)
	private String city;

	@ApiModelProperty(value = "deliveryTime", example = "8am - 12m", required = true)
	private String deliveryTime;

	@ApiModelProperty(value = "contactPhone", example = "32244323243", required = true)
	private String contactPhone;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getIdrequestShipping() {
		return idrequestShipping;
	}

	public void setIdrequestShipping(String idrequestShipping) {
		this.idrequestShipping = idrequestShipping;
	}

	public String getIdQR() {
		return idQR;
	}

	public void setIdQR(String idQR) {
		this.idQR = idQR;
	}

	public int getQrQuantity() {
		return qrQuantity;
	}

	public void setQrQuantity(int qrQuantity) {
		this.qrQuantity = qrQuantity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Override
	public String toString() {
		return String.format(
				"DataShipping [header=%s, idrequestShipping=%s, idQR=%s, qrQuantity=%s, address=%s, department=%s, city=%s, deliveryTime=%s, contactPhone=%s]",
				getHeader(), getIdrequestShipping(), getIdQR(), getQrQuantity(), getAddress(), getDepartment(),
				getCity(), getDeliveryTime(), getContactPhone());
	}

}
