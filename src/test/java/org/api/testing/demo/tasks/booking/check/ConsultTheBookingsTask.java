package org.api.testing.demo.tasks.booking.check;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.interactions.ExecuteGetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;

public class ConsultTheBookingsTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultTheBookingsTask.class.getSimpleName());
    private final String resource;

    public ConsultTheBookingsTask(String resource) {
        this.resource = resource;
    }

    public static ConsultTheBookingsTask with(String resource) {
        return instrumented(ConsultTheBookingsTask.class, resource);
    }

    @Override
    @Step("{0} check registered bookings, filtering by BookingId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecuteGetRequest
                .with(
                        resource,
                        headersDefault()
                )
        );
        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPrint();
    }
}
