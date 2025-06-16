**💳 ATM Management System – Java Spring Boot REST API**
📸 Preview
![image](https://github.com/user-attachments/assets/4e93c7a8-266f-487f-812d-4dd8f5631d25)

![image](https://github.com/user-attachments/assets/9279603d-6881-4bb6-8846-e9eb2a20c101)

📋 Overview
This project simulates an ATM machine using a Java Spring Boot REST API. It allows users to interact with a virtual bank system by performing various financial operations and maintaining a transaction history in a database.

✅ Core Functionalities
👤 User Operations
* Register a user

* Deposit money

* Withdraw money

* Transfer funds to another user

* Check account balance

* Get account details

* View bank statement (transactions) for a certain date

* Close/delete bank account

🏦 Bank Admin Operations
* List all bank users

* List all transactions

* List all accounts

* View total bank balance

Show user with:

* Most transactions

* Highest balance

* Filter transactions between two dates

* Find the date with the most transactions

⚠️ Important Notes
Register users before creating accounts or performing transactions to ensure smooth functionality.

▶️ Running the Application
🛠 Build
bash
Copy
Edit
./gradlew build
🚀 Run
In IDE: Run AtmApplication.java as a Java Application

From CLI:

bash
Copy
Edit
./gradlew bootRun
📑 API Documentation
Accessible after running the app at:

bash
Copy
Edit
http://localhost:8080/swagger-ui.html
Powered by Springdoc OpenAPI (Swagger UI)

🧰 Technology Stack
Category	Tools / Frameworks
* Language	Java 17
* Framework	Spring Boot 3.0.1
* ORM	Hibernate, Spring Data JPA
* Testing	JUnit 5, Mockito
* Documentation	Swagger (springdoc-openapi)
* Database	Oracle
* Build Tool	Maven
* API Testing	Postman
* IDE	Spring Tool Suite (STS)
* Code Generation	Lombok
