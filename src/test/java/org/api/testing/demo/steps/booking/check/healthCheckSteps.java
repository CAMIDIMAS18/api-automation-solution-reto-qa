package org.api.testing.demo.steps.booking.check;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.THE_REST_API_BASE_URL;

public class healthCheckSteps {
    @Dado("que se requiere confirmar que la api funcione correctamente")
    public void userHasAccessToApi() {
        OnStage.theActorCalled(CAMILA.toString());
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at(CAMILA.recall(THE_REST_API_BASE_URL)));
    }

    @Cuando("realizo una solicitud para comprobar la salud del servicio de reservas")
    public void requestToCheckTheHealthOfBookingService() {

        System.out.println("consumo get al recurso /ping");
    }

    @Entonces("se deberá obtener una respuesta exitosa del sistema")
    public void getSuccessfulResponseCode() {

        System.out.println("validar - status code: 201 Created");
    }
}
