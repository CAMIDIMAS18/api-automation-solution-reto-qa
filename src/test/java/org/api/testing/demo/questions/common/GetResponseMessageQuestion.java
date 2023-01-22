package org.api.testing.demo.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the response message")
public class GetResponseMessageQuestion implements Question<String> {

    public static GetResponseMessageQuestion responseMessageIs() {
        return new GetResponseMessageQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        return lastResponse().getBody().asString();
    }
}