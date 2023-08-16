For use ofline is nessary to create the database with data using the script on the "DDL_Script"

BackEnd project required add the next properties:

spring.datasource.url=jdbc:postgresql://HOSTNAME:PORT/DB_NAME
spring.datasource.username= userDB
spring.datasource.password= passDB

FrontEnd project also required to add the url of your API on the:

FrontEnd\src\environments\environment the property "apiUrl:" with the url of the localApi  

Link to the API hosted on AWS for testing using Swagger : http://denuvoappdevestrada.eu-west-1.elasticbeanstalk.com/
Link to the Angular aplication hosted on AWS: http://elasticbeanstalk-eu-west-1-012598363932.s3-website-eu-west-1.amazonaws.com/