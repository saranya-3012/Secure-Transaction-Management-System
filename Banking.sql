create database bluescope;

CREATE TABLE Admin (
    Admin_id SERIAL PRIMARY KEY,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(200) NOT NULL,
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Customer (
    Customer_id SERIAL PRIMARY KEY,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(200) NOT NULL,
    Full_name VARCHAR(100),
	Phone VARCHAR(15),
    Email VARCHAR(100),
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Accounts (
    Account_ID SERIAL PRIMARY KEY,
    Customer_ID INT REFERENCES customer(customer_id) ON DELETE CASCADE,
    Account_Number VARCHAR(20) UNIQUE NOT NULL,
    Balance NUMERIC(12,2) DEFAULT 0,
    Account_type VARCHAR(20),
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Transactions (
    Transaction_ID SERIAL PRIMARY KEY,
    Account_ID INT REFERENCES accounts(account_id) ON DELETE CASCADE,
    Amount NUMERIC(12,2) NOT NULL,
    Type VARCHAR(20),
    Total_Amount NUMERIC(12,2),
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);