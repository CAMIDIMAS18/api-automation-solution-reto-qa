package org.api.testing.demo.tasks.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.api.testing.demo.interactions.ExecuteGetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;

public class ConsumeGetTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeGetTask.class.getSimpleName());
    private final String resource;

    public ConsumeGetTask(String resource) {
        this.resource = resource;
    }

    public static ConsumeGetTask with(String resource) {
        return instrumented(ConsumeGetTask.class, resource);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecuteGetRequest
                .with(
                        resource,
                        headersDefault()
                )
        );
        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPeek();
    }
}
