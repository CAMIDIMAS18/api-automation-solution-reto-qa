package org.api.testing.demo.steps.booking.update;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.api.testing.demo.utils.exceptions.AssertionsServices;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.questions.common.ExpectedJsonSchemaQuestion.theJsonSchemaExpectIs;
import static org.api.testing.demo.questions.common.GetValueFromResponseBodyQuestion.theAttributeValue;
import static org.api.testing.demo.questions.common.ResponseTimeQuestion.responseTimeIs;
import static org.api.testing.demo.questions.common.StatusCodeQuestion.httpResponseStatusCodeIs;
import static org.api.testing.demo.tasks.common.CreateTokenTask.createTokenInTheSystem;
import static org.api.testing.demo.utils.constants.Constants.AUTH_SHEMA;
import static org.api.testing.demo.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.demo.utils.exceptions.AssertionsServices.*;
import static org.hamcrest.Matchers.*;

public class CreateTokenSteps {
    @Cuando("ella ingrese sus credenciales de acceso")
    public void createToken() {

        theActorInTheSpotlight().attemptsTo(createTokenInTheSystem());

        theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(10000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(AUTH_SHEMA))
                .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
    }

    @Entonces("se autenticará en el sistema")
    public void tokenValidation() {
        theActorInTheSpotlight().should(
                seeThat("the token", theAttributeValue("token"), notNullValue())
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
    }
}
