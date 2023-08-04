package co.com.bancolombia.form.qr.model.request;

import co.com.bancolombia.form.qr.Constant;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DestinationTrx {

    @Valid
    @ApiModelProperty(value = "Commerce Document", example = "Document type=..., Document Id=...", required = true)
    private CommerceDocument commerceDocument;

    @ApiModelProperty(value = "commerce Name", example = "Papas el Mono", required = true)
    @Size(min = 3, max = 100, message = "commerce Name must be between 3 and 100 characters")
    @Pattern(regexp = Constant.REGEX_BLACK_LIST, message = "commerce Name contains invalid characters")
    private String commerceName;

    @ApiModelProperty(value = "commerce Activity Name", example = "Otros", required = true)
    @Size(min = 3, max = 100, message = "commerce Activity Name must be between 3 and 100 characters")
    @Pattern(regexp = Constant.REGEX_BLACK_LIST, message = "commerce Activity Name contains invalid characters")
    private String commerceActivityName;

    @ApiModelProperty(value = "beneficiary Product Type", example = "5")
    private String beneficiaryProductType;

    @ApiModelProperty(value = "beneficiary Product Id", example = "40672400050")
    private String beneficiaryProductId;

    public CommerceDocument getCommerceDocument() {
        return commerceDocument;
    }

    public void setCommerceDocument(CommerceDocument commerceDocument) {
        this.commerceDocument = commerceDocument;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }

    public String getCommerceActivityName() {
        return commerceActivityName;
    }

    public void setCommerceActivityName(String commerceActivityName) {
        this.commerceActivityName = commerceActivityName;
    }

    public String getBeneficiaryProductType() {
        return beneficiaryProductType;
    }

    public void setBeneficiaryProductType(String beneficiaryProductType) {
        this.beneficiaryProductType = beneficiaryProductType;
    }

    public String getBeneficiaryProductId() {
        return beneficiaryProductId;
    }

    public void setBeneficiaryProductId(String beneficiaryProductId) {
        this.beneficiaryProductId = beneficiaryProductId;
    }

    @Override
    public String toString() {
        return "DestinationTrx{" +
                "commerceDocument=" + commerceDocument +
                ", commerceName='" + commerceName + '\'' +
                ", commerceActivityName='" + commerceActivityName + '\'' +
                ", beneficiaryProductType='" + beneficiaryProductType + '\'' +
                ", beneficiaryProductId='" + beneficiaryProductId + '\'' +
                '}';
    }
}
