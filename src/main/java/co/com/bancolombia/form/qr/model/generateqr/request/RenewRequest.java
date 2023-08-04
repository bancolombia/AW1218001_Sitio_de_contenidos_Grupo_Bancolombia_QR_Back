package co.com.bancolombia.form.qr.model.generateqr.request;

import jakarta.validation.Valid;


public class RenewRequest {

    @Valid
    private RequestInfo requestInfo;

    @Valid
    private QrRenew qr;

    @Valid
    private QrShipping qrShipping;

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public QrRenew getQr() {
        return qr;
    }

    public void setQr(QrRenew qr) {
        this.qr = qr;
    }

    public QrShipping getQrShipping() {
        return qrShipping;
    }

    public void setQrShipping(QrShipping qrShipping) {
        this.qrShipping = qrShipping;
    }

    @Override
    public String toString() {
        return "RenewRequest{" +
                "requestInfo=" + requestInfo +
                ", qr=" + qr +
                ", qrShipping=" + qrShipping +
                '}';
    }
}
