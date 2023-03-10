# AUTOMATIZACI脫N DE PRUEBAS A SERVICIOS WEB - RESTFUL - BOOKER

### 馃搫 Contexto:
RestFul Booker, es una API que puede utilizar para obtener m谩s informaci贸n sobre API Testing o probar
herramientas de API Testing. RestFul-Booker contiene end-points que permiten Crear, Leer, Actualizar y
Eliminar reservas. El API REST viene con funciones de autenticaci贸n y errores para que pueda explorar.
La API viene precargada con 10 registros para que pueda trabajar y se restablece cada 10 minutos a ese
estado predeterminado.

### 馃毄 Problem谩tica:
Actualmente en la aplicaci贸n de reservas RESTFUL - BOOKER se est谩n detectando diferentes errores
funcionales a nivel de la GUI, pero al detectarlos en este nivel no se puede dar feedback temprano a los
desarrolladores. Por lo que se requiere que se automaticen pruebas a nivel de integraci贸n, por eso se
requiere implementar pruebas automatizadas a los servicios web.

### 馃挕 Soluci贸n:
Para detectar y mitigar de manera temprana los diferentes errores funcionales a nivel de la GUI de la aplicaci贸n,
se plantean unos sets de pruebas automatizadas a nivel de integraci贸n de los servicios web que all铆 se consumen, con el fin de
que se pueda probar la API de forma regular, con mayor rapidez y que as铆 se permita identificar la mayor铆a de los errores
en la fase de desarrollo

***
# 鉁? PLAN DE PRUEBAS FUNCIONAL
(NIVEL API TESTING)

### RESUMEN
Este plan de pruebas muestra el detalle y organizaci贸n de las pruebas de integraci贸n de la feature:

> **FEATURE TEST PLAN**: Restful-Booker | Booking | Crear, modificar, actualizar y eliminar reservas del sistema.
>
>*Como usuario de booking logueado,  
Quiero crear, consultar, actualizar y eliminar reservas  
Para agendar mis pr贸ximos viajes*

* Este Plan se llevar谩 a cabo a nivel de **Pruebas de integraci贸n**.
* Este Plan se llevar谩 a cabo en **Postman** de manera automatizada por medio de una collection.
* Este Plan se llevar谩 a cabo en el framework **Serenity BDD** y **RestAssured** de manera automatizada.
* Este Plan se realizar谩 para determinar **qu茅 pruebas se har谩n** y **c贸mo se realizar谩n las pruebas.**

### OBJETIVO
Validar las funcionalidades b谩sicas que puede realizar un usuario con una reserva (Auth, Booking y Ping), usando Serenity BDD en integraci贸n con Rest Assured para automatizar el proceso.

### SUPUESTOS
* La api se encuentra siempre en funcionamiento.
* Acceso a la p谩gina web donde est谩 la documentaci贸n de la API.
* Herramientas de pruebas como IDE (Intellij Idea, Visual Studio Code, etc) y Postman correctamente instalados y configurados.
* Conocimientos en API Testing.

### ALCANCE
* Capacidad de alcanzar las funcionalidades de Auth, Booking y Ping.
  * No obstante, se podr谩 realizar pruebas adicionales en caso de tener ventaja para validar m谩s funciones.
* Seguir las reglas de negocio sobre la creaci贸n, consulta, actualizaci贸n y eliminaci贸n de las reservas.
* Contemplar m铆nimo un **_HAPPY PATH_** y **_UNHAPPY PATH_** por m茅todo HTTP (GET,POST,PUT,DELETE).
* No se validar谩n los escenarios que no fueron contemplados en los flujos a probar.

### RIESGOS
| Riesgo                                                  | Mitigaci贸n                                                                                                  |
|:--------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| No conocer el funcionamiento o arquitectura de la API   | Ir a la documentaci贸n oficial de [RESTFUL - BOOKER](https://restful-booker.herokuapp.com/apidoc/index.html) |
| No se puede generar el TOKEN de autenticaci贸n de la API | Utilizar el mock de Authorization descrito en la documentaci贸n                                              |
| QA Environment est谩 ca铆do                               | Usar el recurso **/ping** para validar cuando la api est茅 UP.                                                   |
***
### RECURSOS
* **Postman**: como herramienta de apoyo para hacer pruebas de los recursos de la API.
* **Serenity BDD en integraci贸n con Rest Assured**: Frameworks principales para realizar la automatizaci贸n de los scripts de pruebas.
* **Plan de pruebas**: Documento para la planificaci贸n de las pruebas.
* **Documentaci贸n API**: P谩gina web de [RESTFUL - BOOKER](https://restful-booker.herokuapp.com/apidoc/index.html)

### CONFIGURACI脫N DEL AMBIENTE
* Las pruebas ser谩n ejecutadas en el ambiente de **QA**.
* Las pruebas ser谩n ejecutadas con la API de **RESTFUL - BOOKER**.
* Las pruebas ser谩n ejecutas en la versi贸n **v1.0.0** de **RESTFUL - BOOKER**.
* El endpoint de pruebas ser谩: `https://restful-booker.herokuapp.com`

#### 馃敆 API Endpoints
| HTTP Verbs | Endpoints    | Acci贸n                                                                                           |
|------------|--------------|--------------------------------------------------------------------------------------------------|
| POST       | /auth        | Crea un nuevo token de autenticaci贸n para acceder a PUT y DELETE                                 |
| GET        | /booking     | Devuelve los id de todas las reservas que existen dentro de la API.                              |
| GET        | /booking/:id | Devuelve una reserva espec铆fica basada en la identificaci贸n de la reserva proporcionada          |
| POST       | /booking     | Crea una nueva reserva en la API                                                                 |
| GET        | /booking/:id | Actualiza una reserva actual                                                                     |
| PATCH      | /booking/:id | Actualiza una reserva actual con una carga 煤til parcial                                          |
| DELETE     | /booking/:id | Elimina una reserva actual en la API                                                             |
| GET        | /ping        | Un punto final de verificaci贸n de estado simple para confirmar si la API est谩 en funcionamiento. |
***
### NIVELES Y TIPOS DE PRUEBA
**Niveles de pruebas:**
* Pruebas de integraci贸n

**Tipos de pruebas:**
* Pruebas funcionales: En este tipo de pruebas vamos a validar las funcionalidades de la API (Ej: c贸digos de estado)
* Pruebas de integraci贸n: Para validar la integraci贸n de todos los recursos de la API.

### CONSIDERACIONES DE LAS PRUEBAS
- [ ] Crear una colecci贸n en postman que permita realizar pruebas manuales a los servicios web.
- [ ] Automatizar las funcionalidades de: Auth, Booking y Ping.
- [ ] Considerar en la automatizaci贸n de pruebas los m茅todos http: POST, GET, PUT, PATCH,
  DELETE.
- [ ] Considerar la creaci贸n de escenarios Happy Paths y UnHappy Paths.
- [ ] Considerar en la automatizaci贸n todas las aserciones posibles.


### FLUJOS A PROBAR (dentro de alcance)

Test dise帽ados para validar los escenarios **Happy Paths** 馃槂
***
> **鉁? FEATURE**: Booking | Confirmar funcionamiento de la api
> - [x] [YP-001] Confirmar si la api est谩 en funcionamiento
>
> **鉁? FEATURE**: Booking | Generar token de autenticaci贸n
> - [x] [YP-002] Validar la creaci贸n de token de autenticaci贸n
>
> **鉁? FEATURE**: Booking | Crear una reserva
> - [x] [YP-003] Validar la creaci贸n de una nueva reserva
>
> **鉁? FEATURE**: Booking | Consultar una reserva
> - [x] [YP-004] Validar que se pueda consultar una reserva por su BookingId
> - [x] [YP-005] Validar que se puedan consultar todas las reservas existentes
> - [x] [YP-006] Validar que se pueda consultar una reserva por los nombres del cliente
> - [x] [YP-007] Validar que se pueda consultar una reserva por un rango de fechas espec铆ficas
> - [x] [YP-008] Ver todos los ID's de las reservas consultando por fechas espec铆ficas
>
> **鉁? FEATURE**: Booking | Actualizar una reserva
> - [x] [YP-009] Validar la actualizaci贸n de una reserva actual
> - [x] [YP-010] Validar la actualizaci贸n parcial una reserva actual
>
> **鉁? FEATURE**: Booking | Eliminar una reserva
> - [x] [YP-011] Validar la eliminaci贸n de una reserva actual
***
Test dise帽ados para validar los escenarios **UNHappy Paths** 馃ぁ

> **鉁? FEATURE**: UnHappy Paths | Validar que no se puedan crear/consultar/actualizar/eliminar reservas
> - [x] [YP-013] Validar que no se pueda crear un reserva al no enviar un campo obligatorio
> - [x] [YP-014] Validar que no se pueda consultar una reserva eliminada
> - [x] [YP-015] Validar que no se pueda actualizar una reserva eliminada
> - [x] [YP-016] Validar que no se pueda eliminar una reserva sin estar autenticado
***

### CRITERIOS DE SALIDA
> 鉁? Las pruebas han terminado y no hay defectos funcionales  
> 鉁? Todos los defectos restantes tienen una severidad baja

### ENTEGRABLES DE PRUEBAS
> 鈽戯笍 Colecci贸n en postman, la ruta de su ubicaci贸n es: `resources/TestDeliverables/restful-booker-collection.postman_collection.json`  
> 鈽戯笍 El plan de pruebas actual, la ruta de su ubicaci贸n es: `resources/TestDeliverables/PLAN_DE_PRUEBAS.markdown`  
> 鈽戯笍 El informe del resultado de pruebas, la ruta de su ubicaci贸n es: `target/site/serenity/serenity-summary.html`   
> 鈽戯笍 El repositorio en GitHub con los scripts desarrollados: [api-automation-solution-reto-qa](https://github.com/CAMIDIMAS18/api-automation-solution-reto-qa)
***

### 鉂? INFORMACI脫N ADICIONAL
>*Toda la informaci贸n relacionada a la estructura del proyecto se especifica en el archivo README.md*
***