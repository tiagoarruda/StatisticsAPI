# StatisticsAPI
Overview:
I've decided to use jax-rs, jersey and CDI to keep the project simple. I tried to use java built in resources intead of using spring and other frameworks.

Architecture:
The project is pretty simple, for that reason I've created layers separeted only by packages.
	* WS - The package contain web service related classes
	* Controller - Controller and business rules
	* Exception - Custom exceptions
	* Transaction - Contain the entity's

Running the project:
I've ran the application in Tomcat v8.5 without outstanding configuration. Just start the container and add the application.

Tests:
Implementend only Unit Tests using Mockito when necessary.
I've tried to build meaninfull tests as much as possible.

TODO's:
	* Dependency injection is no satisfatory, I've implemented the application using static resources because the @Singleton annotated beans were not performing as expected
	* I've intended to control resources accessibility using annotations like @Lock(LockType.READ), but the behavior was not satisfatory and the static solution functioned as a work around
	* Swagger documentation
