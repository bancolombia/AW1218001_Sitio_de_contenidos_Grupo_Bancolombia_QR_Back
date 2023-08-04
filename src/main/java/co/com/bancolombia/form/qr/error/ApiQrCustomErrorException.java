package co.com.bancolombia.form.qr.error;


public class ApiQrCustomErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApiQrCustomErrorException() {
		super();
	}
	
	public ApiQrCustomErrorException(final String message, final Throwable cause) {
        super(message, cause);
	}
	
	public ApiQrCustomErrorException(final String message) {
        super(message);
	}
	
	public ApiQrCustomErrorException(final Throwable cause) {
        super(cause);
	}

}
