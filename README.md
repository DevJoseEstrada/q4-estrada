# GitHub Repository README

This repository contains the source code for a web application developed with Angular for the front-end and Spring Boot for the back-end. The application is deployed using AWS Elastic Beanstalk, utilizing services like RDS for the database and S3 for storage. The project includes some Unit test and instructions for offline setup.

## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Backend Configuration](#backend-configuration)
- [Frontend Configuration](#frontend-configuration)
- [Additional Notes](#additional-notes)
- [Links](#links)

## Introduction
This project is a web application built with Angular for the front-end and Spring Boot for the back-end. It allows users to interact with data stored in a PostgreSQL database. The application is deployed using AWS Elastic Beanstalk, with RDS for the database and S3 for storage. Additionally, you can set up the database using the provided DDL script and test the APIs using the AWS-hosted Swagger documentation.

## Prerequisites
Before getting started, ensure you have the following prerequisites installed:
- Node.js and npm for Angular development
- Java Development Kit (JDK) for Spring Boot
- PostgreSQL database for local development

## Database Setup
To set up the database with initial data, follow these steps:
1. Create a PostgreSQL database instance.
2. Execute the provided DDL script (`DDL_Script.sql`) to create the necessary tables, schema and data.

## Backend Configuration
In the back-end project, update the `application.properties` file with your database configuration:
```properties
spring.datasource.url=jdbc:postgresql://HOSTNAME:PORT/DB_NAME
spring.datasource.username=userDB
spring.datasource.password=passDB
```

## Frontend Configuration
In the front-end project, modify the environment configuration to point to your local API:
1. Open `FrontEnd/src/environments/environment.ts`.
2. Set the `apiUrl` property to the URL of your local API.


## Additional Notes
- The Angular application's UI is implemented using pure HTML, without utilizing frameworks such as Angular Material, PrimeNG, Tailwind CSS, or Bootstrap.
- Given more time and resources, improvements would include a polished and responsive user interface, enhanced error handling, and security implementations like JWT.


## Links
- [AWS API Swagger](http://denuvoappdevestrada.eu-west-1.elasticbeanstalk.com/)
- [AWS Angular App](http://elasticbeanstalk-eu-west-1-012598363932.s3-website-eu-west-1.amazonaws.com/)
