# AUTOMATIZACIÓN DE PRUEBAS A SERVICIOS WEB - RESTFUL - BOOKER

### 📄 Contexto:
RestFul Booker, es una API que puede utilizar para obtener más información sobre API Testing o probar
herramientas de API Testing. RestFul-Booker contiene end-points que permiten Crear, Leer, Actualizar y
Eliminar reservas. El API REST viene con funciones de autenticación y errores para que pueda explorar.
La API viene precargada con 10 registros para que pueda trabajar y se restablece cada 10 minutos a ese
estado predeterminado.

### 🚩 Problemática:
Actualmente en la aplicación de reservas RESTFUL - BOOKER se están detectando diferentes errores
funcionales a nivel de la GUI, pero al detectarlos en este nivel no se puede dar feedback temprano a los
desarrolladores. Por lo que se requiere que se automaticen pruebas a nivel de integración, por eso se
requiere implementar pruebas automatizadas a los servicios web.

### 💡 Solución:
Para detectar y mitigar de manera temprana los diferentes errores funcionales a nivel de la GUI de la aplicación,
se plantean unos sets de pruebas automatizadas a nivel de integración de los servicios web que allí se consumen, con el fin de
que se pueda probar la API de forma regular, con mayor rapidez y que así se permita identificar la mayoría de los errores
en la fase de desarrollo

***
# ✅ PLAN DE PRUEBAS FUNCIONAL
(NIVEL API TESTING)

### RESUMEN
Este plan de pruebas muestra el detalle y organización de las pruebas de integración de la feature:

> **FEATURE TEST PLAN**: Restful-Booker | Booking | Crear, modificar, actualizar y eliminar reservas del sistema.
>
>*Como usuario de booking logueado,  
Quiero crear, consultar, actualizar y eliminar reservas  
Para agendar mis próximos viajes*

* Este Plan se llevará a cabo a nivel de **Pruebas de integración**.
* Este Plan se llevará a cabo en **Postman** de manera automatizada por medio de una collection.
* Este Plan se llevará a cabo en el framework **Serenity BDD** y **RestAssured** de manera automatizada.
* Este Plan se realizará para determinar **qué pruebas se harán** y **cómo se realizarán las pruebas.**

### OBJETIVO
Validar las funcionalidades básicas que puede realizar un usuario con una reserva (Auth, Booking y Ping), usando Serenity BDD en integración con Rest Assured para automatizar el proceso.

### SUPUESTOS
* La api se encuentra siempre en funcionamiento.
* Acceso a la página web donde está la documentación de la API.
* Herramientas de pruebas como IDE (Intellij Idea, Visual Studio Code, etc) y Postman correctamente instalados y configurados.
* Conocimientos en API Testing.

### ALCANCE
* Capacidad de alcanzar las funcionalidades de Auth, Booking y Ping.
  * No obstante, se podrá realizar pruebas adicionales en caso de tener ventaja para validar más funciones.
* Seguir las reglas de negocio sobre la creación, consulta, actualización y eliminación de las reservas.
* Contemplar mínimo un **_HAPPY PATH_** y **_UNHAPPY PATH_** por método HTTP (GET,POST,PUT,DELETE).
* No se validarán los escenarios que no fueron contemplados en los flujos a probar.

### RIESGOS
| Riesgo                                                  | Mitigación                                                                                                  |
|:--------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| No conocer el funcionamiento o arquitectura de la API   | Ir a la documentación oficial de [RESTFUL - BOOKER](https://restful-booker.herokuapp.com/apidoc/index.html) |
| No se puede generar el TOKEN de autenticación de la API | Utilizar el mock de Authorization descrito en la documentación                                              |
| QA Environment está caído                               | Usar el recurso **/ping** para validar cuando la api esté UP.                                                   |
***
### RECURSOS
* **Postman**: como herramienta de apoyo para hacer pruebas de los recursos de la API.
* **Serenity BDD en integración con Rest Assured**: Frameworks principales para realizar la automatización de los scripts de pruebas.
* **Plan de pruebas**: Documento para la planificación de las pruebas.
* **Documentación API**: Página web de [RESTFUL - BOOKER](https://restful-booker.herokuapp.com/apidoc/index.html)

### CONFIGURACIÓN DEL AMBIENTE
* Las pruebas serán ejecutadas en el ambiente de **QA**.
* Las pruebas serán ejecutadas con la API de **RESTFUL - BOOKER**.
* Las pruebas serán ejecutas en la versión **v1.0.0** de **RESTFUL - BOOKER**.
* El endpoint de pruebas será: `https://restful-booker.herokuapp.com`

#### 🔗 API Endpoints
| HTTP Verbs | Endpoints    | Acción                                                                                           |
|------------|--------------|--------------------------------------------------------------------------------------------------|
| POST       | /auth        | Crea un nuevo token de autenticación para acceder a PUT y DELETE                                 |
| GET        | /booking     | Devuelve los id de todas las reservas que existen dentro de la API.                              |
| GET        | /booking/:id | Devuelve una reserva específica basada en la identificación de la reserva proporcionada          |
| POST       | /booking     | Crea una nueva reserva en la API                                                                 |
| GET        | /booking/:id | Actualiza una reserva actual                                                                     |
| PATCH      | /booking/:id | Actualiza una reserva actual con una carga útil parcial                                          |
| DELETE     | /booking/:id | Elimina una reserva actual en la API                                                             |
| GET        | /ping        | Un punto final de verificación de estado simple para confirmar si la API está en funcionamiento. |
***
### NIVELES Y TIPOS DE PRUEBA
**Niveles de pruebas:**
* Pruebas de integración

**Tipos de pruebas:**
* Pruebas funcionales: En este tipo de pruebas vamos a validar las funcionalidades de la API (Ej: códigos de estado)
* Pruebas de integración: Para validar la integración de todos los recursos de la API.

### CONSIDERACIONES DE LAS PRUEBAS
- [ ] Crear una colección en postman que permita realizar pruebas manuales a los servicios web.
- [ ] Automatizar las funcionalidades de: Auth, Booking y Ping.
- [ ] Considerar en la automatización de pruebas los métodos http: POST, GET, PUT, PATCH,
  DELETE.
- [ ] Considerar la creación de escenarios Happy Paths y UnHappy Paths.
- [ ] Considerar en la automatización todas las aserciones posibles.


### FLUJOS A PROBAR (dentro de alcance)

Test diseñados para validar los escenarios **Happy Paths** 😃
***
> **✨ FEATURE**: Booking | Confirmar funcionamiento de la api
> - [x] [YP-001] Confirmar si la api está en funcionamiento
>
> **✨ FEATURE**: Booking | Generar token de autenticación
> - [x] [YP-002] Validar la creación de token de autenticación
>
> **✨ FEATURE**: Booking | Crear una reserva
> - [x] [YP-003] Validar la creación de una nueva reserva
>
> **✨ FEATURE**: Booking | Consultar una reserva
> - [x] [YP-004] Validar que se pueda consultar una reserva por su BookingId
> - [x] [YP-005] Validar que se puedan consultar todas las reservas existentes
> - [x] [YP-006] Validar que se pueda consultar una reserva por los nombres del cliente
> - [x] [YP-007] Validar que se pueda consultar una reserva por un rango de fechas específicas
> - [x] [YP-008] Ver todos los ID's de las reservas consultando por fechas específicas
>
> **✨ FEATURE**: Booking | Actualizar una reserva
> - [x] [YP-009] Validar la actualización de una reserva actual
> - [x] [YP-010] Validar la actualización parcial una reserva actual
>
> **✨ FEATURE**: Booking | Eliminar una reserva
> - [x] [YP-011] Validar la eliminación de una reserva actual
***
Test diseñados para validar los escenarios **UNHappy Paths** 🤡

> **✨ FEATURE**: UnHappy Paths | Validar que no se puedan crear/consultar/actualizar/eliminar reservas
> - [x] [YP-013] Validar que no se pueda crear un reserva al no enviar un campo obligatorio
> - [x] [YP-014] Validar que no se pueda consultar una reserva eliminada
> - [x] [YP-015] Validar que no se pueda actualizar una reserva eliminada
> - [x] [YP-016] Validar que no se pueda eliminar una reserva sin estar autenticado
***

### CRITERIOS DE SALIDA
> ✅ Las pruebas han terminado y no hay defectos funcionales  
> ✅ Todos los defectos restantes tienen una severidad baja

### ENTEGRABLES DE PRUEBAS
> ☑️ Colección en postman, la ruta de su ubicación es: `resources/TestDeliverables/restful-booker-collection.postman_collection.json`  
> ☑️ El plan de pruebas actual, la ruta de su ubicación es: `resources/TestDeliverables/PLAN_DE_PRUEBAS.markdown`  
> ☑️ El informe del resultado de pruebas, la ruta de su ubicación es: `target/site/serenity/serenity-summary.html`   
> ☑️ El repositorio en GitHub con los scripts desarrollados: [api-automation-solution-reto-qa](https://github.com/CAMIDIMAS18/api-automation-solution-reto-qa)
***

### ❗ INFORMACIÓN ADICIONAL
>*Toda la información relacionada a la estructura del proyecto se especifica en el archivo README.md*
***