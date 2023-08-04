package co.com.bancolombia.form.qr.controller;

import co.com.bancolombia.bancolombia_home_rest.structure.RequestOld;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;
import co.com.bancolombia.form.qr.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Api(value = "Return QR - Generator")
public interface IGenerateController {

    @ApiOperation(value = "Generate qr")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation", response = Response.class),
            @ApiResponse(code = 400, message = "Invalid Status"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PostMapping(value = "/generate-qr", produces = {Constant.HEADER_PRODUCES_BANCOLOMBIA})
    ResponseEntity<Response> generateQr(@RequestBody RequestOld request);
}
