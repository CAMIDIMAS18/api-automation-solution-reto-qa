package org.api.testing.demo.steps.booking.check;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.api.testing.demo.questions.booking.check.TheQueryFieldsAndValuesAre;
import org.api.testing.demo.tasks.booking.check.ConsultTheBookingsTask;
import org.api.testing.demo.tasks.booking.check.FilterBookingSearchTask;
import org.api.testing.demo.utils.exceptions.AssertionsServices;
import org.api.testing.demo.utils.exceptions.NotQueryParameterFoundException;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.questions.booking.check.TheBookingIdIs.theBookingId;
import static org.api.testing.demo.questions.common.GetExpectedJsonSchema.theJsonSchemaExpectIs;
import static org.api.testing.demo.questions.common.GetResponseTime.responseTimeIs;
import static org.api.testing.demo.questions.common.GetStatusCode.httpResponseStatusCodeIs;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.*;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.demo.utils.environments.Endpoints.*;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.*;
import static org.hamcrest.Matchers.*;

public class GetBookingSteps {
    @Cuando("ella/el filtre la consulta por el parámetro {string}")
    public void getScheduledReservations(String queryParametersOption) {

        switch (queryParametersOption) {
            case "All Bookings":
                theActorInTheSpotlight().attemptsTo(ConsultTheBookingsTask.with(GET_ALL_BOOKINGS));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_ALL_BOOKINGS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            case "ID Booking":
                theActorInTheSpotlight().attemptsTo(ConsultTheBookingsTask.with(GET_BOOKING_BY_ID + CAMILA.recall(BOOKING_ID)));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_BOOKING_DETAILS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            case "Customer Names":
                theActorInTheSpotlight().attemptsTo(FilterBookingSearchTask.withParams(GET_BOOKING_BY_CUSTOM_NAMES, CAMILA.recall(PATH_PARAMS_WITH_NAMES)));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_ALL_BOOKINGS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            case "Dates":
                theActorInTheSpotlight().attemptsTo(FilterBookingSearchTask.withParams(GET_BOOKING_BY_DATES, CAMILA.recall(PATH_PARAMS_WITH_DATES)));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_ALL_BOOKINGS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            default:
                throw new NotQueryParameterFoundException(queryParametersOption);
        }

        theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(15000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));
    }

    @Entonces("visualizará los detalles de la reserva")
    public void validateBookingDetails() {
        theActorInTheSpotlight()
                .should(seeThat(TheQueryFieldsAndValuesAre.expected())
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED)
                );
    }

    @Entonces("visualizará el Id de su reserva")
    public void validateScheduledBookingsById() {
        int bookingId = CAMILA.recall(BOOKING_ID);

        theActorInTheSpotlight().should(
                seeThat("the value of booking Id", theBookingId(bookingId), is(equalTo(bookingId)))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    @Cuando("ella filtre la consulta por las fechas entre {string} a {string}")
    public void getScheduledReservationsByDates(String checkin, String checkout) {
        Map<String, Object> hashMapParams = new HashMap<>();
        hashMapParams.put("checkin", checkin);
        hashMapParams.put("checkout", checkout);

        theActorInTheSpotlight().attemptsTo(FilterBookingSearchTask.withParams(GET_BOOKING_BY_DATES, hashMapParams));
    }

    @Entonces("visualizará todos los bookingIDs encontrados para el rango de fechas")
    public void validateAllBookingIds() {
        theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(15000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_ALL_BOOKINGS_SCHEMA))
                .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
    }
}
