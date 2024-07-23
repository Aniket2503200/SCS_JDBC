-- Create a new database named ExampleDB
CREATE DATABASE Question19;

-- Switch to the ExampleDB database
USE Question19;

-- Create the 'users' table with columns for ID, name, and email
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

-- Optional: Insert a sample record into the 'users' table
INSERT INTO users (name, email) VALUES ('John Doe', 'john.doe@example.com');
select *from users;