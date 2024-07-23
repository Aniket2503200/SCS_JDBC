create database Question14;

use Question14;

-- Create the table
CREATE TABLE your_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    your_column VARCHAR(255) NOT NULL
);

-- Insert sample data
INSERT INTO your_table (your_column) VALUES ('Value 1');
INSERT INTO your_table (your_column) VALUES ('Value 2');
INSERT INTO your_table (your_column) VALUES ('Value 3');
INSERT INTO your_table (your_column) VALUES ('Value 4');
INSERT INTO your_table (your_column) VALUES ('Value 5');

select *from your_table;