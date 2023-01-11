package org.api.testing.demo.tasks.booking;

import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.interactions.RequestPostHttpMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.environments.Endpoints.CREATE_BOOKING;
import static org.api.testing.demo.models.request.CreateBookingRequestBuilder.Builder.postApi;

public class CreateBooking implements Task {

    private final Map<String, String> bookingInformation;

    public CreateBooking(Map<String, String> bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    public static CreateBooking withInformationRequested(Map<String, String> bookingInformation) {
        return instrumented(CreateBooking.class, bookingInformation);
    }

    @Override
    @Step("se realiza la tarea de creación de la reserva para {0} con la información ingresada")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(RequestPostHttpMethod.withData(
                CREATE_BOOKING,
                postApi()
                        .withFirstName(bookingInformation.get("firstname"))
                        .andLastName(bookingInformation.get("lastname"))
                        .andTotalPrice(Integer.valueOf(bookingInformation.get("totalprice")))
                        .andDepositPaid(Boolean.valueOf(bookingInformation.get("depositpaid")))
                        .andBookingDates(bookingInformation.get("checkinDate"), bookingInformation.get("checkoutDate"))
                        .andAdditionalNeeds(bookingInformation.get("additionalneeds"))
                        .build()
        ));
    }
}
