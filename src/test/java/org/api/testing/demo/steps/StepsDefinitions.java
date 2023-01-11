package org.api.testing.demo.steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.api.testing.demo.tasks.booking.CreateBooking;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.steps.conf.Actors.CAMILA;
import static org.api.testing.demo.utils.common.JsonUtils.parseJsonObject;
import static org.api.testing.demo.utils.constants.Constants.URI;

public class StepsDefinitions {

    public static final String BODY = "body";

    @Dado("que la/el cliente desea crear/consultar/actualizar/eliminar una/la reservación de/para su próximo viaje")
    public void preparingAPI() {
        OnStage.theActorCalled(CAMILA.toString());
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at(CAMILA.recall(URI)));
    }

    @Cuando("el/ella ingresa la siguiente información solicitada para la {string} de la reserva")
    public void sendRequestToApi(String requestOption, List<Map<String, String>> dataMapList) {

        if (requestOption.contains("creación")) {
            System.out.println("Opción:" + requestOption);

            for (Map<String, String> data : dataMapList) {

                theActorInTheSpotlight().attemptsTo(CreateBooking.withInformationRequested(data));

                //Muestra la respuesta del servicio
                System.out.println("****Response");
                String responseBody = lastResponse().getBody().prettyPrint();

                //Trae el parametro bookingid
                Integer bookingId = parseJsonObject(lastResponse().getBody().asString()).get("bookingid").getAsInt();
                System.out.println("*** bookingid: " + bookingId);
                CAMILA.remember(BODY, responseBody);
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

    @Entonces("su solicitud se creará en el sistema con un número de registro único")
    public void validateServiceResponse() {
        CAMILA.recall(BODY);
    }
}
