package org.api.testing.demo.questions.booking.check;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class TheBookingIdIs implements Question<Integer> {

    private final String jsonPath;

    public TheBookingIdIs(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static TheBookingIdIs theBookingId(Integer id) {
        return new TheBookingIdIs("$.[?(@.bookingid =='" + id + "')].bookingid");
    }

    @Override
    public Integer answeredBy(Actor actor) {
        String responseBody = lastResponse().getBody().asPrettyString();
        DocumentContext documentContext = JsonPath.using(Configuration.defaultConfiguration()).parse(responseBody);
        JsonElement jsonElement = JsonParser.parseString(documentContext.read(this.jsonPath).toString());
        return jsonElement.getAsInt();
    }
}