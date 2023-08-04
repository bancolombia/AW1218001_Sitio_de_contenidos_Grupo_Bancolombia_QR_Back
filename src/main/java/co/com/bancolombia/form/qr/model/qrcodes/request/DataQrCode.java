package co.com.bancolombia.form.qr.model.qrcodes.request;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;

@ApiModel("Data qr code")
@Component
public class DataQrCode {
	
	private Qr qr;

	public Qr getQr() {
		return qr;
	}

	public void setQr(Qr qr) {
		this.qr = qr;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Qr: %s%n", getQr()));
        sb.append("*****************************");
        
        return sb.toString();
    }

}
