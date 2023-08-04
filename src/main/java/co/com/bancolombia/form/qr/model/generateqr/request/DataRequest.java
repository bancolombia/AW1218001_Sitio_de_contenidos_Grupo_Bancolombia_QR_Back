package co.com.bancolombia.form.qr.model.generateqr.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRequest {

    @Valid
    private RenewRequest data;

}
