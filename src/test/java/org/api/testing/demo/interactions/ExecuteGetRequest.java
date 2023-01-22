package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ExecuteGetRequest implements Interaction {
    private final String resource;
    private final Map<String, String> headers;

    public ExecuteGetRequest(String resource, Map<String, String> headers) {
        this.resource = resource;
        this.headers = headers;
    }

    public static ExecuteGetRequest with(String resource, Map<String, String> headers) {
        return instrumented(ExecuteGetRequest.class, resource, headers);
    }

    @Override
    @Step("send a request to service")
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Get.resource(resource)
                        .with(requestSpecification -> requestSpecification
                                .headers(headers)
                                .relaxedHTTPSValidation()
                                .log().all())
        );
    }
}