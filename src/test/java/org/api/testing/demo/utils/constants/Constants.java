package org.api.testing.demo.utils.constants;

public class Constants {

    /**
     * General constants
     */
    public static final String BOOKING_ID = "Id booking";
    public static final String BOOKING_DATA = "Booking data";
    public static final String CHECKIN_DATE = "checkIn";
    public static final String CHECKOUT_DATE = "checkOut";
    public static final String CREATED_MESSAGE = "Created";
    public static final String SERVER_ERROR = "Internal Server Error";
    public static final String NOT_FOUND_BOOKING = "Not Found";
    public static final String NOT_METHOD_ALLOWED = "Method Not Allowed";
    public static final String FORBIDDEN_REQUEST = "Forbidden";
    public static final String PATH_PARAMS_WITH_NAMES = "path params with custom names";
    public static final String PATH_PARAMS_WITH_DATES = "path params with dates";
    public static final String THE_REST_API_BASE_URL = "The base url";
    public static final String TOKEN = "token";
    public static final String RESPONSE_BODY = "Response body";
    public static final String SCENARIO = "scenario";
    public static final String SUCCESSFUL_VALIDATION = "Valid response is returned!";


    /**
     * Json schema paths
     */
    public static final String CREATE_BOOKING_SHEMA = "PostJsonSchema";
    public static final String GET_ALL_BOOKINGS_SCHEMA = "GetAllBookingsSchema";
    public static final String GET_BOOKING_DETAILS_SCHEMA = "GetBookingDetailsSchema";
    public static final String AUTH_SHEMA = "AuthSchema";

    private Constants() {
        //Nothing
    }
}
