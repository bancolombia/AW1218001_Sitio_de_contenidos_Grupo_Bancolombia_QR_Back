package co.com.bancolombia.form.qr.model.request;

import io.swagger.annotations.ApiModelProperty;

public class TermsAndConditions {

    @ApiModelProperty(value = "type", example = "Entre Cuentas")
    private String type;

    @ApiModelProperty(value = "value")
    private String value;

    @ApiModelProperty(value = "date", example = "2018-07-16T14:30:00")
    private String date;

    @ApiModelProperty(value = "version", example = "V2.6")
    private String version;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TermsAndConditions{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", date='" + date + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
