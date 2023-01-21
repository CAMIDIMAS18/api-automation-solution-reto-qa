# language: es

@updateBooking
Necesidad del negocio: Actualizar una reserva
  Como cliente
  Quiero actualizar la información de mi reserva
  Para poder actualizar y eliminar reservas

  Regla de negocio: Para actualizar la información de la reserva, el cliente debe estar autenticado.

    @FunctionalTest
    Regla: Se deben enviar todos los campos de la reservación
    Esquema del escenario: [YAPE-UD-010] Validar la actualización de una reserva actual
      Dado que la cliente desea actualizar la reservación de su próximo viaje
      Cuando ella ingrese todos los datos de su reserva con las actualizaciones deseadas
        | firstname   | lastname   | totalprice   | depositpaid   | checkinDate   | checkoutDate   | additionalneeds   |
        | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkinDate> | <checkoutDate> | <additionalneeds> |
      Entonces visualizará los detalles de la reserva con su nueva información
      Ejemplos:
        | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds              |
        | Camila    | Dimax    | 2000       | true        | 2024-11-01  | 2024-12-09   | Breakfast, dinner and snacks |

    @FunctionalTest
    Regla: No se tienen campos mínimos para enviar la solicitud
    Esquema del escenario:[YAPE-UD-011] Validar la actualización parcial una reserva actual
      Dado que la cliente desea actualizar la reservación de su próximo viaje
      Cuando ella solicita la actualización para la fecha de ingreso '<checkIn>' & la fecha de salida '<checkOut>'
      Entonces visualizará los campos de la reserva con la nueva información
      Ejemplos:
        | checkIn    | checkOut   |
        | 2023-01-27 | 2023-02-07 |