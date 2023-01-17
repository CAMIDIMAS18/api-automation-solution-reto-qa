# language: es

@createBookings
Necesidad del negocio: Realizar la creación de una nueva reserva

  Como cliente
  Quiero realizar una reservación
  Para agendar mi próximo viaje

  Regla de negocio: Para la creación exitosa de una reserva se deberán enviar los siguientes campos obligatorios:
  firstName, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds.

    @createBooking
    Esquema del escenario: [YAPE-CB-001] Crear una nueva reserva
      Dado que la cliente desea crear la reservación de su próximo viaje
      Cuando ella ingresa la siguiente información solicitada para la creación de la reserva
        | firstname   | lastname   | totalprice   | depositpaid   | checkinDate   | checkoutDate   | additionalneeds   |
        | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkinDate> | <checkoutDate> | <additionalneeds> |
      Entonces su solicitud se creará en el sistema con su información y un número de registro único
      Ejemplos:
        | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds |
        | Pepita    | Perez    | 10000      | false       | 2025-01-07  | 2025-01-07   | Breakfast       |
        #| Julian    | Bautista | 19000      | false       | 2023-06-08  | 2023-06-16   | [blank]           |