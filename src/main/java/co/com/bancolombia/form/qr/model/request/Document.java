package co.com.bancolombia.form.qr.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@ApiModel("Document")
public class Document {
	
	@ApiModelProperty(value = "Document type", example = "CC", required = true)
	@Size(min = 2, max = 3, message = "Identification type must be of 2 and 3 characters")
	@Pattern(regexp = "^CC$|^CD$|^CE$|^NIT$|^PA$|^TI$", message = "IdentificationType must be CC, CD, CE, NIT, PA, TI")
	private String documentType;
	
	@ApiModelProperty(value = "Document number", example = "12345678890", required = true)
	@Size(min = 3, max = 20, message = "Document number must be between 3 and 20 characters")
	@Pattern(regexp = "^[A-Za-z\\d]*$", message = "Document number contains invalid characters")
	private String documentNumber;

	public String getDocumentType() {
		return documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("\n********** Data **********\n");
		sb.append(String.format("Document type: %s%n", getDocumentType()));
		sb.append(String.format("Document number: %s%n", getDocumentNumber()));
		sb.append("*****************************");

		return sb.toString();
	}

}
