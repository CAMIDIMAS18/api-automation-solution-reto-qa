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
import static org.api.testing.demo.utils.environments.Endpoints.CREATE_BOOKING;

public class NotCreateBookingTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotCreateBookingTask.class.getSimpleName());
    private final Map<String, String> bookingInformation;

    public NotCreateBookingTask(Map<String, String> bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    public static NotCreateBookingTask withInformationIncomplete(Map<String, String> bookingInformation) {
        return instrumented(NotCreateBookingTask.class, bookingInformation);
    }

    @Override
    @Step("booking registration")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecutePostRequest.with(
                CREATE_BOOKING,
                headersDefault(),
                andRequestBody()
                        .withFirstName(bookingInformation.get("firstname"))
                        .andTotalPrice(Integer.valueOf(bookingInformation.get("totalprice")))
                        .andDepositPaid(Boolean.valueOf(bookingInformation.get("depositpaid")))
                        .andBookingDates(bookingInformation.get("checkinDate"), bookingInformation.get("checkoutDate"))
                        .andAdditionalNeeds(bookingInformation.get("additionalneeds"))
                        .build()
        ));
        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPeek();
    }
}
