package org.api.testing.demo.steps.booking.common;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.api.testing.demo.models.common.BookingData;
import org.api.testing.demo.tasks.common.LoadDataTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.*;

public class CommonSteps {

    @Dado("que la/el cliente desea crear/consultar/actualizar/eliminar una/la reservación de/para su próximo viaje")
    public void preparingAPI() {
        OnStage.theActorCalled(CAMILA.toString());
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at(CAMILA.recall(THE_REST_API_BASE_URL)));
    }

    @Y("se carga su información al sistema")
    public void loadTestDataIntoSystem(List<Map<String, String>> datos) {
        theActorInTheSpotlight().wasAbleTo(LoadDataTask.informationBooking(datos.get(0)));
        CAMILA.remember(BOOKING_DATA, datos);

        Map<String, Object> pathParamsWithNames = new HashMap<>();
        pathParamsWithNames.put("firstname", BookingData.getData().get("firstname"));
        pathParamsWithNames.put("lastname", BookingData.getData().get("lastname"));
        CAMILA.remember(PATH_PARAMS_WITH_NAMES, pathParamsWithNames);

        Map<String, Object> pathParamsWithDates = new HashMap<>();
        pathParamsWithDates.put("checkin", BookingData.getData().get("checkinDate"));
        pathParamsWithDates.put("checkout", BookingData.getData().get("checkoutDate"));
        CAMILA.remember(PATH_PARAMS_WITH_DATES, pathParamsWithDates);

    }
}
