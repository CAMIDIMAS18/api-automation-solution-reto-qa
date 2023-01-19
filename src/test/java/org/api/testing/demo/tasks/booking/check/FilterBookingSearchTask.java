package org.api.testing.demo.tasks.booking.check;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.interactions.ExecuteGetRequestWithPathParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;

public class FilterBookingSearchTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterBookingSearchTask.class.getSimpleName());
    private final String resource;
    private final Map<String, Object> pathParams;

    public FilterBookingSearchTask(String resource, Map<String, Object> pathParams) {
        this.resource = resource;
        this.pathParams = pathParams;
    }

    public static FilterBookingSearchTask withParams(String resource, Map<String, Object> pathParams) {
        return instrumented(FilterBookingSearchTask.class, resource, pathParams);
    }

    @Override
    @Step("{0} query registered reservations, filtering by their names")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecuteGetRequestWithPathParams
                .with(
                        resource,
                        headersDefault(),
                        pathParams
                )
        );
        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPrint();
    }
}
