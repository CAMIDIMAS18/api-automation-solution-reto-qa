package org.api.testing.demo.questions.booking.check;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import org.api.testing.demo.models.common.BookingData;
import org.api.testing.demo.utils.exceptions.AssertionsServices;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

@Subject("the fields and values obtained in the response are validated")
public class TheQueryFieldsAndValuesAre implements Question<Boolean> {

    public static TheQueryFieldsAndValuesAre expected() {
        return new TheQueryFieldsAndValuesAre();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse(response -> response
                        .assertThat()
                        .body("firstname", equalTo(BookingData.getData().get("firstname")))
                        .and().body("lastname", equalTo(BookingData.getData().get("lastname")))
                        .and().body("totalprice", equalTo(parseInt(BookingData.getData().get("totalprice").toString())))
                        .and().body("depositpaid", equalTo(parseBoolean((BookingData.getData().get("depositpaid").toString()))))
                        .and().body("bookingdates.checkin", equalTo(BookingData.getData().get("checkinDate")))
                        .and().body("bookingdates.checkout", equalTo(BookingData.getData().get("checkoutDate")))
                        .and().body("additionalneeds", equalTo(BookingData.getData().get("additionalneeds")))
                ).orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED)
        );
        return true;
    }
}
