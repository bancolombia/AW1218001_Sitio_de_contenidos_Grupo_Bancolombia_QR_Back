package co.com.bancolombia.form.qr.model.generateqr.request;

import co.com.bancolombia.form.qr.model.request.Creator;
import co.com.bancolombia.form.qr.model.request.DestinationTrx;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;

public class QrRenew {

    @Valid
    @ApiModelProperty(value = "Commerce Document", required = true)
    private DestinationTrx destinationTrx;

    @ApiModelProperty(value = "qr Information")
    private QrInformation qrInformation;

    @ApiModelProperty(value = "creator")
    private Creator creator;

    public DestinationTrx getDestinationTrx() {
        return destinationTrx;
    }

    public void setDestinationTrx(DestinationTrx destinationTrx) {
        this.destinationTrx = destinationTrx;
    }

    public QrInformation getQrInformation() {
        return qrInformation;
    }

    public void setQrInformation(QrInformation qrInformation) {
        this.qrInformation = qrInformation;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "QrRenew{" +
                "destinationTrx=" + destinationTrx +
                ", qrInformation=" + qrInformation +
                ", creator=" + creator +
                '}';
    }
}
