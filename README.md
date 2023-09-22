# SpringSecurityLearner

A sample project to learn spring security

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Endpoint Security](#endpoint-security)
- [API Endpoints](#api-endpoints)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 11 or later
- MySQL Database
- [Spring Boot](https://spring.io/projects/spring-boot) framework
- Maven for building the project

## Getting Started

Follow these steps to set up and run the Spring security Learner Microservice:

1. Clone this repository:

   ```shell
   git clone https://github.com/your-username/healthpro-customer-microservice.git
   cd healthpro-customer-microservice
2. Configure your MySQL database settings in application.properties.
3. Build the microservice:
   ```shell
   mvn clean install

4. Run the microservice:
   ```shell
   java -jar target/healthpro-customer-microservice.jar

## Endpoint Security

V1(deprecated) -- "This currently has very basic security using the default user name("user") and spring generated
password(can be seen in
the application logs)"

V2(current version) -- "This application now uses the username and password configured in applicaiton.properties file"

## API Endpoints

`GET /test/hello`: Test endpoint.

`GET /api/customer/get/{id}`: Retrieve customer information by ID.

`PUT /api/customer/delete/{id}`: Update customer information.

`DELETE /api/customer/update/{id}`: Delete a customer.




