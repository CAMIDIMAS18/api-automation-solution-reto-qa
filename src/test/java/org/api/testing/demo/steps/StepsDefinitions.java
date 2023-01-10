package org.api.testing.demo.steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.api.testing.demo.tasks.booking.CreateBookingTask;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.environments.Endpoints.BASE_URL;

public class StepsDefinitions {
    @Dado("que {} desea crear/consultar/actualizar/eliminar una reserva")
    public void preparingAPI(String actorName) {
        OnStage.theActorCalled(actorName).describedAs("es un huésped que puede crear, consultar, actualizar y eliminar reservas");
        theActorInTheSpotlight().whoCan(CallAnApi.at(BASE_URL));
    }

    @Cuando("el/ella ingrese la siguiente información en los campos correspondientes a la {string}")
    public void sendRequestToApi(String requestOption, List<Map<String, String>> dataMapList) {

        if (requestOption.contains("creación")) {
            System.out.println("Opción:" + requestOption);

            for (Map<String, String> data : dataMapList) {

                theActorInTheSpotlight().attemptsTo(CreateBookingTask.withBookingInformation(data));

                String responseBody = lastResponse().getBody().prettyPrint();
            }

        } else if (requestOption.contains("consultar")) {
            System.out.println("Opción:" + requestOption);

        } else if (requestOption.contains("actualizar")) {
            System.out.println("Opción:" + requestOption);

        } else if (requestOption.contains("eliminar")) {
            System.out.println("Opción:" + requestOption);

        } else {
            //throw new NotOperationWLFoundException(optionWL);
        }
    }

    @Entonces("deberá validar que la reservación fue creada con éxito")
    public void validateServiceResponse() {

    }


}
