CREATE DATABASE IF NOT EXISTS talentpool;
USE talentpool;

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rollNo VARCHAR(20) NOT NULL,
    branch VARCHAR(50),
    year INT,
    cgpa DOUBLE,
    skills VARCHAR(255)
);
