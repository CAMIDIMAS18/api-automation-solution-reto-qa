# language: es

@getBookings
Necesidad del negocio: Consultar las reservaciones agendadas
  Como usuario
  Quiero consultar la información de una reserva
  Para validar que la reservación se realizó correctamente

  Regla: Los parámetros de búsqueda permitidos son: idBooking, firstname & lastname, Checkin to Checkout.

    Antecedentes:
      Dado que la cliente desea consultar la reservación de su próximo viaje

    @getBookingDetails @FunctionalTest
    Escenario: [YAPE-GB-004] Validar que se pueda consultar una reserva por su BookingId
      Cuando ella filtre la consulta por el parámetro "ID Booking"
      Entonces visualizará los detalles de la reserva

    @getAllBookings @FunctionalTest
    Escenario: [YAPE-GB-005] Validar que se puedan consultar todas las reservas existentes
      Cuando ella filtre la consulta por el parámetro "All Bookings"
      Entonces visualizará el Id de su reserva

    @getBookingByCustomerNames @FunctionalTest
    Escenario: [YAPE-GB-006] Validar que se pueda consultar una reserva por los nombres del cliente
      Cuando ella filtre la consulta por el parámetro "Customer Names"
      Entonces visualizará el Id de su reserva

    @getBookingByDates @FunctionalTest
    Escenario: [YAPE-GB-007] Validar que se pueda consultar una reserva por un rango de fechas especificas
      Cuando ella filtre la consulta por el parámetro "Dates"
      Entonces visualizará todos los bookingIDs encontrados para el rango de fechas
      #Entonces visualizará el Id de su reserva

    @getBookingBySpecificDates @FunctionalTest
    Esquema del escenario: [YAPE-GB-008] Ver todos los IDs de las reservas consultando por fechas especificas
      Cuando ella filtre la consulta por las fechas entre "<checkin>" a "<checkout>"
      Entonces visualizará todos los bookingIDs encontrados para el rango de fechas
      Ejemplos:
        | checkin    | checkout   |
        | 2018-01-01 | 2022-01-23 |