package org.api.testing.demo.tasks.common;

import com.jayway.jsonpath.DocumentContext;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.api.testing.demo.interactions.ExecutePutRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.models.request.CreateBookingRequestBuilder.Builder.andRequestBody;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.common.JsonUtils.parseDocumentContextFromString;
import static org.api.testing.demo.utils.constants.Constants.RESPONSE_BODY;

public class ConsumePutTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumePutTask.class.getSimpleName());
    private final int id;
    private final Map<String, String> headers;
    private final Map<String, String> newBookingInformation;


    public ConsumePutTask(int id, Map<String, String> newBookingInformation, Map<String, String> headers) {
        this.id = id;
        this.newBookingInformation = newBookingInformation;
        this.headers = headers;
    }

    public static ConsumePutTask withNewInformationRequested(int id, Map<String, String> newBookingInformation, Map<String, String> headers) {
        return instrumented(ConsumePutTask.class, id, newBookingInformation, headers);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecutePutRequest
                .with(id,
                        headers,
                        andRequestBody()
                                .withFirstName(newBookingInformation.get("firstname"))
                                .andLastName(newBookingInformation.get("lastname"))
                                .andTotalPrice(Integer.valueOf(newBookingInformation.get("totalprice")))
                                .andDepositPaid(Boolean.valueOf(newBookingInformation.get("depositpaid")))
                                .andBookingDates(newBookingInformation.get("checkinDate"), newBookingInformation.get("checkoutDate"))
                                .andAdditionalNeeds(newBookingInformation.get("additionalneeds"))
                                .build()
                )
        );
        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPeek();

        String response = lastResponse().getBody().asPrettyString();
        DocumentContext documentContextResponse = parseDocumentContextFromString(response);
        CAMILA.remember(RESPONSE_BODY, documentContextResponse);
    }
}
