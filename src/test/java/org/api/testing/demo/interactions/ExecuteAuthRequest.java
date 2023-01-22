package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.models.request.CreateTokenBuilder;
import org.api.testing.demo.utils.exceptions.GenericRuntimeException;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.EXCEPTION_ERROR_CONSUMPTION_SERVICE;

public class ExecuteAuthRequest implements Interaction {

    private final String endPoint;
    private final CreateTokenBuilder requestModelBuilder;
    private final Map<String, String> headers;

    public ExecuteAuthRequest(String endPoint, Map<String, String> headers, CreateTokenBuilder requestModelBuilder) {
        this.endPoint = endPoint;
        this.headers = headers;
        this.requestModelBuilder = requestModelBuilder;
    }

    public static ExecuteAuthRequest with(String endPoint, Map<String, String> headers, CreateTokenBuilder requestModelBuilder) {
        return instrumented(ExecuteAuthRequest.class, endPoint, headers, requestModelBuilder);
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
