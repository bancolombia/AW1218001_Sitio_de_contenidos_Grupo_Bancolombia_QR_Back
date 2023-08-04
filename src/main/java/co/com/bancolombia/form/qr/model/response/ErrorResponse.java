package co.com.bancolombia.form.qr.model.response;

import co.com.bancolombia.form.qr.model.Error;

import java.util.List;

public class ErrorResponse {

    private List<Error> errors;

    public List<Error> getData() {
        return errors;
    }

    public void setData(List<Error> data) {
        this.errors = data;
    }

    public ErrorResponse() {
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "data=" + errors +
                '}';
    }
}
