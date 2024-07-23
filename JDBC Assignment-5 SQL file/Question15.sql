-- Create a database if it doesn't exist
CREATE DATABASE IF NOT EXISTS Question15;

-- Switch to the newly created database
USE Question15;

-- Drop the table if it already exists
DROP TABLE IF EXISTS large_objects;

-- Create table with BLOB and CLOB columns
CREATE TABLE large_objects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    blob_data BLOB,
    text_data TEXT
);

-- Insert sample data into the table
INSERT INTO large_objects (name, blob_data, text_data) VALUES
('Sample Data 1', 'This is a binary data', 'This is a large text data for TEXT'),
('Sample Data 2', 'Another binary data', 'Another large text data for TEXT');