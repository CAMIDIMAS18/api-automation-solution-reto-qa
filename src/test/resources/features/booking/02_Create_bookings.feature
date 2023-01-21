# language: es

@createBooking
Necesidad del negocio: Crear una reserva
  Como cliente
  Quiero hacer una nueva reserva
  Para poder programar mi próximo viaje

  Regla: Los campos firstname, lastname, totalprice, depositPaid, checkIn, checkOut, additionalNeeds,
  son obligatorios para crear una reserva

    @FunctionalTest @POST
    Esquema del escenario: [YAPE-CB-003] Validar la creación de una nueva reserva
      Dado que la cliente desea crear la reservación de su próximo viaje
      Y se carga su información al sistema
        | firstname   | lastname   | totalprice   | depositpaid   | checkinDate   | checkoutDate   | additionalneeds   |
        | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkinDate> | <checkoutDate> | <additionalneeds> |
      Cuando ella solicita la creación de una reserva
      Entonces su solicitud se creará en el sistema con su información y un número de registro único
      Ejemplos:
        | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds |
        | Camila    | DimassX  | 1000       | true        | 2025-01-07  | 2025-01-07   | Breakfast       |