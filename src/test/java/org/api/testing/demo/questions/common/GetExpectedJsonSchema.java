package org.api.testing.demo.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.api.testing.demo.utils.constants.Constants.SUCCESSFUL_VALIDATION;

@Subject("the response schema matches json schema defined in file '#fieldName'")
public class GetExpectedJsonSchema implements Question<Boolean> {

    private final String fieldName;

    public GetExpectedJsonSchema(String fieldName) {
        this.fieldName = fieldName;
    }

    public static GetExpectedJsonSchema theJsonSchemaExpectIs(String fieldName) {
        return new GetExpectedJsonSchema(fieldName);
    }

    /**
     * Obtiene el esquema de la respuesta y compara con respecto al definido
     */
    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse(SUCCESSFUL_VALIDATION,
                        response -> response.assertThat()
                                .body(matchesJsonSchemaInClasspath("schemas/" + fieldName + ".json"))
                )
        );
        return true;
    }
}