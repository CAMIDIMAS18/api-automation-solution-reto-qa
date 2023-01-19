package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import org.api.testing.demo.utils.exceptions.GenericRuntimeException;

import java.util.Map;

import static org.api.testing.demo.utils.enums.HttpStatusCodes.CREATED;
import static org.api.testing.demo.utils.environments.Endpoints.DELETE_BOOKING;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.EXCEPTION_ERROR_CONSUMPTION_SERVICE;

public class ExecuteDeleteRequest implements Interaction {
    private final int id;
    private final Map<String, String> headers;
    private final Map<String, Object> params;

    public ExecuteDeleteRequest(int id, Map<String, String> headers, Map<String, Object> params) {
        this.id = id;
        this.headers = headers;
        this.params = params;
    }

    public static ExecuteDeleteRequest with(int id, Map<String, String> headers, Map<String, Object> params) {
        return Tasks.instrumented(ExecuteDeleteRequest.class, id, headers, params);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Delete.from((DELETE_BOOKING + "/" + id))
                        .with(requestSpecification -> requestSpecification
                                .headers(headers)
                                .pathParams(params)
                                .relaxedHTTPSValidation()
                                .log().all())
        );

        if (SerenityRest.lastResponse().statusCode() != CREATED.getHttpStatusCode()) {
            throw new GenericRuntimeException(EXCEPTION_ERROR_CONSUMPTION_SERVICE);
        }
    }
}