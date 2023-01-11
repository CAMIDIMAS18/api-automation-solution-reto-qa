package org.api.testing.demo.tasks.booking;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.abilities.Authenticate;
import org.api.testing.demo.interactions.SendPostRequest;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.demo.environments.Endpoints.CREATE_BOOKING;
import static org.api.testing.demo.models.headers.GetHeaderModel.headersAuthentication;
import static org.api.testing.demo.models.request.CreateBookingRequestBuilder.Builder.andRequestBody;

public class AuthenticateUser implements Task {

    public static AuthenticateUser withUserInformation() {
        return instrumented(AuthenticateUser.class);
    }

    private Authenticate authenticated(Actor actor) {
        return Authenticate.as(actor);
    }

    //Modificar ENDPOINT, Y MODELO BUILD
    @Override
    @Step("se realiza la autenticación de: {0}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SendPostRequest.with(
                CREATE_BOOKING,
                headersAuthentication(),
                andRequestBody()
                        .withFirstName(authenticated(actor).username())
                        .andLastName(authenticated(actor).password())
                        .build()
        ));
    }
}
