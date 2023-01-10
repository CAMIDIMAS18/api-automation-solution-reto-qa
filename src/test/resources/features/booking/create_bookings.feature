# language: es

@CreateBookings
Necesidad del negocio: Crear una nueva reserva

  Como usuario
  Quiero realizar una reservación
  Para agendar mi próximo viaje

  Regla de negocio: Los siguientes campos son obligatorios para la creación exitosa de una reserva:
  firstName, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds.

    Antecedentes:
      Dado que Camila desea crear una reserva

    @CreateBooking
    Esquema del escenario: YAPE-001 - Crear una nueva reserva
      Cuando ella ingrese la siguiente información en los campos correspondientes a la "creación"
        | Primer Nombre | Primer Apellido | Precio Reserva | Deposito      | Fecha Registro | Fecha Salida   | Servicios Adicionales |
        | <firstname>   | <lastname>      | <totalprice>   | <depositpaid> | <checkinDate>  | <checkoutDate> | <additionalneeds>     |
      Entonces deberá validar que la reservación fue creada con éxito
      Ejemplos:
        | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds   |
        | Camila    | Dimas    | 9000       | true        | 2023-01-07  | 2023-01-07   | Breakfast, Dinner |


    @NotCreateBooking
    Escenario: No crear una nueva reserva
      Cuando el "registre" una nueva reserva con parámetro incorrecto
      Entonces no se deberá realizar la creación de la reserva