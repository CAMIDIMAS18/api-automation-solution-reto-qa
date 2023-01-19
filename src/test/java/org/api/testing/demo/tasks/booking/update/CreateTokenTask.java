package org.api.testing.demo.tasks.booking.update;

import com.jayway.jsonpath.DocumentContext;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.interactions.ExecuteAuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;
import static org.api.testing.demo.models.request.CreateTokenBuilder.Builder.andRequestBody;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.common.JsonUtils.parseDocumentContextFromString;
import static org.api.testing.demo.utils.common.JsonUtils.parseJsonObject;
import static org.api.testing.demo.utils.constants.Constants.RESPONSE_BODY;
import static org.api.testing.demo.utils.constants.Constants.TOKEN;
import static org.api.testing.demo.utils.environments.Endpoints.*;

public class CreateTokenTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTokenTask.class.getSimpleName());

    public CreateTokenTask() {
    }

    public static CreateTokenTask createTokenInTheSystem() {
        return instrumented(CreateTokenTask.class);
    }

    @Override
    @Step("create token")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecuteAuthRequest.with(
                AUTH,
                headersDefault(),
                andRequestBody()
                        .withUsername(USER_NAME)
                        .andPassword(PASSWORD)
                        .build()
        ));
        LOGGER.info("Response Body Is: ");
        String response = lastResponse().getBody().prettyPrint();
        DocumentContext documentContextResponse = parseDocumentContextFromString(response);
        CAMILA.remember(RESPONSE_BODY, documentContextResponse);

        String token = parseJsonObject(lastResponse().getBody().asString()).get("token").getAsString();
        LOGGER.info("token is: {}", token);
        CAMILA.remember(TOKEN, token);
    }
}
