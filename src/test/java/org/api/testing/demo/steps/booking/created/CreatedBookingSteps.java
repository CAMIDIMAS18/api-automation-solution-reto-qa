package org.api.testing.demo.steps.booking.created;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.api.testing.demo.models.common.BookingData;
import org.api.testing.demo.tasks.booking.created.CreateBookingTask;
import org.api.testing.demo.tasks.booking.created.ValidateInCreatedBooking;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.questions.common.GetExpectedJsonSchema.theJsonSchemaExpectIs;
import static org.api.testing.demo.questions.common.GetResponseTime.responseTimeIs;
import static org.api.testing.demo.questions.common.GetStatusCode.httpResponseStatusCodeIs;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.BOOKING_DATA;
import static org.api.testing.demo.utils.constants.Constants.CREATE_BOOKING_SHEMA;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class CreatedBookingSteps {
    @Cuando("el/ella solicita la creación de una reserva")
    public void sendRequestToApi() {
        List<Map<String, String>> datos = CAMILA.recall(BOOKING_DATA);

        for (Map<String, String> data : datos) {

            theActorInTheSpotlight().attemptsTo(CreateBookingTask.withInformationRequested(data));

            theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                    .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

            theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(10000L))
                    .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

            theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(CREATE_BOOKING_SHEMA))
                    .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
        }
    }

    @Entonces("su solicitud se creará en el sistema con su información y un número de registro único")
    public void validateServiceResponse() {
        ValidateInCreatedBooking.thatBookingIdWasGenerated();
        ValidateInCreatedBooking.thatFirstNameIs((String) BookingData.getData().get("firstname"));
        ValidateInCreatedBooking.thatLastNameIs((String) BookingData.getData().get("lastname"));
        ValidateInCreatedBooking.thatTotalPriceIs((String) BookingData.getData().get("totalprice"));
        ValidateInCreatedBooking.thatDepositPaidIs((String) BookingData.getData().get("depositpaid"));
        ValidateInCreatedBooking.thatCheckInDateIs((String) BookingData.getData().get("checkinDate"));
        ValidateInCreatedBooking.thatCheckOutDateIs((String) BookingData.getData().get("checkoutDate"));
        ValidateInCreatedBooking.thatAdditionalNeedsIs((String) BookingData.getData().get("additionalneeds"));
    }
}
