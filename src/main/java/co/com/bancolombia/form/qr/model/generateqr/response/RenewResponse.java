package co.com.bancolombia.form.qr.model.generateqr.response;

import java.util.List;

public class RenewResponse {

    private List<Response> data;

    public RenewResponse() {
    }

    public List<Response> getData() {
        return data;
    }

    public void setData(List<Response> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RenewResponse{" +
                "data=" + data +
                '}';
    }
}
