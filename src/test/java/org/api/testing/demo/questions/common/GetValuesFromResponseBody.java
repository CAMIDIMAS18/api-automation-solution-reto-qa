package org.api.testing.demo.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class GetValuesFromResponseBody implements Question {

    private final String jsonPath;

    public GetValuesFromResponseBody(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static Question<Object> theAttributeValue(String jsonPath) {
        return new GetValuesFromResponseBody(jsonPath);
    }

    @Override
    public Object answeredBy(Actor actor) {
        return lastResponse().jsonPath().get(jsonPath);
    }
}