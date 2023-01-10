package org.api.testing.demo.environments;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import org.api.testing.demo.steps.conf.CommonHooks;

public class Endpoints {
    public static final String BASE_URL = setEndpoint("baseurl");
    public static final String AUTH = setEndpoint("auth");
    public static final String GET_BOOKING_BY_IDS = setEndpoint("getBookingIds");
    public static final String GET_BOOKING_BY_ID = setEndpoint("getBookingId");
    public static final String GET_BOOKING_BY_NAMES = setEndpoint("getBookingByNames");
    public static final String CREATE_BOOKING = setEndpoint("createBooking");
    public static final String UPDATE_BOOKING = setEndpoint("updateBookingId");
    public static final String PARTIAL_UPDATE_BOOKING = setEndpoint("partialUpdateBooking");
    public static final String DELETE_BOOKING = setEndpoint("deleteBooking");
    public static final String HEALTH_CHECK = setEndpoint("healthCheckApi");

    private Endpoints() {
        //Nothing
    }

    public static String setEndpoint(String path) {
        return EnvironmentSpecificConfiguration.from(CommonHooks.environmentVariables).getProperty(path);
    }
}
