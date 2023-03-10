# language: es

@createToken
Necesidad del negocio: Generar token de autenticación
  Como cliente
  Quiero autenticarme en el sistema
  Para poder actualizar y eliminar reservas

  Regla: El token expira cada 10 minutos

    @integrationTest
    Escenario: [YP-002] Validar la creación de token de autenticación
      Dado que la cliente desea crear la reservación de su próximo viaje
      Cuando ella ingrese sus credenciales de acceso
      Entonces se autenticará en el sistema