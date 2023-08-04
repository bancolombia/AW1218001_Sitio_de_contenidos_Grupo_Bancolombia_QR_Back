package co.com.bancolombia.form.qr.model.request;

import co.com.bancolombia.form.qr.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContactInfo {

    @ApiModelProperty(value = "quantity", example = "5")
    @JsonProperty("quantity")
    private String quantity;

    @ApiModelProperty(value = "address", example = "Calle Falsa 123", required = true)
    @Size(min = Constant.DELIVERY_INF_MIN_SIZE_ADDRESS_VALUE, max = Constant.DELIVERY_INF_MAX_SIZE_ADDRESS_VALUE, message = Constant.DELIVERY_INF_ADDRESS_VALUE_MESSAGE)
    @Pattern(regexp = Constant.REGEX_BLACK_LIST, message = "Address contains invalid characters")
    @NotNull(message = Constant.DELIVERY_INF_ADDRESS_REQUIRED)
    private String address;

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

    @ApiModelProperty(value = "schedule", example = "8am - 12m")
    private String schedule;

    @ApiModelProperty(value = "contactPhoneNumber", example = "3224432324", required = true)
    @Size(min = Constant.DELIVERY_INF_MIN_SIZE_CONTACT_PHONE_VALUE, max = Constant.DELIVERY_INF_MAX_SIZE_CONTACT_PHONE_VALUE, message = Constant.DELIVERY_INF_CONTACT_PHONE_VALUE_MESSAGE)
    @Pattern(regexp = Constant.DELIVERY_INF_CONTACT_PHONE_REGEX, message = "Contact phone contains invalid characters")
    @NotNull(message = Constant.DELIVERY_INF_CONTACT_PHONE_REQUIRED)
    private String contactPhoneNumber;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "quantity=" + quantity +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", city='" + city + '\'' +
                ", schedule='" + schedule + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'' +
                '}';
    }
}
