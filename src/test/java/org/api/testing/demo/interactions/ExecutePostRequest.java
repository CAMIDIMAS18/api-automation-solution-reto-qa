package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.utils.exceptions.GenericRuntimeException;
import org.api.testing.demo.models.request.CreateBookingRequestBuilder;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.EXCEPTION_ERROR_CONSUMPTION_SERVICE;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;

public class ExecutePostRequest implements Interaction {

    private final String endPoint;
    private final CreateBookingRequestBuilder requestModelBuilder;
    private final Map<String, String> headers;

    public ExecutePostRequest(String endPoint, Map<String, String> headers, CreateBookingRequestBuilder requestModelBuilder) {
        this.endPoint = endPoint;
        this.headers = headers;
        this.requestModelBuilder = requestModelBuilder;
    }

    public static ExecutePostRequest with(String endPoint, Map<String, String> headers, CreateBookingRequestBuilder requestModelBuilder) {
        return instrumented(ExecutePostRequest.class, endPoint, headers, requestModelBuilder);
    }

    @Override
    @Step("send a request to service")
    public <T extends Actor> void performAs(T actor) {

        SerenityRest.rest();
        actor.attemptsTo(
                Post.to(endPoint)
                        .with(requestSpecification -> requestSpecification
                                .headers(headers)
                                .body(requestModelBuilder.getRequestBody())
                                .relaxedHTTPSValidation()
                                .log().all())
        );

        if (SerenityRest.lastResponse().statusCode() != OK.getHttpStatusCode()) {
            throw new GenericRuntimeException(EXCEPTION_ERROR_CONSUMPTION_SERVICE);
        }
    }
}