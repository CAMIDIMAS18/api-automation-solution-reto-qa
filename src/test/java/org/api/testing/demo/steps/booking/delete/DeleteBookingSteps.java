package org.api.testing.demo.steps.booking.delete;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

public class DeleteBookingSteps {
    @Cuando("ella/el solicite la eliminación con su código único de reserva")
    public void deleteBooking() {

        System.out.println("Realizar el consumo del método DELETE con el bookingID");
    }

    @Entonces("ya no podrá visualizar su reserva en el sistema")
    public void checkBookingDeleted() {

        System.out.println("validar - status code: 201 Created");
        System.out.println("Sirve el mismo que se haga en el consumo del recurso /ping");

    }
}
