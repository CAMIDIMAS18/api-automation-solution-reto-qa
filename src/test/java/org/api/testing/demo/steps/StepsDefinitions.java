package org.api.testing.demo.steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.api.testing.demo.environments.Endpoints;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActor;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class StepsDefinitions {
    @Dado("que Camila desea {string} una reserva")
    public void preparingAPI(String requestOption) {
        if (requestOption.contains("crear")) {
            theActorInTheSpotlight().describedAs("un huésped que puede crear, consultar, actualizar y eliminar reservas");
            theActor("Camila").can(CallAnApi.at(Endpoints.BASE_URL));

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


    @Cuando("el diligencie la siguiente información en los campos correspondientes")
    public void sendRequestToApi(List<Map<String, String>> dataMapList) {

    }

    @Entonces("debera validar que la reservación fue creada con éxito")
    public void validateServiceResponse() {

    }


}
