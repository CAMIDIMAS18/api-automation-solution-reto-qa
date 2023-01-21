package org.api.testing.demo.tasks.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.api.testing.demo.interactions.ExecuteDeleteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsumeDeleteTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeDeleteTask.class.getSimpleName());
    private final int id;
    private final Map<String, String> headers;


    public ConsumeDeleteTask(int id, Map<String, String> headers) {
        this.id = id;
        this.headers = headers;
    }

    public static ConsumeDeleteTask with(int id, Map<String, String> headers) {
        return instrumented(ConsumeDeleteTask.class, id, headers);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecuteDeleteRequest
                .with(id, headers)
        );

        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPeek();
    }
}