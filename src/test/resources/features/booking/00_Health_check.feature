# language: es

@healthCheck
Necesidad del negocio: Confirmar si la api está en funcionamiento
  Yo como tester
  Quiero hacer una verificación de estado simple
  Para confirmar si la api restful-booker se encuentra en funcionamiento

  @FunctionalTest @GET @Pending
  Escenario: [YAPE-HC-001] Confirmar si la api está en funcionamiento
    Dado que se requiere confirmar que la api funcione correctamente
    Cuando realizo una solicitud para comprobar la salud del servicio de reservas
    Entonces se deberá obtener una respuesta exitosa del sistema