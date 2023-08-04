package co.com.bancolombia.form.qr.model.generateqr.response;

import java.util.List;

public class MigrationResponse {

    private List<String> idRequest;

    public MigrationResponse() {
        // TODO document why this constructor is empty
    }

    public List<String> getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(List<String> idRequest) {
        this.idRequest = idRequest;
    }

    @Override
    public String toString() {
        return "MigrationResponse{" +
                "idRequest=" + idRequest +
                '}';
    }
}
