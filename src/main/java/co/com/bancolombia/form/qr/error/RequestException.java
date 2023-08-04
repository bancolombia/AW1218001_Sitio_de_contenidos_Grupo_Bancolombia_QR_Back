package co.com.bancolombia.form.qr.error;

import org.springframework.http.HttpStatus;

import co.com.bancolombia.bancolombia_home_rest.structure.Response;

public class RequestException extends Exception {
	
	private final Response response;
	private final HttpStatus status;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RequestException() {
		super();
		response = null;
		status = null;
	}
	
	public RequestException(final String message, final Throwable cause) {
        super(message, cause);
        response = null;
        status = null;
	}
	
	public RequestException(final String message) {
        super(message);
        response = null;
        status = null;
	}
	
	public RequestException(final Throwable cause) {
        super(cause);
        response = null;
        status = null;
	}
	
	public RequestException(Response response, HttpStatus status) {
		super();
		this.response = response;
		this.status = status;
	}

	public Response getResponse() {
		return response;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
