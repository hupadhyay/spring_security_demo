### Basic Spring Security Example
this project present two ways of authentication and authorization.

- **Inmemory Authentication**: This is very simple example of Spring security authentication and authorization. It uses "inmemoryAuthentication()" 
	method of the AuthenticationManagerBuilder. In this case, you need to provide your username, password, and role in the program itself(hardcoded).
	This example is good to start learning of spring secuirty features. 
	
- **Jdbc Authentication**: This is also a basic example of Spring Security. In this case we are using "datasource" and "jdbcAuthentication()" method 
	method of the AuthenticationManagerBuilder to authenticate and authorize user. In memory database "H2" is used to  provide database to the program.  





