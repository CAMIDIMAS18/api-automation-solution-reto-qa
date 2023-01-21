package org.api.testing.demo.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.concurrent.TimeUnit;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the response time of the request")
public class ResponseTimeQuestion implements Question<Long> {

    public static ResponseTimeQuestion responseTimeIs() {
        return new ResponseTimeQuestion();
    }

    @Override
    public Long answeredBy(Actor actor) {
        return lastResponse().getTimeIn(TimeUnit.MILLISECONDS);
    }
}
