/**
 * HTTPStatus is an enumeration of the different types of HTTP response status.
 * 
 * @author Stephan Jamieson
 * @version 16/2/2016
 */
public enum HTTPStatus {

	CONTINUE("100", "Continue"),
	SWITCHING_PROTOCOLS("101", "Switching Protocols"),
	OK("200", "OK"),
	CREATED("201", "Created"),
	ACCEPTED("202", "Accepted"), 
	NON_AUTHORITATIVE_INFORMATION("203", "Non-Authoratitive Information"),
	NO_CONTENT("204", "No Content, please set a file that the server must get for you."),
	RESET_CONTENT("205", "Reset Content"),
	PARTIAL_CONTENT("206", "Partial Content"),
	MULTIPLE_CHOICES("300", "Multiple Choices"),
	MOVED_PERMANENTLY("301", "Moved Permanently"),
	FOUND("302", "Found"),
	SEE_OTHER("303", "See Other"),
	NOT_MODIFIED("304", "Not Modified"),
	USE_PROXY("305", "Use Proxy"),
	TEMPORARY_REDIRECT("307", "Temporary Redirect"),
	BAD_REQUEST("400", "Bad Request"),
	UNAUTHORIZED("401", "Unauthorized"),
	PAYMENT_REQUIRED("402", "Payment Required"),
	FORBIDDEN("403", "Forbidden"),
	NOT_FOUND("404", "Not Found, Jason's server is awsesome, but it cannot create something out of nothing..."),
	METHOD_NOT_ALLOWED("405", "Method Not Allowed, this server only does GET"),
	NOT_ACCEPTABLE("406", "Not Acceptable"),
	PROXY_AUTHENTICATION_REQUIRED("407", "Proxy Authentication Required"),
	REQUEST_TIMEOUT("408", "Request Timeout"),
	CONFLICT("409", "Conflict"),
	GONE("410", "Gone"),
	LENGTH_REQUIRED("411", "Length Required"),
	PRECONDITION_FAILED("412", "Precondition Failed"),
	REQUEST_ENTITY_TOO_LARGE("413","Request Entity Too Large"),
	REQUEST_URI_TOO_LARGE("414", "Request-URI Too Large"),
	UNSUPPORTED_MEDIA_TYPE("415", "Unsupported Media Type"),
	REQUESTED_RANGE_NOT_SATISFIABLE("416", "Requested range not satisfiable"),
	EXPECTATION_FAILED("417", "Expectation Failed"),
	INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
	NOT_IMPLEMENTED("501", "Not Implemented"),
	BAD_GATEWAY("502", "Bad Gateway"),
	SERVICE_UNAVAILABLE("503", "Service Unavailable"),
	GATEWAY_TIMEOUT("504", "Gateway Time-out"),
	HTTP_VERSION_NOT_SUPPORTED("505", "HTTP Version not supported");
	

	private final String code;
	private final String reason;

	private HTTPStatus(final String code, final String reason) { 
		this.code = code; 
		this.reason = reason;
	}
	
	/**
	 * Obtain the status code number.
	 */
	public String getCode() { return code; }
	
	/**
	 * Obtain the status reason.
	 */
	public String getReason() { return reason; }
	
	/**
	 * Obtain a String of the form <code>this.getCode()+" "+this.getReason()</code>.
	 */
	public String toString() { return this.code+" "+this.reason; }
	
}