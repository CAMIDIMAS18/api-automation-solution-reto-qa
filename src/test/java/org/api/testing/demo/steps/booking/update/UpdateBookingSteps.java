package org.api.testing.demo.steps.booking.update;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import java.util.List;
import java.util.Map;

public class UpdateBookingSteps {
    @Cuando("ella/el ingrese todos los datos de su reserva con las actualizaciones deseadas")
    public void updateBooking(List<Map<String, String>> datos) {

        System.out.println("Realizar el consumo del método PUT con el bookingID");
        System.out.println("Guardar esta información: " + datos);
        System.out.println("Validar status code 200 OK");
        System.out.println("Validar json schema - GET_BOOKING_DETAILS_SCHEMA ");
        System.out.println("Validar tiempo de respuesta");

    }

    @Cuando("ella/el solicita la actualización para la fecha de ingreso {string} & la fecha de salida {string}")
    public void partialBookingUpdate(String checkIn, String checkOut) {

        System.out.println("Realizar el consumo del método PATCH con el bookingID");
        System.out.println("Guardar esta información: " + checkIn + ", " + checkOut);
        System.out.println("Validar status code 200 OK");
        System.out.println("Validar json schema - GET_BOOKING_DETAILS_SCHEMA");
        System.out.println("Validar tiempo de respuesta");

    }

    @Entonces("visualizará los detalles de la reserva con su nueva información")
    public void validateTheBookingUpdate() {

        System.out.println("Validar todos los detalles de la reserva");
        System.out.println("Validar tarea donde se validan, está para hacerse solo con los datos cargados al inicio");

    }
}
