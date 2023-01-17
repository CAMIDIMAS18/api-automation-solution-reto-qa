package org.api.testing.demo.tasks.booking.created;

import org.api.testing.demo.exceptions.AssertionsServices;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.questions.common.GetValuesFromResponseBody.theAttributeValue;
import static org.hamcrest.Matchers.*;

public class ValidateInCreatedBookingResponse {
    private ValidateInCreatedBookingResponse() {
        //Nothing
    }

    public static void thatBookingIdWasGenerated() {
        theActorInTheSpotlight().should(
                seeThat("the booking Id", theAttributeValue("booking.firstname"), notNullValue())
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    public static void thatFirstNameIs(String firstName) {
        theActorInTheSpotlight().should(
                seeThat("the first name", theAttributeValue("booking.firstname"), is(equalTo(firstName)))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    public static void thatLastNameIs(String lastName) {
        theActorInTheSpotlight().should(
                seeThat("the last name", theAttributeValue("booking.lastname"), is(equalTo(lastName)))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    public static void thatTotalPriceIs(String totalPrice) {
        theActorInTheSpotlight().should(
                seeThat("the total price", theAttributeValue("booking.totalprice"), is(equalTo(Integer.parseInt(totalPrice))))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    public static void thatDepositPaidIs(String depositPaid) {
        theActorInTheSpotlight().should(
                seeThat("the deposit paid", theAttributeValue("booking.depositpaid"), is(equalTo(Boolean.parseBoolean(depositPaid))))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    public static void thatCheckInDateIs(String checkInDate) {
        theActorInTheSpotlight().should(
                seeThat("the checkIn Date", theAttributeValue("booking.bookingdates.checkin"), is(equalTo(checkInDate)))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    public static void thatCheckOutDateIs(String checkOutDate) {
        theActorInTheSpotlight().should(
                seeThat("the checkOut Date", theAttributeValue("booking.bookingdates.checkout"), is(equalTo(checkOutDate)))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }

    public static void thatAdditionalNeedsIs(String additionalNeeds) {
        theActorInTheSpotlight().should(
                seeThat("the checkOut Date", theAttributeValue("booking.additionalneeds"), is(equalTo(additionalNeeds)))
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }
}

