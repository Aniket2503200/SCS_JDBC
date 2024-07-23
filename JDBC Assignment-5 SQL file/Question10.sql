-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS Question10;

-- Use the newly created database
USE Question10;

-- Create a simple table called 'test_table'
CREATE TABLE IF NOT EXISTS test_table (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Insert some sample data into the 'test_table'
INSERT INTO test_table (id, name) VALUES (1, 'Aniket');
INSERT INTO test_table (id, name) VALUES (2, 'Varsha');
INSERT INTO test_table (id, name) VALUES (3, 'Charlie');

select *from test_table;