package co.com.bancolombia.form.qr.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@ApiModel("Qr")
public class CommerceInformation {
	
	@ApiModelProperty(value = "Name", example = "Prueba generacion", required = true)
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	@Pattern(regexp = "^[A-Za-z\\d\\-\\.\\,\\;\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*$", message = "Name contains invalid characters")
	private String name;
	
	@ApiModelProperty(value = "Document", example = "Document type=..., Document number=...", required = true)
	@Valid
	private Document document;

	public String getName() {
		return name;
	}
	public Document getDocument() {
		return document;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	@Override
    public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
        sb.append("\n********** Data **********\n");
        sb.append(String.format("Name: %s%n", getName()));
        sb.append(String.format("Document: %s%n", getDocument()));
        sb.append("*****************************");
        
        return sb.toString();
    }

}
