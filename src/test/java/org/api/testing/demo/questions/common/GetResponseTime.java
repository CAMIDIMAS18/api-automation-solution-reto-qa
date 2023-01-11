package org.api.testing.demo.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.concurrent.TimeUnit;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the response time of the request")
public class GetResponseTime implements Question<Long> {

    public static GetResponseTime responseTimeIs() {
        return new GetResponseTime();
    }

    /**
     * Obtiene el tiempo de respuesta
     */
    @Override
    public Long answeredBy(Actor actor) {
        return lastResponse().getTimeIn(TimeUnit.MILLISECONDS);
    }
}
