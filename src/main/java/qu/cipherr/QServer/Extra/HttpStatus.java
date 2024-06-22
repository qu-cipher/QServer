package qu.cipherr.QServer.Extra;

public class HttpStatus {
    // Informational responses (100–199)
    public static final String CONTINUE = "HTTP/1.1 100 Continue";
    public static final String SWITCHING_PROTOCOLS = "HTTP/1.1 101 Switching Protocols";
    public static final String PROCESSING = "HTTP/1.1 102 Processing";
    public static final String EARLY_HINTS = "HTTP/1.1 103 Early Hints";

    // Successful responses (200–299)
    public static final String OK = "HTTP/1.1 200 OK";
    public static final String CREATED = "HTTP/1.1 201 Created";
    public static final String ACCEPTED = "HTTP/1.1 202 Accepted";
    public static final String NON_AUTHORITATIVE_INFORMATION = "HTTP/1.1 203 Non-Authoritative Information";
    public static final String NO_CONTENT = "HTTP/1.1 204 No Content";
    public static final String RESET_CONTENT = "HTTP/1.1 205 Reset Content";
    public static final String PARTIAL_CONTENT = "HTTP/1.1 206 Partial Content";
    public static final String MULTI_STATUS = "HTTP/1.1 207 Multi-Status";
    public static final String ALREADY_REPORTED = "HTTP/1.1 208 Already Reported";
    public static final String IM_USED = "HTTP/1.1 226 IM Used";

    // Redirection messages (300–399)
    public static final String MULTIPLE_CHOICES = "HTTP/1.1 300 Multiple Choices";
    public static final String MOVED_PERMANENTLY = "HTTP/1.1 301 Moved Permanently";
    public static final String FOUND = "HTTP/1.1 302 Found";
    public static final String SEE_OTHER = "HTTP/1.1 303 See Other";
    public static final String NOT_MODIFIED = "HTTP/1.1 304 Not Modified";
    public static final String USE_PROXY = "HTTP/1.1 305 Use Proxy";
    public static final String SWITCH_PROXY = "HTTP/1.1 306 Switch Proxy";
    public static final String TEMPORARY_REDIRECT = "HTTP/1.1 307 Temporary Redirect";
    public static final String PERMANENT_REDIRECT = "HTTP/1.1 308 Permanent Redirect";

    // Client error responses (400–499)
    public static final String BAD_REQUEST = "HTTP/1.1 400 Bad Request";
    public static final String UNAUTHORIZED = "HTTP/1.1 401 Unauthorized";
    public static final String PAYMENT_REQUIRED = "HTTP/1.1 402 Payment Required";
    public static final String FORBIDDEN = "HTTP/1.1 403 Forbidden";
    public static final String NOT_FOUND = "HTTP/1.1 404 Not Found";
    public static final String METHOD_NOT_ALLOWED = "HTTP/1.1 405 Method Not Allowed";
    public static final String NOT_ACCEPTABLE = "HTTP/1.1 406 Not Acceptable";
    public static final String PROXY_AUTHENTICATION_REQUIRED = "HTTP/1.1 407 Proxy Authentication Required";
    public static final String REQUEST_TIMEOUT = "HTTP/1.1 408 Request Timeout";
    public static final String CONFLICT = "HTTP/1.1 409 Conflict";
    public static final String GONE = "HTTP/1.1 410 Gone";
    public static final String LENGTH_REQUIRED = "HTTP/1.1 411 Length Required";
    public static final String PRECONDITION_FAILED = "HTTP/1.1 412 Precondition Failed";
    public static final String PAYLOAD_TOO_LARGE = "HTTP/1.1 413 Payload Too Large";
    public static final String URI_TOO_LONG = "HTTP/1.1 414 URI Too Long";
    public static final String UNSUPPORTED_MEDIA_TYPE = "HTTP/1.1 415 Unsupported Media Type";
    public static final String RANGE_NOT_SATISFIABLE = "HTTP/1.1 416 Range Not Satisfiable";
    public static final String EXPECTATION_FAILED = "HTTP/1.1 417 Expectation Failed";
    public static final String IM_A_TEAPOT = "HTTP/1.1 418 I'm a teapot";
    public static final String MISDIRECTED_REQUEST = "HTTP/1.1 421 Misdirected Request";
    public static final String UNPROCESSABLE_ENTITY = "HTTP/1.1 422 Unprocessable Entity";
    public static final String LOCKED = "HTTP/1.1 423 Locked";
    public static final String FAILED_DEPENDENCY = "HTTP/1.1 424 Failed Dependency";
    public static final String TOO_EARLY = "HTTP/1.1 425 Too Early";
    public static final String UPGRADE_REQUIRED = "HTTP/1.1 426 Upgrade Required";
    public static final String PRECONDITION_REQUIRED = "HTTP/1.1 428 Precondition Required";
    public static final String TOO_MANY_REQUESTS = "HTTP/1.1 429 Too Many Requests";
    public static final String REQUEST_HEADER_FIELDS_TOO_LARGE = "HTTP/1.1 431 Request Header Fields Too Large";
    public static final String UNAVAILABLE_FOR_LEGAL_REASONS = "HTTP/1.1 451 Unavailable For Legal Reasons";

    // Server error responses (500–599)
    public static final String INTERNAL_SERVER_ERROR = "HTTP/1.1 500 Internal Server Error";
    public static final String NOT_IMPLEMENTED = "HTTP/1.1 501 Not Implemented";
    public static final String BAD_GATEWAY = "HTTP/1.1 502 Bad Gateway";
    public static final String SERVICE_UNAVAILABLE = "HTTP/1.1 503 Service Unavailable";
    public static final String GATEWAY_TIMEOUT = "HTTP/1.1 504 Gateway Timeout";
    public static final String HTTP_VERSION_NOT_SUPPORTED = "HTTP/1.1 505 HTTP Version Not Supported";
    public static final String VARIANT_ALSO_NEGOTIATES = "HTTP/1.1 506 Variant Also Negotiates";
    public static final String INSUFFICIENT_STORAGE = "HTTP/1.1 507 Insufficient Storage";
    public static final String LOOP_DETECTED = "HTTP/1.1 508 Loop Detected";
    public static final String NOT_EXTENDED = "HTTP/1.1 510 Not Extended";
    public static final String NETWORK_AUTHENTICATION_REQUIRED = "HTTP/1.1 511 Network Authentication Required";
}
