package org.api.testing.demo.steps.booking.common;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Pero;
import org.api.testing.demo.tasks.booking.created.NotCreateBookingTask;
import org.api.testing.demo.tasks.common.ConsumeDeleteTask;
import org.api.testing.demo.tasks.common.ConsumeGetTask;
import org.api.testing.demo.utils.exceptions.NotQueryParameterFoundException;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.assertions.EnsureResponseBodyStringTask.ensureThatResponseMessageMatchesWith;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;
import static org.api.testing.demo.questions.common.ResponseTimeQuestion.responseTimeIs;
import static org.api.testing.demo.questions.common.StatusCodeQuestion.httpResponseStatusCodeIs;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.*;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.*;
import static org.api.testing.demo.utils.environments.Endpoints.GET_BOOKING_BY_ID;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class UnHappyPathsSteps {

    @Pero("ella no envió un/el campo obligatorio lastname en la información solicitada")
    public void userDoesNotSendData(List<Map<String, String>> datos) {
        theActorInTheSpotlight().attemptsTo(NotCreateBookingTask.withInformationIncomplete(datos.get(0)));
    }

    @Pero("la reservación fue eliminada previamente")
    public void bookingPreviouslyDeleted() {

        theActorInTheSpotlight().attemptsTo(ConsumeGetTask.with(GET_BOOKING_BY_ID + CAMILA.recall(BOOKING_ID)));
    }

    @Cuando("ella consulte el ID de su reserva")
    public void ellaConsulteElDeSuReserva() {
        theActorInTheSpotlight().attemptsTo(ConsumeGetTask.with(GET_BOOKING_BY_ID + CAMILA.recall(BOOKING_ID)));
    }

    @Pero("no se autenticó correctamente en el sistema")
    public void improperAuthentication() {
        CAMILA.forget(TOKEN);
        theActorInTheSpotlight().attemptsTo(
                ConsumeDeleteTask.with(CAMILA.recall(BOOKING_ID), headersDefault()));
    }

    @Entonces("no se deberá {} la reserva en el sistema")
    public void bookingProcessNotAllowed(String option) {

        switch (option) {
            case "crear":
                theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(INTERNAL_SERVER_ERROR.getHttpStatusCode()))
                        .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

                theActorInTheSpotlight().attemptsTo(ensureThatResponseMessageMatchesWith(SERVER_ERROR));
                break;

            case "visualizar":
                theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(NOT_FOUND.getHttpStatusCode()))
                        .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

                theActorInTheSpotlight().attemptsTo(ensureThatResponseMessageMatchesWith(NOT_FOUND_BOOKING));
                break;

            case "actualizar":
                theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(METHOD_NOT_ALLOWED.getHttpStatusCode()))
                        .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

                theActorInTheSpotlight().attemptsTo(ensureThatResponseMessageMatchesWith(NOT_METHOD_ALLOWED));
                break;

            case "eliminar":
                theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(FORBIDDEN.getHttpStatusCode()))
                        .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

                theActorInTheSpotlight().attemptsTo(ensureThatResponseMessageMatchesWith(FORBIDDEN_REQUEST));
                break;

            default:
                throw new NotQueryParameterFoundException(option);
        }

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(5000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));
    }
}

