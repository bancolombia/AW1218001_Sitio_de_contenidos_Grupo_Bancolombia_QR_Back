package co.com.bancolombia.form.qr.error;

public class TrustStoreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TrustStoreException() {
		super();
	}
	
	public TrustStoreException(final String message, final Throwable cause) {
        super(message, cause);
	}
	
	public TrustStoreException(final String message) {
        super(message);
	}
	
	public TrustStoreException(final Throwable cause) {
        super(cause);
	}

}
