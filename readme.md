Spring Boot Java 19 application that exposes a REST api consisting of one endpoint:

* GET /api/prices (gets the price based on application date, product id and brand id)

Run the application by running BCNCTestApplication.java, no parameters required.

A Open API codegen generated API will be exposed at localhost:8080/api/ and can be accessed through SwaggerUI on 
http://localhost:8080/api/swagger-ui/index.html

The data is stored on a H2 database accesible from: http://localhost:8080/api/h2-console/ using:
* user: sa
* password: password
* url: jdbc:h2:mem:localdb
