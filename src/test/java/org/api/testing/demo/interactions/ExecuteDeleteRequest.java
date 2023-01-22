package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.Map;

import static org.api.testing.demo.utils.environments.Endpoints.DELETE_BOOKING;

public class ExecuteDeleteRequest implements Interaction {
    private final int id;
    private final Map<String, String> headers;

    public ExecuteDeleteRequest(int id, Map<String, String> headers) {
        this.id = id;
        this.headers = headers;
    }

    public static ExecuteDeleteRequest with(int id, Map<String, String> headers) {
        return Tasks.instrumented(ExecuteDeleteRequest.class, id, headers);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Delete.from((DELETE_BOOKING + "/" + id))
                        .with(requestSpecification -> requestSpecification
                                .headers(headers)
                                .relaxedHTTPSValidation()
                                .log().all())
        );
    }
}