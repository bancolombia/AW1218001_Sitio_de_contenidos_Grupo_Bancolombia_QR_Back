package co.com.bancolombia.form.qr.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import co.com.bancolombia.bancolombia_home_rest.HomeRest;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;

@Component
public class Utilities {
	
	public Response getEmptyResponse(HomeRest home, HttpStatus status) {

		String code = null;
		String description = null;
		if (status == HttpStatus.BAD_REQUEST) {
			code = co.com.bancolombia.bancolombia_home_rest.Constant.BAD_REQUEST_ERROR_CODE;
			description = co.com.bancolombia.bancolombia_home_rest.Constant.BAD_REQUEST_ERROR_DESCRIPTION;
		} else if (status == HttpStatus.UNAUTHORIZED) {
			code = co.com.bancolombia.bancolombia_home_rest.Constant.UNAUTHORIZED_ERROR_CODE;
			description = co.com.bancolombia.bancolombia_home_rest.Constant.UNAUTHORIZED_ERROR_DESCRIPTION;
		} else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
			code = co.com.bancolombia.bancolombia_home_rest.Constant.INTERNAL_SERVER_ERROR_CODE;
			description = co.com.bancolombia.bancolombia_home_rest.Constant.INTERNAL_SERVER_ERROR_DESCRIPTION;
		}

		return home.generateEmptyReponse(null, code, description);

	}
	
	public Response generateEmptyReponse(String messageId, String code, String description) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(co.com.bancolombia.bancolombia_home_rest.Constant.CODE, code);
		data.put(co.com.bancolombia.bancolombia_home_rest.Constant.DESCRIPTION, description);
		Response response = new Response();
		response.getMeta().setMessageId(messageId);		
		response.getMeta().setRequestTimeStamp((new co.com.bancolombia.bancolombia_home_rest.util.Utilities()).getNowDateTime());
		response.setData(data);
		response.getMeta().setResponseSize(data.size());
		response.getMeta().setVersion(co.com.bancolombia.bancolombia_home_rest.Constant.VERSION_JSON_API);
		return response;
	}

}
