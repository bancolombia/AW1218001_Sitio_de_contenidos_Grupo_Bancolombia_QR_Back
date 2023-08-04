package co.com.bancolombia.form.qr.model.generateqr.request;

import io.swagger.annotations.ApiModelProperty;

public class QrInformation {

    @ApiModelProperty(value = "reference", example = "QR - Papas El Mompri")
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "QrInformation{" +
                "reference='" + reference + '\'' +
                '}';
    }
}
