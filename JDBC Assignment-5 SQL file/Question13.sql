create database question13;

use question13;

-- Create the table if it does not exist
CREATE TABLE IF NOT EXISTS sample_table (
    id INT PRIMARY KEY,
    name VARCHAR(50)
);

-- Insert data into the table
INSERT INTO sample_table (id, name) VALUES (1, 'Alice');
INSERT INTO sample_table (id, name) VALUES (2, 'Aniket');
INSERT INTO sample_table (id, name) VALUES (3, 'Varsha');
INSERT INTO sample_table (id, name) VALUES (4, 'Omkar');

-- Select all data from the table
SELECT * FROM sample_table;
