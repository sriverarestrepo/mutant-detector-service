# MUTANT DETECTOR SERVICE
### MELI CHALLENGE

_El reto se trata de construir una API-REST en donde se cuente con dos servicios principalmente, uno llamado mutant que nos permitira mediante un arreglo de carácteres determinar si basado en sus condiciones de ADN es o no un mutante, por otro lado está el servicio de estadísticas llamado stats el cual nos proporcionará la cantidad de mutantes y humanos evaluados y su correspondiente ratio._

--------
--------

# Herramientas y stack tecnológico utilizado.


 * [Git]
 * [GitHub] - Uso de Gitflow.
 * [GitKraken] - Cliente Git
 * [Java 8] 
 * [Maven 3]
 * [Spring Boot]
 * [IDE STS 4] - Spring tools Suite.
 * [MongoBD]
 * [Heroku] - Servidor en la nube.
 * [MongoBD]
 * [JUnit 5] 
 * [Mockito]
 * [JaCoCo] - Visualizar el reporte de cobertura de código.
 * [Docker]
 * [Swagger] - Documentación de la API.

--------
# Desarrollo técnico y estructura de componentes.

Se desarrolla con orientacion principalmente a los servicios y de forma independiente uno del otro.

Se cuenta con los siguientes componentes:

 - Config.
 - Controllers.
 - Manejo de excepciones.
 - Model
 - Service
 - Manager
 - Repository
 - Validation
  
--------
# Documentación API.

_Se realiza la correpondiente documentación de la API, apoyandonos en la implementación Swagger_

#### URL's

[Documentación API en Swagger](https://mutant-detector-service.herokuapp.com/swagger-ui.html#/)
: ( https://mutant-detector-service.herokuapp.com/swagger-ui.html#/ )

![Visualización general de la documentación](https://github.com/sriverarestrepo/mutant-detector-service/blob/master/documentation/api_documentation.png)



[Servicio para detectar mutantes](https://mutant-detector-service.herokuapp.com/mutant)
: ( https://mutant-detector-service.herokuapp.com/mutant )


![Visualización general de la documentación]https://github.com/sriverarestrepo/mutant-detector-service/blob/master/documentation/api_documentation_mutant.png)



[Servicio para obtener las estadísticas](https://mutant-detector-service.herokuapp.com/stats)
: ( https://mutant-detector-service.herokuapp.com/stats )


![Visualización general de la documentación]https://github.com/sriverarestrepo/mutant-detector-service/blob/master/documentation/api_documentation_stats.png)


--------
# Ejemplos API.


#### _Servicio: /mutant_
1) Request: [TYPE POST; HEADER Content-Type: application/json]

   {"dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
		
2) Response: 200 - OK


#### _Servicio: /mutant_
1) Request: [TYPE GET]

   {"count_mutant_dna": 2, "count_human_dna": 2,"ratio": 1}
		
2) Response: 200 - OK


--------
# Code Coverage.

![Code coverage]https://github.com/sriverarestrepo/mutant-detector-service/blob/master/documentation/code_coverage.png)

--------