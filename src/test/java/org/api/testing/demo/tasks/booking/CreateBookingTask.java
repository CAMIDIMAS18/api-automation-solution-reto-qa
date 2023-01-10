package org.api.testing.demo.tasks.booking;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.interactions.RequestPostHttpMethod;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.environments.Endpoints.CREATE_BOOKING;
import static org.api.testing.demo.models.request.CreateBookingRequestBuilder.Builder.postApi;

public class CreateBookingTask implements Task {

    private final Map<String, String> bookingInformation;

    public CreateBookingTask(Map<String, String> bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    public static CreateBookingTask withBookingInformation(Map<String, String> bookingInformation) {
        return instrumented(CreateBookingTask.class, bookingInformation);
    }

    @Override
    @Step("{0} realiza la creación de su reserva")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(RequestPostHttpMethod.withData(
                CREATE_BOOKING,
                postApi()
                        .withFirstName(bookingInformation.get("firstname"))
                        .andLastName(bookingInformation.get("lastname"))
                        .andTotalPrice(Integer.valueOf(bookingInformation.get("totalprice")))
                        .andDepositPaid(Boolean.valueOf(bookingInformation.get("depositpaid")))
                        .andCheckInBookingDate(bookingInformation.get("checkinDate"))
                        .andCheckOutBookingDate(bookingInformation.get("checkoutDate"))
                        .andAdditionalNeeds(bookingInformation.get("additionalneeds"))
                        .build()
        ));
    }
}
