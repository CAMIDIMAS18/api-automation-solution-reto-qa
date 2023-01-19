package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.api.testing.demo.utils.exceptions.GenericRuntimeException;

import java.util.Map;

import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.demo.utils.environments.Endpoints.UPDATE_BOOKING;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.EXCEPTION_ERROR_CONSUMPTION_SERVICE;

public class ExecutePutRequest implements Interaction {
    private final String body;
    private final int id;
    private final Map<String, String> headers;

    public ExecutePutRequest(String body, int id, Map<String, String> headers) {
        this.id = id;
        this.headers = headers;
        this.body = body;
    }

    public static ExecutePutRequest with(int id, Map<String, String> headers, String body) {
        return Tasks.instrumented(ExecutePutRequest.class, id, headers, body);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Put.to(UPDATE_BOOKING + "/" + id)
                        .with(requestSpecification -> requestSpecification
                                .headers(headers)
                                .body(body)
                                .relaxedHTTPSValidation()
                                .log().all())
        );

        if (SerenityRest.lastResponse().statusCode() != OK.getHttpStatusCode()) {
            throw new GenericRuntimeException(EXCEPTION_ERROR_CONSUMPTION_SERVICE);
        }
    }
}
