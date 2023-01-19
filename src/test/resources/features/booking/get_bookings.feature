# language: es

@getBookings
Necesidad del negocio: Consultar las reservaciones agendadas
  Como usuario
  Quiero consultar la información de una reserva
  Para validar que la reservación se realizó correctamente

  Regla: Los parámetros de búsqueda permitidos son: idBooking, firstname & lastname, Checkin to Checkout, All Bookings.

    Antecedentes:
      Dado que la cliente desea consultar la reservación de su próximo viaje

    @viewBookingDetails @integrationTest
    Escenario: [YAPE-GB-002] Validar que se pueda consultar una reserva por su BookingId
      Cuando ella filtre la consulta por el parámetro "ID Booking"
      Entonces visualizará los detalles de la reserva

    @getAllBookings @integrationTest
    Escenario: [YAPE-GB-003] Validar que se puedan consultar todas las reservas existentes
      Cuando ella filtre la consulta por el parámetro "All Bookings"
      Entonces visualizará el Id de su reserva

    @getBookingByCustomerNames @integrationTest
    Escenario: [YAPE-GB-004] Validar que se pueda consultar una reserva por los nombres del cliente
      Cuando ella filtre la consulta por el parámetro "Customer Names"
      Entonces visualizará el Id de su reserva

    @getBookingByDates @integrationTest
    Escenario: [YAPE-GB-005] Validar que se pueda consultar una reserva por un rango de fechas especificas
      Cuando ella filtre la consulta por el parámetro "Dates"
      Entonces visualizará el Id de su reserva

    @viewByBookingDates
    Esquema del escenario: To view all the booking IDs by booking dates
      Cuando ella filtre la consulta por el parámetro "Dates"
      Entonces visualizará todos los bookingIDs encontrados
    Ejemplos:
    | checkin    | checkout   |
    | 2018-01-01 | 2021-12-31 |
    | 2010-01-01 | 2020-12-31 |