-- Create the database if it does not exist
CREATE DATABASE Question5;

-- Use the newly created database
USE Question5;

-- Create a simple table called 'test_table'
CREATE TABLE test_table (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Insert some sample data into the 'test_table'
INSERT INTO test_table (id, name) VALUES (1, 'Alice');
INSERT INTO test_table (id, name) VALUES (2, 'Bob');
INSERT INTO test_table (id, name) VALUES (3, 'Charlie');

select * from test_table;