package org.api.testing.demo.tasks.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.abilities.Authenticate;
import org.api.testing.demo.interactions.ExecutePostRequest;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersDefault;
import static org.api.testing.demo.models.request.CreateBookingRequestBuilder.Builder.andRequestBody;
import static org.api.testing.demo.utils.environments.Endpoints.CREATE_BOOKING;

public class AuthenticateUser implements Task {

    public static AuthenticateUser withUserInformation() {
        return instrumented(AuthenticateUser.class);
    }

    private Authenticate authenticated(Actor actor) {
        return Authenticate.as(actor);
    }

    //Modificar ENDPOINT, Y MODELO BUILD
    @Override
    @Step("authentication in the system for {0}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecutePostRequest.with(
                CREATE_BOOKING,
                headersDefault(),
                andRequestBody()
                        .withFirstName(authenticated(actor).username())
                        .andLastName(authenticated(actor).password())
                        .build()
        ));
    }
}
