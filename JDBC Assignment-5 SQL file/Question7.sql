-- Create the database if it does not exist
CREATE DATABASE Question7;

-- Use the newly created database
USE Question7;

-- Create a simple table called 'test_table'
CREATE TABLE test_table (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Insert some sample data into the 'test_table'
INSERT INTO test_table (id, name) VALUES (1, 'Aniket');
INSERT INTO test_table (id, name) VALUES (2, 'Varsha');
INSERT INTO test_table (id, name) VALUES (3, 'Yuvraj');
INSERT INTO test_table (id, name) VALUES (4, 'Omkar');
INSERT INTO test_table (id, name) VALUES (5, 'Kunal');
INSERT INTO test_table (id, name) VALUES (6, 'Raghav');
select *from test_table;