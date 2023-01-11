package org.api.testing.demo.steps.conf;

import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;
import org.api.testing.demo.abilities.Authenticate;

import static org.api.testing.demo.environments.Endpoints.BASE_URL;
import static org.api.testing.demo.steps.conf.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.URI;

public class CommonHooks {

    public static EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        CAMILA.remember(URI, BASE_URL);
        CAMILA.describedAs("un huésped que puede crear, consultar, actualizar y eliminar su reserva");
        CAMILA.whoCan(Authenticate.with("admin", "password123"));
    }

    /**
     * Las celdas vacías en una tabla de datos se transformarían en nulas en lugar de la cadena vacía.
     * Al usar replaceWithEmptyString = "[blank]" en un tipo de tabla de datos,
     * se puede insertar explícitamente una cadena vacía.
     */
    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
}