package org.api.testing.demo.steps.booking;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Pero;
import org.api.testing.demo.tasks.common.ConsumeGetTask;
import org.api.testing.demo.utils.exceptions.NotQueryParameterFoundException;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.demo.steps.hooks.Actors.CAMILA;
import static org.api.testing.demo.utils.constants.Constants.BOOKING_ID;
import static org.api.testing.demo.utils.environments.Endpoints.GET_BOOKING_BY_ID;

public class UnHappyPathsSteps {

    @Pero("ella no envió un campo obligatorio en la información solicitada")
    public void userDoesNotSendData() {

        System.out.println("No enviar el campo de checkin o checkout");

    }

    @Pero("la reservación fue eliminada previamente")
    public void bookingPreviouslyDeleted() {

        System.out.println("validar que la reserva se eliminó antes ¡¿");

    }

    @Cuando("ella consulte el ID de su reserva")
    public void ellaConsulteElDeSuReserva() {

        System.out.println("Realizar consultar con el booking ID eliminado");
        theActorInTheSpotlight().attemptsTo(ConsumeGetTask.with(GET_BOOKING_BY_ID + CAMILA.recall(BOOKING_ID)));

    }

    @Pero("no se autenticó correctamente en el sistema")
    public void improperAuthentication() {

        System.out.println("No hacer la autenticación");

    }

    @Entonces("no se deberá {} la reserva en el sistema")
    public void bookingProcessNotAllowed(String option) {

        switch (option) {
            case "crear":

                System.out.println("validar - Status code: 500 Internal Server Error");

                break;

            case "visualizar":

                System.out.println("validar - status code: 404 Not Found");

                break;

            case "actualizar":

                System.out.println("validar - status code: 405 Method Not Allowed");

                break;

            case "eliminar":

                System.out.println("validar - status code: 403 Forbidden");

                break;

            default:
                throw new NotQueryParameterFoundException(option);
        }

        System.out.println("validar tiempo respuesta");

    }
}

