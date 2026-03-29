# Secure Transaction Management System 

## Description

Secure Transaction Management System is a banking application built with Java Servlets that enables secure financial transactions between users and their accounts. It maintains data integrity, prevents unauthorized access, and keeps precise records of all transactions. Users can transfer funds, check transaction history, and manage account information safely.

The system relies on MySQL for database storage and is deployed as a WAR file on an Apache Tomcat server, showcasing essential Java web development skills such as Servlets, JDBC integration, authentication, and secure transaction handling.

---

## Features

* Secure user authentication with role-based access control
* Create, update, and manage customer accounts
* Perform secure fund transfers between accounts
* Transaction validation to prevent unauthorized transfers
* View transaction history per account
* Reusable JDBC database connection utility with proper exception handling
* Password hashing for secure user authentication
* Automated logging of all transactions

---

## Technologies Used

* Java (Servlets)
* Servlet Filters
* JDBC
* MySQL
* Apache Tomcat
* Maven
* SHA-256 Password Hashing
* HikariCP Connection Pooling
* SonarQube
* Postman

---

## Project Structure
 
* **controller**      – Handle HTTP requests and responses
* **dao**             – Database operations using JDBC
* **dbconfiguration** – SQL scripts / schema
* **filter**          – Handles authentication and request validation
* **model**           – Entity classes
* **service**         – Business logic (transaction validation, account management)
* **util**            – Helper classes for hashing, DB connection

---

## Functional Modules

### User Authentication

* User registration and login
* Password hashing (SHA)
* Input validation
* Role-based access (admin/user)

---

### Customer

* Create customer profile
* View all customers
* Link customer accounts to authenticated users

---

### Account

* Create and manage bank accounts
* View account details and balance
* Prevent invalid transactions

---

### Transaction

* Initiate fund transfer between accounts
* Validate transaction amount and account status
* Store transaction history per account
* Retrieve transaction logs by account or user

---

### Automated Logging

* Record all transactions with timestamp, amount, sender, and receiver details
* Maintain audit trail for secure financial operations

---

## Business Rules

* Each user must have a registered account to perform transactions  
* Transactions can only be initiated by logged-in users  
* Transfers must be between existing accounts in the system  
* Transaction amount must be positive  
* All transactions are recorded with timestamp for tracking  

---

## API Endpoints

### Authentication APIs

```
POST /register
POST /login
```

### Account APIs

```
POST /account
GET /account
```

### Transaction APIs

```
POST /transaction
GET /transaction
```

---

## Sample Requests

### Register User

```json
{
  "adminId": 1
  "username": "saranya",
  "password": "Password@123"
}
```

### Create Customer

```json
{
  "customerId": 1,
  "username": "customer01",
  "password": "Password@123",
  "fullName": "Saranya",
  "phone": "9876543210",
  "email": "saranya@example.com"
}
```

### Create Account

```json
{
  "customerId": 1,
  "accountNumber": "ACC2001",
  "balance": 5000,
  "accountType": "Savings"
}
```

### Transfer Request

```json
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 1000
}
```

---

## Transaction Logic

1. User sends a transfer request after logging in
2. System authenticates the user credentials and retrieves user identity
3. Retrieve sender and receiver account details from the database
4. Validate that both accounts exist and are eligible for transfer
5. Ensure the sender’s account has sufficient balance for the requested amount
6. Begin database transaction (atomic update)
7. Deduct the transfer amount from the sender’s account
8. Add the transfer amount to the receiver’s account
9. Insert a transaction record into the transactions table with timestamp and status
10. Commit the database changes if all operations succeed
11. Return success or error response to the user

---

## Database Design

Tables:

- **Admin** – Represents bank employees who manage customer accounts and monitor transactions  
- **Customer** – bank clients who own accounts  
- **Account** – Holds customer funds and is linked to transactions  
- **Transaction** – logs all fund transfers between accounts  

---

## Security Implementation

* Password hashing using SHA-256 for secure storage  
* Role-based access control for Admin (bank employee) and Customer  
* Server-side validation of user credentials for authentication  
* Input validation to prevent invalid or malicious data  
* All sensitive operations restricted to authorized users only

---

## Database Connection

* Uses HikariCP connection pooling for:

  * High performance
  * Connection reuse
  * Efficient resource management

---

## Deployment

1. Clone the repository
2. Setup MySQL database and import schema
3. Update database credentials in `application.properties`
4. Build WAR file using Maven
5. Deploy WAR in Apache Tomcat webapps folder
6. Start Tomcat server and access endpoints

---

## Testing

* API testing with Postman  
* Code quality analysis with SonarQube  
* Manual testing of key functionalities: registration, account management, and fund transfers
---

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/be991728-214c-4997-8ee0-87c2e03f901a" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/ad9c4592-abeb-4aa4-8b6a-c68c47bb3edc" />


