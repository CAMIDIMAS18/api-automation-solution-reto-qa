# language: es

@CreateBookings
Necesidad del negocio: Realizar la creación de una nueva reserva

  Como cliente
  Quiero realizar una reservación
  Para agendar mi próximo viaje

  Regla de negocio: Para la creación exitosa de una reserva se deberán enviar los siguientes campos obligatorios:
  firstName, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds.

    Antecedentes:
      Dado que la cliente desea crear la reservación de su próximo viaje

    @CreateBooking
      #CB - Create Booking
    Esquema del escenario: [YAPE-CB-001] Crear una nueva reserva
      Cuando ella ingresa la siguiente información solicitada para la "creación" de la reserva
        | firstname   | lastname   | totalprice   | depositpaid   | checkinDate   | checkoutDate   | additionalneeds   |
        | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkinDate> | <checkoutDate> | <additionalneeds> |
      Entonces su solicitud se creará en el sistema con un número de registro único
      Ejemplos:
        | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds   |
        | Camila    | Dimas    | 9000       | true        | 2023-01-07  | 2023-01-07   | Breakfast, Dinner |
        | Julian    | Bautista | 19000      | false       | 2023-06-08  | 2023-06-16   | [blank]           |


    @NotCreateBooking
    Esquema del escenario: [YAPE-CB-002] No crear nueva reserva por envío de valores inválidos
      Cuando ella "registre" una nueva reserva con parámetro incorrecto
      Entonces no se deberá realizar la creación de la reserva
      Ejemplos:
        | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds   |
        | Camila    | Dimas    | 9000       | true        | 2023-01-07  | 2023-01-07   | Breakfast, Dinner |