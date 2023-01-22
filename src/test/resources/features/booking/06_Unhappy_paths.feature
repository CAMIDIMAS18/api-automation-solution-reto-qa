# language: es

@UnHappyPaths
Necesidad del negocio: Validar que no se puedan crear/consultar/actualizar/eliminar reservas
  Yo como PM
  Quiero tener un mapeo de errores del sistema
  Para confirmar que el funcionamiento del servicio corresponde al esperado por negocio

  @notCreatedBooking @integrationTest
    #Status code: 500 Internal Server Error
  Esquema del escenario: [YP-012] Validar que no se pueda crear un reserva al no enviar un campo obligatorio
    Dado que la cliente desea crear la reservación de su próximo viaje
    Pero ella no envió el campo obligatorio lastname en la información solicitada
      | firstname   | totalprice   | depositpaid   | checkinDate   | checkoutDate   | additionalneeds   |
      | <firstname> | <totalprice> | <depositpaid> | <checkinDate> | <checkoutDate> | <additionalneeds> |
    Entonces no se deberá crear la reserva en el sistema
    Ejemplos:
      | firstname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds |
      | Camila    | 1000       | true        | 2025-01-07  | 2025-01-07   | Breakfast       |

  @notGetBookingDetails @integrationTest
    # status code: 404 Not Found
  Escenario: [YP-013] Validar que no se pueda consultar una reserva eliminada
    Dado que la cliente desea consultar la reservación de su próximo viaje
    Pero la reservación fue eliminada previamente
    Cuando ella consulte el ID de su reserva
    Entonces no se deberá visualizar la reserva en el sistema

  @notUpdateBooking @integrationTest
    # status code: 405 Method Not Allowed
  Escenario: [YP-014] Validar que no se pueda actualizar una reserva eliminada
    Dado que la cliente desea actualizar la reservación de su próximo viaje
    Pero la reservación fue eliminada previamente
    Cuando ella ingrese todos los datos de su reserva con las actualizaciones deseadas
      | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds      |
      | Camila    | Dimas    | 0          | true        | 2023-01-27  | 2023-02-07   | Breakfast and Dinner |
    Entonces no se deberá actualizar la reserva en el sistema

  @notDeleteBooking @integrationTest
    # status code: 403 Forbidden
  Escenario: [YP-015] Validar que no se pueda eliminar una reserva sin estar autenticado
    Dado que la cliente desea eliminar la reservación de su próximo viaje
    Pero no se autenticó correctamente en el sistema
    Cuando ella solicite la eliminación con su código único de reserva
    Entonces no se deberá eliminar la reserva en el sistema