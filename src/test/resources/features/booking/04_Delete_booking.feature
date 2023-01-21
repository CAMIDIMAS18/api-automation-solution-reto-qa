# language: es

@deleteBooking
Necesidad del negocio: Eliminar una reserva
  Como cliente
  Quiero eliminar mi reserva agenda
  Para poder cancelar la reservación en cualquier momento

  Regla de negocio: Para eliminar la información de la reserva, el cliente debe estar autenticado.

    @FunctionalTest @DELETE @Pending
    Escenario: [YAPE-DL-009] Validar la eliminación de una reserva actual
      Dado que la cliente desea eliminar la reservación de su próximo viaje
      Cuando ella solicite la eliminación con su código único de reserva
      Entonces ya no podrá visualizar su reserva en el sistema