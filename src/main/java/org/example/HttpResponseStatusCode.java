package org.example;

/** This enum contains a list of all possible HTTP response codes according to its RFC definition.
 * This class is immutable and thread-safe, as all instances are created statically and their
 * properties cannot be modified. This prevents the creation of invalid HTTP request response codes
 * {@code @example}
 * <pre>{@code
 *            HttpResponseStatusCode okResponse = HttpResponseStatusCode.CreateFromCode(200);
 *            bool isOkayResponse = okResponse == HttpResponseStatusCode.OK ? true : false;
 *             }</pre>
 *
 * @see  <a href="https://www.rfc-editor.org/rfc/rfc9110.html#name-status-codes">Html RFC - Status Code definition</a>
 */
public enum HttpResponseStatusCode {

        // 1xx Informational
        CONTINUE(100, "Continue"),
        SWITCHING_PROTOCOLS(101, "Switching Protocols"),
        PROCESSING(102, "Processing"),
        EARLY_HINTS(103, "Early Hints"),

        // 2xx Success
        OK(200, "OK"),
        CREATED(201, "Created"),
        ACCEPTED(202, "Accepted"),
        NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
        NO_CONTENT(204, "No Content"),
        RESET_CONTENT(205, "Reset Content"),
        PARTIAL_CONTENT(206, "Partial Content"),
        MULTI_STATUS(207, "Multi-Status"),
        ALREADY_REPORTED(208, "Already Reported"),
        IM_USED(226, "IM Used"),

        // 3xx Redirection
        MULTIPLE_CHOICES(300, "Multiple Choices"),
        MOVED_PERMANENTLY(301, "Moved Permanently"),
        FOUND(302, "Found"),
        SEE_OTHER(303, "See Other"),
        NOT_MODIFIED(304, "Not Modified"),
        USE_PROXY(305, "Use Proxy"),
        SWITCH_PROXY(306, "Switch Proxy"),
        TEMPORARY_REDIRECT(307, "Temporary Redirect"),
        PERMANENT_REDIRECT(308, "Permanent Redirect"),

        // 4xx Client Error
        BAD_REQUEST(400, "Bad Request"),
        UNAUTHORIZED(401, "Unauthorized"),
        PAYMENT_REQUIRED(402, "Payment Required"),
        FORBIDDEN(403, "Forbidden"),
        NOT_FOUND(404, "Not Found"),
        METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
        NOT_ACCEPTABLE(406, "Not Acceptable"),
        PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
        REQUEST_TIMEOUT(408, "Request Timeout"),
        CONFLICT(409, "Conflict"),
        GONE(410, "Gone"),
        LENGTH_REQUIRED(411, "Length Required"),
        PRECONDITION_FAILED(412, "Precondition Failed"),
        PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
        URI_TOO_LONG(414, "URI Too Long"),
        UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
        RANGE_NOT_SATISFIABLE(416, "Range Not Satisfiable"),
        EXPECTATION_FAILED(417, "Expectation Failed"),
        IM_A_TEAPOT(418, "I'm a teapot"),
        MISDIRECTED_REQUEST(421, "Misdirected Request"),
        UNPROCESSABLE_CONTENT(422, "Unprocessable Content"),
        LOCKED(423, "Locked"),
        FAILED_DEPENDENCY(424, "Failed Dependency"),
        TOO_EARLY(425, "Too Early"),
        UPGRADE_REQUIRED(426, "Upgrade Required"),
        PRECONDITION_REQUIRED(428, "Precondition Required"),
        TOO_MANY_REQUESTS(429, "Too Many Requests"),
        REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
        UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

        // 5xx Server Error
        INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
        NOT_IMPLEMENTED(501, "Not Implemented"),
        BAD_GATEWAY(502, "Bad Gateway"),
        SERVICE_UNAVAILABLE(503, "Service Unavailable"),
        GATEWAY_TIMEOUT(504, "Gateway Timeout"),
        HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),
        VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
        INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
        LOOP_DETECTED(508, "Loop Detected"),
        NOT_EXTENDED(510, "Not Extended"),
        NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

        private final int responseCode;

        private final String description;


        /**
         * This constructor enables the creation of the predefined static instances
         * This method shall remain private, so that the creation of invalid responses (instances of this class)
         * is prevented
         *
         * @param responseCode: The HTTP status code according to the HTTP standard
         * @param description: The HTTP status code description according to the HTTP standard
         */
         HttpResponseStatusCode(int responseCode, String description) {
                this.responseCode= responseCode;
                this.description = description;
        }


        /** Getter method used to fetch the responseCode property of a HttpResponseCode object
         *
         * @return the code number of a given (predefined) HttpResponseStatusCode object
         */
        public int getCode(){
                return responseCode;
        }

        /** Getter method used to fetch the description property of a HttpResponseCode object
         *
         * @return the description of a given (predefined) HttpResponseStatusCode object
         */
        public String getDescription(){
                return description;
        }

        /** Creates a HttpResponseStatusCode object from a given response code (number)
         * Furthermore it checks the provided number (parameter) against the set of predefined HttpResponseStatusCode objects
         * If the provided number matches the number of a predefined HttpResponseStatusCode, the corresponding object is returned.
         *
         * @throws IllegalArgumentException  If no match is found, an IllegalArgumentException is thrown as the code was invalid as a consequence.
         * @param code a number that corresponds to a response code according to the HTTP standard
         * @return a valid HttpResponseStatusCode object providing the number of the code and the according description
         */
        public static HttpResponseStatusCode fromCode(int code) {
                for (HttpResponseStatusCode value : HttpResponseStatusCode.values()) {
                        if (value.getCode() == code) {
                                return value;
                        }
                }
                throw new IllegalArgumentException("Invalid HTTP response code: " + code);
        }


        /** This method is used to determine the HttpResponseCategory to a given HttpResponseStatusCode object
         *
         * @param statusCode the response code object containing the code number and a description
         * @return an instance of the HttpResponseCategory class that is derived from the number of the given HttpResponseStatusCode object
         */
        public static HttpResponseCategory getResponseCategory(HttpResponseStatusCode statusCode){
                return  HttpResponseCategory.getHttpResponseCategory(statusCode);
        }

}
