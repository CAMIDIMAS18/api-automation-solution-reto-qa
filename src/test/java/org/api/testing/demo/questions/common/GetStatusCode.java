package org.api.testing.demo.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the status code of the response")
public class GetStatusCode implements Question<Integer> {

    public static GetStatusCode httpResponseStatusCodeIs() {
        return new GetStatusCode();
    }

    /**
     * Obtiene el código de estado HTTP de la respuesta
     */
    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse().getStatusCode();
    }
}
