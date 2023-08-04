package co.com.bancolombia.form.qr.model.generateqr.request;

import java.util.List;


public class RenewDataRequest {

    private List<RenewRequest> data;

    public RenewDataRequest() {
    }

    public List<RenewRequest> getData() {
        return data;
    }

    public void setData(List<RenewRequest> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RenewDataRequest{" +
                "data=" + data +
                '}';
    }
}
