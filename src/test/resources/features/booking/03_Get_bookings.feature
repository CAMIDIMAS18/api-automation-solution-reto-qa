# language: es

@getBookings
Necesidad del negocio: Consultar una reserva
  Como usuario
  Quiero consultar la información de una reserva
  Para validar que la reservación se realizó correctamente

  Regla: Los parámetros de búsqueda permitidos son: idBooking, firstname & lastname, Checkin to Checkout.

    Antecedentes:
      Dado que la cliente desea consultar la reservación de su próximo viaje

    @getBookingDetails @integrationTest
    Escenario: [YP-004] Validar que se pueda consultar una reserva por su BookingId
      Cuando ella filtre la consulta por el parámetro "ID Booking"
      Entonces visualizará los detalles de la reserva

    @getAllBookings @integrationTest
    Escenario: [YP-005] Validar que se puedan consultar todas las reservas existentes
      Cuando ella filtre la consulta por el parámetro "All Bookings"
      Entonces visualizará el Id de su reserva

    @getBookingByCustomerNames @integrationTest
    Escenario: [YP-006] Validar que se pueda consultar una reserva por los nombres del cliente
      Cuando ella filtre la consulta por el parámetro "Customer Names"
      Entonces visualizará el Id de su reserva

    @getBookingByDates @integrationTest
    Escenario: [YP-007] Validar que se pueda consultar una reserva por un rango de fechas especificas
      Cuando ella filtre la consulta por el parámetro "Dates"
      Entonces visualizará todos los bookingIDs encontrados para el rango de fechas
      #Entonces visualizará el Id de su reserva (BUG)

    @getBookingBySpecificDates @integrationTest
    Esquema del escenario: [YP-008] Ver todos los IDs de las reservas consultando por fechas especificas
      Cuando ella filtre la consulta por las fechas entre "<checkin>" a "<checkout>"
      Entonces visualizará todos los bookingIDs encontrados para el rango de fechas
      Ejemplos:
        | checkin    | checkout   |
        | 2018-01-01 | 2022-01-23 |