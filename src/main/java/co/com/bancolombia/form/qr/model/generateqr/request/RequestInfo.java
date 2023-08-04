package co.com.bancolombia.form.qr.model.generateqr.request;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.model.request.TermsAndConditions;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RequestInfo {

    @ApiModelProperty(value = "request Type", example = "Otros", required = true)
    @Size(min = 3, max = 20, message = "requestType must be between 3 and 20 characters")
    @Pattern(regexp = Constant.REGEX_BLACK_LIST, message = "requestType contains invalid characters")
    private String requestType;

    @ApiModelProperty(value = "request Date", example = "2018-07-16", required = true)
    private String requestDate;

    @ApiModelProperty(value = "notification Email", example = "sedeCentro@gmail.com", required = true)
    @Email(message = "notificationEmail contains invalid characters")
    private String notificationEmail;

    private List<TermsAndConditions> termsAndConditions;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public List<TermsAndConditions> getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(List<TermsAndConditions> termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "requestType='" + requestType + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", notificationEmail='" + notificationEmail + '\'' +
                ", termsAndConditions=" + termsAndConditions +
                '}';
    }
}
