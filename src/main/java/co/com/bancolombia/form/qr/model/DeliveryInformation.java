package co.com.bancolombia.form.qr.model;

import co.com.bancolombia.form.qr.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;


@ApiModel("Delivery information")
@Component
public class DeliveryInformation {

	@ApiModelProperty(value = "qrCuantity", example = "5", required = true)
	@Min(value = Constant.DELIVERY_INF_MIN_QUANTITY_VALUE, message = Constant.DELIVERY_INF_MIN_QUANTITY_VALUE_MESSAGE)
	@Max(value = Constant.DELIVERY_INF_MAX_QUANTITY_VALUE, message = Constant.DELIVERY_INF_MAX_QUANTITY_VALUE_MESSAGE)
	@JsonProperty("qrCuantity")
	private int qrQuantity;
	
	@ApiModelProperty(value = "department", example = "Antioquia", required = true)
	@Size(min = Constant.DELIVERY_INF_MIN_SIZE_DEPARTMENT_VALUE, max = Constant.DELIVERY_INF_MAX_SIZE_DEPARTMENT_VALUE, message = Constant.DELIVERY_INF_DEPARTMENT_VALUE_MESSAGE)
	@Pattern(regexp = Constant.DELIVERY_INF_DEPARTMENT_REGEX, message = "Department contains invalid characters")
	@NotNull(message = Constant.DELIVERY_INF_DEPARTMENT_REQUIRED)
	private String department;
	
	@ApiModelProperty(value = "city", example = "Medellin", required = true)
	@Size(min = Constant.DELIVERY_INF_MIN_SIZE_CITY_VALUE, max = Constant.DELIVERY_INF_MAX_SIZE_CITY_VALUE, message = Constant.DELIVERY_INF_CITY_VALUE_MESSAGE)
	@Pattern(regexp = Constant.DELIVERY_INF_CITY_REGEX, message = "City contains invalid characters")
	@NotNull(message = Constant.DELIVERY_INF_CITY_REQUIRED)
	private String city;
	
	@ApiModelProperty(value = "address", example = "Calle Falsa 123", required = true)
	@Size(min = Constant.DELIVERY_INF_MIN_SIZE_ADDRESS_VALUE, max = Constant.DELIVERY_INF_MAX_SIZE_ADDRESS_VALUE, message = Constant.DELIVERY_INF_ADDRESS_VALUE_MESSAGE)
	@Pattern(regexp = Constant.REGEX_BLACK_LIST, message = "Address contains invalid characters")
	@NotNull(message = Constant.DELIVERY_INF_ADDRESS_REQUIRED)
	private String address;
	
	@ApiModelProperty(value = "contactPhone", example = "3224432324", required = true)
	@Size(min = Constant.DELIVERY_INF_MIN_SIZE_CONTACT_PHONE_VALUE, max = Constant.DELIVERY_INF_MAX_SIZE_CONTACT_PHONE_VALUE, message = Constant.DELIVERY_INF_CONTACT_PHONE_VALUE_MESSAGE)
	@Pattern(regexp = Constant.DELIVERY_INF_CONTACT_PHONE_REGEX, message = "Contact phone contains invalid characters")
	@NotNull(message = Constant.DELIVERY_INF_CONTACT_PHONE_REQUIRED)
	private String contactPhone;
	
	@ApiModelProperty(value = "deliveryTime", example = "8am - 12m", required = true)
	@Size(min = Constant.DELIVERY_INF_MIN_SIZE_DELIVERY_TIME_VALUE, max = Constant.DELIVERY_INF_MAX_SIZE_DELIVERY_TIME_VALUE, message = Constant.DELIVERY_INF_DELIVERY_TIME_VALUE_MESSAGE)
	@Pattern(regexp = Constant.DELIVERY_INF_DELIVERY_TIME_REGEX, message = "Delivery time contains invalid characters")
	@NotNull(message = Constant.DELIVERY_INF_DELIVERY_TIME_REQUIRED)
	private String deliveryTime;

	public int getQrQuantity() {
		return qrQuantity;
	}

	public String getDepartment() {
		return department;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setQrQuantity(int qrQuantity) {
		this.qrQuantity = qrQuantity;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@Override
	public String toString() {
		return String.format(
				"DeliveryInformation [qrQuantity=%s, department=%s, city=%s, address=%s, contactPhone=%s, deliveryTime=%s]",
				getQrQuantity(), getDepartment(), getCity(), getAddress(), getContactPhone(), getDeliveryTime());
	}

}
