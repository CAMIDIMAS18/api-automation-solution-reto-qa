package org.api.testing.demo.interactions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import org.api.testing.demo.models.request.CreateBookingRequestBuilder;

import java.util.Map;

import static org.api.testing.demo.utils.environments.Endpoints.PARTIAL_UPDATE_BOOKING;

public class ExecutePatchRequest implements Interaction {
    private final CreateBookingRequestBuilder requestModelBuilder;
    private final int id;
    private final Map<String, String> headers;

    public ExecutePatchRequest(int id, Map<String, String> headers, CreateBookingRequestBuilder requestModelBuilder) {
        this.id = id;
        this.headers = headers;
        this.requestModelBuilder = requestModelBuilder;
    }

    public static ExecutePatchRequest with(int id, Map<String, String> headers, CreateBookingRequestBuilder requestModelBuilder) {
        return Tasks.instrumented(ExecutePatchRequest.class, id, headers, requestModelBuilder);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Patch.to(PARTIAL_UPDATE_BOOKING + "/" + id)
                        .with(requestSpecification -> requestSpecification
                                .headers(headers)
                                .body(requestModelBuilder.getRequestBody())
                                .relaxedHTTPSValidation()
                                .log().all())
        );
    }
}
