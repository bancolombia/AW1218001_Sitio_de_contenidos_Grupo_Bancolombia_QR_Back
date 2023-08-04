package co.com.bancolombia.form.qr.model.generateqr.request;

import co.com.bancolombia.form.qr.model.request.ContactInfo;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;

import java.util.List;

public class QrShipping {

    @ApiModelProperty(value = "physical Request", example = "true", required = true)
    private Boolean physicalRequest;

    @ApiModelProperty(value = "contactInfo", required = true)
    @Valid
    private List<ContactInfo> contactInfo;

    public Boolean getPhysicalRequest() {
        return physicalRequest;
    }

    public void setPhysicalRequest(Boolean physicalRequest) {
        this.physicalRequest = physicalRequest;
    }

    public List<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "QrShipping{" +
                "physicalRequest=" + physicalRequest +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
