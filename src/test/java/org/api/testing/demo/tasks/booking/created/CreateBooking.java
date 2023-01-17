package org.api.testing.demo.tasks.booking.created;

import com.jayway.jsonpath.DocumentContext;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.interactions.SendPostRequest;
import org.api.testing.demo.utils.common.JsonUtils;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.utils.environments.Endpoints.CREATE_BOOKING;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;
import static org.api.testing.demo.models.request.CreateBookingRequestBuilder.Builder.andRequestBody;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.common.JsonUtils.parseJsonObject;
import static org.api.testing.demo.utils.constants.Constants.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateBooking implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBooking.class.getSimpleName());


    public static String response;

    public static Integer bookingId;

    private final Map<String, String> bookingInformation;


    public CreateBooking(Map<String, String> bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    public static CreateBooking withInformationRequested(Map<String, String> bookingInformation) {
        return instrumented(CreateBooking.class, bookingInformation);
    }

    @Override
    @Step("se realiza el proceso de creación de la reserva para {0} con la información ingresada")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SendPostRequest.with(
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
        response = lastResponse().getBody().prettyPrint();
        DocumentContext documentContext = JsonUtils.parseDocumentContextFromString(response);
        CAMILA.remember(RESPONSE_BODY, response);

        bookingId = parseJsonObject(lastResponse().getBody().asString()).get("bookingid").getAsInt();
        CAMILA.remember(BOOKING_ID, bookingId);
        LOGGER.info("Booking Id is: {}", bookingId);
    }
}
