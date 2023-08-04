package co.com.bancolombia.form.qr.service;

import co.com.bancolombia.bancolombia_home_rest.error.JsonMapperException;
import co.com.bancolombia.bancolombia_home_rest.error.SqsMessageException;
import co.com.bancolombia.bancolombia_home_rest.structure.RequestOld;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;
import co.com.bancolombia.form.qr.error.RequestException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface IMessageService<T> {
	
	T getData(HttpServletRequest headers, RequestOld request, Class<?> dataClass) throws RequestException, SqsMessageException, JsonMapperException;
	
	Response generateResponse(HttpServletRequest headers, RequestOld request, Map<String, Object> data, String message);

}
