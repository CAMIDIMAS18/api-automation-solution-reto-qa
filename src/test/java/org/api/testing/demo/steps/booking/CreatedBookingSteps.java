package org.api.testing.demo.steps.booking;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.api.testing.demo.tasks.booking.created.CreateBooking;
import org.api.testing.demo.tasks.booking.created.ValidateInCreatedBookingResponse;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.*;
import static org.api.testing.demo.questions.common.GetExpectedJsonSchema.theJsonSchemaExpectIs;
import static org.api.testing.demo.questions.common.GetResponseTime.responseTimeIs;
import static org.api.testing.demo.questions.common.GetStatusCode.httpResponseStatusCodeIs;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.*;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class CreatedBookingSteps {

    @Dado("que la/el cliente desea crear/consultar/actualizar/eliminar una/la reservación de/para su próximo viaje")
    public void preparingAPI() {
        OnStage.theActorCalled(CAMILA.toString());
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at(CAMILA.recall(THE_REST_API_BASE_URL)));
    }

    @Cuando("el/ella ingresa la siguiente información solicitada para la creación de la reserva")
    public void sendRequestToApi(List<Map<String, String>> dataMapList) {

        CAMILA.remember(BOOKING_DATA, dataMapList);

        for (Map<String, String> data : dataMapList) {

            theActorInTheSpotlight().attemptsTo(CreateBooking.withInformationRequested(data));

            theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                    .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

            theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(15000L))
                    .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

            theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(NAME_SCHEMA_RESOURCE_CREATED))
                    .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
        }
    }

    @Entonces("su solicitud se creará en el sistema con su información y un número de registro único")
    public void validateServiceResponse() {
        List<Map<String, String>> dataMapList = CAMILA.recall(BOOKING_DATA);

        for (Map<String, String> userData : dataMapList) {

            ValidateInCreatedBookingResponse.thatBookingIdWasGenerated();
            ValidateInCreatedBookingResponse.thatFirstNameIs(userData.get("firstname"));
            ValidateInCreatedBookingResponse.thatLastNameIs(userData.get("lastname"));
            ValidateInCreatedBookingResponse.thatTotalPriceIs(userData.get("totalprice"));
            ValidateInCreatedBookingResponse.thatDepositPaidIs(userData.get("depositpaid"));
            ValidateInCreatedBookingResponse.thatCheckInDateIs(userData.get("checkinDate"));
            ValidateInCreatedBookingResponse.thatCheckOutDateIs(userData.get("checkoutDate"));
            ValidateInCreatedBookingResponse.thatAdditionalNeedsIs(userData.get("additionalneeds"));
        }
    }
}
