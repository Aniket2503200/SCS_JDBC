create database Question18;

use Question18;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_name VARCHAR(100) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);

INSERT INTO users (username, name, email) VALUES ('admin', 'Admin User', 'admin@example.com');
INSERT INTO users (username, name, email) VALUES ('jdoe', 'John Doe', 'jdoe@example.com');


INSERT INTO accounts (account_name, balance) VALUES ('Account 1', 1000.00);
INSERT INTO accounts (account_name, balance) VALUES ('Account 2', 1500.00);
