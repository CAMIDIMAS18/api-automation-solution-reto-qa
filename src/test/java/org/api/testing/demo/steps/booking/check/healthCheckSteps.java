package org.api.testing.demo.steps.booking.check;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.api.testing.demo.tasks.common.ConsumeGetTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.assertions.EnsureResponseBodyStringTask.ensureThatResponseMessageMatchesWith;
import static org.api.testing.demo.questions.common.ResponseTimeQuestion.responseTimeIs;
import static org.api.testing.demo.questions.common.StatusCodeQuestion.httpResponseStatusCodeIs;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.CREATED_MESSAGE;
import static org.api.testing.demo.utils.constants.Constants.THE_REST_API_BASE_URL;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.CREATED;
import static org.api.testing.demo.utils.environments.Endpoints.HEALTH_CHECK;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class healthCheckSteps {
    @Dado("que se requiere confirmar que la api funcione correctamente")
    public void userHasAccessToApi() {
        OnStage.theActorCalled(CAMILA.toString());
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at(CAMILA.recall(THE_REST_API_BASE_URL)));
    }

    @Cuando("realizo una solicitud para comprobar la salud del servicio de reservas")
    public void requestToCheckTheHealthOfBookingService() {
        theActorInTheSpotlight().attemptsTo(ConsumeGetTask.with(HEALTH_CHECK));
    }

    @Entonces("se deberá obtener una respuesta exitosa del sistema")
    public void getSuccessfulResponseCode() {

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(5000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(CREATED.getHttpStatusCode()))
                .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().attemptsTo(ensureThatResponseMessageMatchesWith(CREATED_MESSAGE));
    }
}