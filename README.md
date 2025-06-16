# Atm-Management-System
![image](https://github.com/user-attachments/assets/1fed4b29-9a9e-4219-ad34-8b7c42861db0)
![image](https://github.com/user-attachments/assets/9ead084b-d970-49d9-9df1-580b75b6bc55)

Java Spring Boot Rest API ATM Project
The application simulates the usage of an ATM via REST API calls. It aims to provide the user with bank operations and to keep a record of customer transactions in a database.

Functionality
User

Deposit

Withdraw

Transfer (to another bank user)

Bank account details

Bank statement for a certain date

Close bank account

Bank

Bank users

Transactions

Accounts

Bank balance

Show user with most transactions

Show user with highest balance

Transactions occured between two given dates

Find the date with most transactions

Observation: In order for the application flow to run correctly, register the users first.

Running App
Build the project following the ./gradlew build command.

You can run the application (a REST server) in your IDE by running class AtmApplication as Java Application or on the command line gradle wrapper run.

Documentation
The documentation for each ATM endpoint was done via Swagger springdoc-openapi and it can be found after running the application at the following url http://localhost:8080/swagger-ui.html

Technology
Java 17

Spring Boot (3.0.1)

Hibernate

Spring Data JPA

Lombok

Unit test: Junit 5 + Mockito

Documentation: Swagger springdoc-openapi

Endpoints testing: Postman

Build Tool: Maveen

Database: Oracel

IDE: STS
