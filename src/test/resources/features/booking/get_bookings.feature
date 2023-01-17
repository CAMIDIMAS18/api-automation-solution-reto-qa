# language: es

@getBookings
Necesidad del negocio: Consultar las reservaciones registradas

  Como usuario
  Quiero consultar la información de una reserva
  Para validar que la reservación se realizó correctamente

  Regla de negocio: 1) Los parámetros de búsqueda permitidos son:
  Id Booking, Customer Name, CheckIn/CheckOut Booking Date y/o All Bookings.

    @getAllBookings #$.[?(@.bookingid == '9332')].bookingid
    Escenario: Consultar todas las reservas existentes
      Cuando ella consulte todas las reservas registradas en el sistema
      Entonces podrá validar si su reserva se encuentra agendada

    @getBookingName
    Escenario: Consultar la existencia de una reservación por medio del nombre del cliente
      Cuando el "consulte" con el parámetro "name"
      Entonces debera comprobar que la información de la reserva encontrada es la correcta

    @getBookingDate
    Escenario: Consultar la existencia de una reservación por medio de las fechas de reservación
      Cuando el "consulte" con el parámetro "date"
      Entonces debera comprobar que la información de la reserva encontrada es la correcta

    @getBookingAllIds
    Escenario: Consultar la existencia de todas las reservaciones existentes
      Cuando el "consulte" con el parámetro "allIds"
      Entonces debera comprobar que su reserva se encuentra entre todas las reservaciones realizadas

    @NotGetBooking
    Esquema del escenario: No entregar información de una reserva no existente
      Cuando el "consulte" la reserva con el parámetro <parameter> erroneo
      Entonces no se deberá mostrar la información de la reserva
      Ejemplos:
        | parameter |
        | Id        |
        | name      |
        | date      |
        | allIds    |