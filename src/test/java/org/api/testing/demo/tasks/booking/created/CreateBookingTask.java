package org.api.testing.demo.tasks.booking.created;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.interactions.ExecutePostRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;
import static org.api.testing.demo.models.request.CreateBookingRequestBuilder.Builder.andRequestBody;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.common.JsonUtils.parseJsonObject;
import static org.api.testing.demo.utils.constants.Constants.BOOKING_ID;
import static org.api.testing.demo.utils.environments.Endpoints.CREATE_BOOKING;

public class CreateBookingTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBookingTask.class.getSimpleName());
    private final Map<String, String> bookingInformation;

    public CreateBookingTask(Map<String, String> bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    public static CreateBookingTask withInformationRequested(Map<String, String> bookingInformation) {
        return instrumented(CreateBookingTask.class, bookingInformation);
    }

    @Override
    @Step("booking registration")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecutePostRequest.with(
                CREATE_BOOKING,
                headersDefault(),
                andRequestBody()
                        .withFirstName(bookingInformation.get("firstname"))
                        .andLastName(bookingInformation.get("lastname"))
                        .andTotalPrice(Integer.valueOf(bookingInformation.get("totalprice")))
                        .andDepositPaid(Boolean.valueOf(bookingInformation.get("depositpaid")))
                        .andBookingDates(bookingInformation.get("checkinDate"), bookingInformation.get("checkoutDate"))
                        .andAdditionalNeeds(bookingInformation.get("additionalneeds"))
                        .build()
        ));
        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPeek();

        Integer bookingId = parseJsonObject(lastResponse().getBody().asString()).get("bookingid").getAsInt();
        LOGGER.info("Booking Id is: {}", bookingId);
        CAMILA.remember(BOOKING_ID, bookingId);
    }
}
