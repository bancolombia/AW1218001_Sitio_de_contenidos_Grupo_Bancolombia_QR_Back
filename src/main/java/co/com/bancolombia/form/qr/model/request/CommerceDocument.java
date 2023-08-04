package co.com.bancolombia.form.qr.model.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CommerceDocument {

    @ApiModelProperty(value = "Document type", example = "CC", required = true)
    @Size(min = 2, max = 3, message = "Identification type must be of 2 and 3 characters")
    @Pattern(regexp = "^CC$|^CD$|^CE$|^NIT$|^PA$|^TI$", message = "IdentificationType must be CC, CD, CE, NIT, PA, TI")
    private String documentType;

    @ApiModelProperty(value = "document Id", example = "12345678890", required = true)
    @Size(min = 3, max = 20, message = "document Id must be between 3 and 20 characters")
    @Pattern(regexp = "^[A-Za-z\\d]*$", message = "document Id contains invalid characters")
    private String documentId;

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return "CommerceDocument{" +
                "documentType='" + documentType + '\'' +
                ", documentId='" + documentId + '\'' +
                '}';
    }
}
