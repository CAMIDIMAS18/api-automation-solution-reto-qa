package org.api.testing.demo.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the value matches the expected")
public class GetValueFromResponseBodyQuestion implements Question<Object> {

    private final String jsonPath;

    public GetValueFromResponseBodyQuestion(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public static Question<Object> theAttributeValue(String jsonPath) {
        return new GetValueFromResponseBodyQuestion(jsonPath);
    }

    @Override
    public Object answeredBy(Actor actor) {
        return lastResponse().getBody().jsonPath().get(jsonPath);
    }
}