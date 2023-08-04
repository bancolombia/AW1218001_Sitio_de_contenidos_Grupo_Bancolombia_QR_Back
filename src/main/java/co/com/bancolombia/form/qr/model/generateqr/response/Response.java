package co.com.bancolombia.form.qr.model.generateqr.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private List<String> idRequest;
    private String idQR;
    private String image;


    public Response() {
    }

    public List<String> getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(List<String> idRequest) {
        this.idRequest = idRequest;
    }

    public String getIdQR() {
        return idQR;
    }

    public void setIdQR(String idQR) {
        this.idQR = idQR;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Response{" +
                "idRequest=" + idRequest +
                ", idQR='" + idQR + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
