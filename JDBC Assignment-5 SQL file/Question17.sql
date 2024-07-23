create database Question17;

use Question17;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100),
    email VARCHAR(100)
);

INSERT INTO users (username, name, email) VALUES ('admin', 'Administrator', 'admin@example.com');
INSERT INTO users (username, name, email) VALUES ('johndoe', 'John Doe', 'johndoe@example.com');
INSERT INTO users (username, name, email) VALUES ('janedoe', 'Jane Doe', 'janedoe@example.com');

select *from users;