/**
 * 
 */
package co.com.bancolombia.form.qr;

public class Constant {

	/**** Data Project ****/
	public static final String PROJECT_NAME = "Form - QR";
	public static final String PROJECT_VERSION = "1.00.000";
	public static final String PROJECT_DESCRIPTION = "Form QR Middleware";
	public static final String PROJECT_LICENSE = "Apache 2.0";
	public static final String SWAGGER_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.html";
	public static final String SWAGGER_CONCTACT_NAME = "Portal de contenidos Grupo Bancolombia";

	public static final String LOCAL_ENV = "local";
	
	public static final String CREATING_ASYNC_TASK_EXECUTOR_TEXT = "Creating async task executor...";
	public static final int CREATING_ASYNC_CORE_POOL_SIZE = 2;
	public static final int CREATING_ASYNC_MAX_POOL_SIZE = 2;
	public static final int CREATING_ASYNC_QUEUE_CAPACITY = 25;
	public static final String CREATING_ASYNC_THREAD_NAME_PREFIX_TEXT = "QrThread-";
	
	public static final String LOG_KEY_VALUE = "\"{}\": \"{}\"";

	// Messages
	public static final String MESSAGE_SQS_LOCAL_CONNECTION = "Obtaining a default client by profile: {}";
	public static final String MESSAGE_SQS_AWS_CONNECTION = "Obtaining a credentials by Instance";

	// Produces data
	public static final String HEADER_PRODUCES_BANCOLOMBIA = "application/vnd.bancolombia.v3+json";
	public static final String APIQR_CLIENT_ID = "client-id";
	public static final String APIQR_CLIENT_SECRET = "client-secret";
	public static final String APIQR_CONCAT_FULL_PATH = "%s/%s/%s"; 
	public static final String APIQR_TLS_PROTOCOL = "TLSv1.3";
	public static final String APIQR_UNEXPECTED_DEFAULT_TRUST_MANAGER = "Unexpected default trust managers: ";
	public static final String APIQR_CHANNEL = "Portal de Contenidos";
	public static final String APIQR_TYPE = "Nuevo";	
	public static final String APIQR_HABEAS_DATA_VERSION = "V2.6";	
	public static final int APIQR_CONNECTION_TIMEOUT_SECONDS = 60;
	public static final int APIQR_READ_TIMEOUT_SECONDS = 60;
	public static final String APIQR_LOG_REQUEST_API = "Request API-QR: {}";
	public static final String APIQR_LOG_RESPONSE_API = "Response API-QR: {}";
	public static final String APIQR_LOG_CONSUME_API = "Consume API-QR: {}";
	public static final String APIQR_LOG_KEY_VALUE = "{}: {}";
	public static final String APIQR_S3_LOG_KEY_VALUE = "Retrieving key from S3 bucket -  \"{}\": \"{}\"";
	public static final String APIQR_S3_RETRIEVING_TEMPLATES_FROM_S3 = "Retrieving templates from bucket S3";
	public static final String APIQR_UNEXPECTED_RESPONSE_FROM_REMOTE_SERVER = "Lo sentimos algo salió mal, intentalo mal tarde.";

	/**** Validation ****/ 
	public static final String REGEX_BLACK_LIST = "^[^~&\\\"'_?<>{}\\[\\]%$\\/\\\\]*$";
	
	public static final long DELIVERY_INF_MIN_QUANTITY_VALUE = 1;
	public static final String DELIVERY_INF_MIN_QUANTITY_VALUE_MESSAGE = "The minimum quantity is 1";
	public static final long DELIVERY_INF_MAX_QUANTITY_VALUE = 50;
	public static final String DELIVERY_INF_MAX_QUANTITY_VALUE_MESSAGE = "The maximum quantity is 50";
	
	public static final String DELIVERY_INF_DEPARTMENT_REQUIRED = "The Department is required";
	public static final String DELIVERY_INF_DEPARTMENT_REGEX = "^[A-Za-z\\-\\.\\,\\;\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*$";
	public static final int DELIVERY_INF_MIN_SIZE_DEPARTMENT_VALUE = 3;
	public static final int DELIVERY_INF_MAX_SIZE_DEPARTMENT_VALUE = 50;
	public static final String DELIVERY_INF_DEPARTMENT_VALUE_MESSAGE = "The Department length is out of range";
	
	public static final String DELIVERY_INF_CITY_REQUIRED = "The City is required";
	public static final String DELIVERY_INF_CITY_REGEX = "^[A-Za-z\\-\\.\\,\\;\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*$";
	public static final int DELIVERY_INF_MIN_SIZE_CITY_VALUE = 3;
	public static final int DELIVERY_INF_MAX_SIZE_CITY_VALUE = 50;
	public static final String DELIVERY_INF_CITY_VALUE_MESSAGE = "The City length is out of range";

	public static final String DELIVERY_INF_ADDRESS_REQUIRED = "The Address is required";
	public static final String DELIVERY_INF_ADDRESS_REGEX = "^[A-Za-z\\-\\.\\,\\;\\#\\d\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*$";
	public static final int DELIVERY_INF_MIN_SIZE_ADDRESS_VALUE = 3;
	public static final int DELIVERY_INF_MAX_SIZE_ADDRESS_VALUE = 1000;
	public static final String DELIVERY_INF_ADDRESS_VALUE_MESSAGE = "The Address length is out of range";
	
	public static final String DELIVERY_INF_CONTACT_PHONE_REQUIRED = "The Contact phone is required";
	public static final String DELIVERY_INF_CONTACT_PHONE_REGEX = "^[\\d]{7,10}$";
	public static final int DELIVERY_INF_MIN_SIZE_CONTACT_PHONE_VALUE = 7;
	public static final int DELIVERY_INF_MAX_SIZE_CONTACT_PHONE_VALUE = 10;
	public static final String DELIVERY_INF_CONTACT_PHONE_VALUE_MESSAGE = "The Contact phone length is out of range";
	
	public static final String DELIVERY_INF_DELIVERY_TIME_REQUIRED = "The Delivery time";
	public static final String DELIVERY_INF_DELIVERY_TIME_REGEX = "^[A-Za-z\\-\\.\\,\\;\\#\\d\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*$";
	public static final int DELIVERY_INF_MIN_SIZE_DELIVERY_TIME_VALUE = 3;
	public static final int DELIVERY_INF_MAX_SIZE_DELIVERY_TIME_VALUE = 50;
	public static final String DELIVERY_INF_DELIVERY_TIME_VALUE_MESSAGE = "The Delivery time length is out of range";
	
	// Patter Time
	public static final String DATE_TIME_FORMATTER = "yyyy-M-d";

	// Message response
	public static final String MESSAGE_ERROR_IN_PROCESS_DATA = "Error in process data";
	public static final String MESSAGE_ERROR_IN_PROCESS_DATA_DETAIL = "Error in process data: {}";
	public static final String MESSAGE_NO_ACCEPT_REQUEST = "No accept request json value - Headers or Body";
	public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal server error";
	public static final String MESSAGE_INTERNAL_SERVER_ERROR_DETAIL = "Internal server error: {}";
	public static final String MESSAGE_INTERRUPTEDEXCEPTION_SERVER_ERROR_DETAIL = "Interrupted exception server error: {}";
	public static final String MESSAGE_INCOMPLETE_REQUEST_BODY_DATA = "Incomplete request body data";
	public static final String MESSAGE_FILE_GENERATED_SUCCESFULLY = "File successfully generated";
	public static final String MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR = "File not generated or error";

	// Report configuration
	public static final float QR_WIDTH = 1308.875f;
	public static final float QR_HEIGHT = 1308.875f;
	public static final String QR_LANGUAGE_ES_CO = "es-CO";
	
	public static final String QR_REPORT_COCKADE_TITLE = "Bancolombia - Escarapela QR - V 2.2";
	public static final float QR_REPORT_COCKADE_FIXED_POSITION_LEFT = 147;
	public static final float QR_REPORT_COCKADE_FIXED_POSITION_BOTTOM = 385;
	public static final float QR_REPORT_COCKADE_SCALE_TO_FIT_WIDTH = 288;
	public static final float QR_REPORT_COCKADE_SCALE_TO_FIT_HEIGHT = 340;
	public static final int QR_REPORT_COCKADE_PAGE = 1;
	
	public static final String QR_REPORT_TALKATIVE_TITLE = "Bancolombia - Hablador QR - V 2.2";
	public static final float QR_REPORT_TALKATIVE_FIXED_POSITION_LEFT = 207;
	public static final float QR_REPORT_TALKATIVE_FIXED_POSITION_BOTTOM = 493;
	public static final float QR_REPORT_TALKATIVE_SCALE_TO_FIT_WIDTH = 194;
	public static final float QR_REPORT_TALKATIVE_SCALE_TO_FIT_HEIGHT = 216;
	public static final int QR_REPORT_TALKATIVE_PAGE = 1;

	// API Configuration
	public static final String AWS_SECRETSM_AWSCURRENT = "AWSCURRENT";
	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

	public static final String CODE = "BPQR-B0021";

	private Constant() {
	}
}
