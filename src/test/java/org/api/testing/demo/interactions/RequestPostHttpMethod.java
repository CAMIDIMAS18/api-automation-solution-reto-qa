package org.api.testing.demo.interactions;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;
import org.api.testing.demo.models.request.CreateBookingRequestBuilder;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RequestPostHttpMethod implements Interaction {

    private final String endPoint;

    private final CreateBookingRequestBuilder requestModelBuilder; //como hago generico esto?, desacoplar el builder, que reciba cualquiera

    public RequestPostHttpMethod(String endPoint, CreateBookingRequestBuilder requestModelBuilder) {
        this.endPoint = endPoint;
        this.requestModelBuilder = requestModelBuilder;
    }

    public static RequestPostHttpMethod withData(String endPoint, CreateBookingRequestBuilder requestModelBuilder) {
        return instrumented(RequestPostHttpMethod.class, endPoint, requestModelBuilder);
    }

    @Override
    @Step("realizando la petición al servicio")
    public <T extends Actor> void performAs(T actor) {

        SerenityRest.rest();
        actor.attemptsTo(
                Post.to(endPoint)
                        .with(requestSpecification -> requestSpecification
                                .header("Accept", "application/json") //desacoplar los headers (builder -> probar)
                                .contentType(ContentType.JSON)
                                .body(requestModelBuilder.getRequestBody())
                                .relaxedHTTPSValidation()
                                .log().all())
        );
    }
}
