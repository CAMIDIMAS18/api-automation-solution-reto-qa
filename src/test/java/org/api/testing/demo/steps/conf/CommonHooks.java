package org.api.testing.demo.steps.conf;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CommonHooks {
    public static EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("Juan");
        String theRestApiBaseUrl = environmentVariables.optionalProperty("environments.dev.baseurl")
                .orElse("environments.stg.baseurl");
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));
 /*       String theRestApiBaseUrl = environmentVariables.optionalProperty("environments.dev.baseurl")
                .orElse("environments.stg.baseurl");
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));
        theActorInTheSpotlight().describedAs("un huésped que puede crear, consultar, actualizar y eliminar reservas");
    */
    }
}