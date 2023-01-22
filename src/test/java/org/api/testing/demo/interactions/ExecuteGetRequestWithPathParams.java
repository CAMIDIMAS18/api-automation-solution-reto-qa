package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.utils.exceptions.GenericRuntimeException;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.EXCEPTION_ERROR_CONSUMPTION_SERVICE;

public class ExecuteGetRequestWithPathParams implements Interaction {
    private final String resource;
    private final Map<String, String> headers;
    private final Map<String, Object> params;

    public ExecuteGetRequestWithPathParams(String resource, Map<String, String> headers, Map<String, Object> params) {
        this.resource = resource;
        this.headers = headers;
        this.params = params;
    }

    public static ExecuteGetRequestWithPathParams with(String resource, Map<String, String> headers, Map<String, Object> params) {
        return instrumented(ExecuteGetRequestWithPathParams.class, resource, headers, params);
    }

    @Override
    @Step("send a request to service")
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Get.resource(resource)
                        .with(requestSpecification -> requestSpecification
                                .headers(headers)
                                .pathParams(params)
                                .relaxedHTTPSValidation()
                                .log().all())
        );

        if (SerenityRest.lastResponse().statusCode() != OK.getHttpStatusCode()) {
            throw new GenericRuntimeException(EXCEPTION_ERROR_CONSUMPTION_SERVICE);
        }

    }
}