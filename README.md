# Secure Transaction Management System – Java Servlet & JDBC

## Description

Secure Transaction Management System is a Java Servlet–based banking application designed to handle secure financial transactions between users and accounts. The system ensures data integrity, prevents unauthorized access, and maintains accurate transaction records. Users can perform fund transfers, view transaction history, and manage account details securely.

The application uses MySQL for data storage and is packaged as a WAR file for deployment on an Apache Tomcat server. It demonstrates core Java web development concepts including Servlets, JDBC integration, authentication, and secure transaction processing.

---

## Features

* Secure user authentication with role-based access control
* Create, update, and manage customer accounts
* Perform secure fund transfers between accounts
* Transaction validation to prevent overdrafts or unauthorized transfers
* View transaction history per account
* Reusable JDBC database connection utility with proper exception handling
* Password hashing and JWT token–based authentication for security
* Automated logging of all transactions

---

## Technologies Used

* Java (Servlets)
* JDBC
* MySQL
* Apache Tomcat
* Maven
* JWT Authentication
* BCrypt Password Hashing
* HikariCP Connection Pooling

---

## Project Structure

```
src/
├── controller/       # Servlets handling HTTP requests
├── dao/              # Database operations using JDBC
├── model/            # Entity classes
├── service/          # Business logic (transaction validation, account management)
├── util/             # JWT, password hashing, database connection utility
├── database/         # SQL scripts / schema
```

---

## Functional Modules

### User Authentication

* Register user
* Login user
* Password hashing using BCrypt
* JWT token generation and validation
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
* Prevent invalid transactions (e.g., overdraft)

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

* Minimum account balance: ₹1000
* Transfers allowed only between valid active accounts
* Transaction cannot exceed account balance
* Only authenticated users can initiate transactions
* Transaction logs are immutable for security

---

## API Endpoints

### Authentication APIs

```
POST /register
POST /login
```

### Customer APIs

```
POST /customer
GET /customer
```

### Account APIs

```
POST /account
GET /account
```

### Transaction APIs

```
POST /transaction
GET /transaction/{id}
GET /user/{id}/transactions
```

---

## Sample Requests

### Register User

```json
{
  "username": "saranya",
  "password": "StrongPassword123"
}
```

### Create Customer

```json
{
  "userId": 1,
  "customerName": "Saranya",
  "gender": "Female",
  "phoneNo": "9876543210",
  "email": "saranya@example.com",
  "address": "Tuticorin",
  "aadharNo": "123456789012"
}
```

### Create Account

```json
{
  "customerId": 1,
  "accountNumber": "ACC2001",
  "initialBalance": 5000
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

1. Authenticate the user initiating the transfer
2. Verify sender and receiver accounts exist and are active
3. Check sufficient balance in sender account
4. Deduct amount from sender and add to receiver account atomically
5. Record transaction details with timestamp
6. Respond with success/failure status

---

## Database Design

Tables:

* **users** – Stores login credentials and roles
* **customer** – Stores customer personal details
* **account** – Stores account information, balances, and linked customer IDs
* **transaction** – Logs all transactions with sender, receiver, amount, and timestamps

Relationships:

```
User → Customer → Account → Transaction
```

---

## Security Implementation

* Password hashing using BCrypt
* JWT token authentication for API requests
* Secret key signing with expiration (30 minutes)
* Role-based validation for sensitive operations
* Server-side token verification to prevent unauthorized access

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
3. Update database credentials in `db.properties`
4. Build WAR file using Maven
5. Deploy WAR in Apache Tomcat webapps folder
6. Start Tomcat server and access endpoints

---

## Purpose

This project demonstrates:

* Servlet-based web application architecture
* Secure authentication and role-based access
* JDBC database integration
* Transaction validation and audit logging
* Real-world secure banking transaction management

---

## License

MIT License

---

## Contact

Saranya – [saranya@example.com](mailto:saranya@example.com)
Project link: [https://github.com/saranya-3012/Secure-Transaction-Management-System](https://github.com/saranya-3012/Secure-Transaction-Management-System)
