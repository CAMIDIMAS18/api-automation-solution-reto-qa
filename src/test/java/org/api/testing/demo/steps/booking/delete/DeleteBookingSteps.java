package org.api.testing.demo.steps.booking.delete;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.api.testing.demo.tasks.common.ConsumeDeleteTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.assertions.EnsureResponseBodyStringTask.ensureThatResponseMessageMatchesWith;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersAuthentication;
import static org.api.testing.demo.questions.common.ResponseTimeQuestion.responseTimeIs;
import static org.api.testing.demo.questions.common.StatusCodeQuestion.httpResponseStatusCodeIs;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.BOOKING_ID;
import static org.api.testing.demo.utils.constants.Constants.CREATED_MESSAGE;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.CREATED;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class DeleteBookingSteps {
    @Cuando("ella/el solicite la eliminación con su código único de reserva")
    public void deleteBooking() {

        theActorInTheSpotlight().attemptsTo(
                ConsumeDeleteTask.with(CAMILA.recall(BOOKING_ID), headersAuthentication()));
    }

    @Entonces("ya no podrá visualizar su reserva en el sistema")
    public void checkBookingDeleted() {

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(5000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(CREATED.getHttpStatusCode()))
                .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().attemptsTo(ensureThatResponseMessageMatchesWith(CREATED_MESSAGE));

    }
}
