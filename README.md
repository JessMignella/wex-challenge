# Wex Transaction App

## Overview
This project is an application to persist and retrieve purchase transactions. It's built using Spring Boot.

## Dependencies
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Boot Starter Validation
- Lombok (Optional)
- Spring Boot Starter Test
- Springdoc OpenAPI Starter WebMvc UI
- Mapstruct
- MySQL Connector
- JUnit Jupiter
- Mockito

## Requirements
- Java 17
- Maven 3.8.x
- MySQL

## Installation and Setup
1. Clone the repository.
2. Set up your MySQL database and configure the `application.yml` file accordingly.
3. Build the project using Maven: `mvn clean install`.
4. Run the application: `java -jar target/transaction-0.0.1-SNAPSHOT.jar`.
5. Access the application at `http://localhost:8080`.

## Usage
- This application provides endpoints for managing purchase transactions.
- Use the provided OpenAPI documentation to explore available endpoints and interact with the application.

## Testing
- Unit tests are implemented using JUnit Jupiter and Mockito.
- To run the tests, use the following command: `mvn test`.
