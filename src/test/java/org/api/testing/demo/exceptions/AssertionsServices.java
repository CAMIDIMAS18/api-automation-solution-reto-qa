package org.api.testing.demo.exceptions;

public class AssertionsServices extends AssertionError {

    public static final String THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED = "The status code services response is not expected";
    public static final String THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED = "The response time is not as expected";
    public static final String THE_SCHEMA_SERVICE_IS_NOT_EXPECTED = "The schema service response is not expected";
    public static final String EXCEPTION_ERROR_CONSUMPTION_SERVICE = "An error occurred in the consumption of the service";

    public AssertionsServices(String message, Throwable cause) {
        super(message, cause);
    }

    public AssertionsServices(String message) {
        super(message);
    }

}
