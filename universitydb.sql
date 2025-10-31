CREATE DATABASE university;

USE university;

CREATE TABLE student_marks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    marks INT
);

INSERT INTO student_marks (name, marks) VALUES
('Anitha', 85),
('Rahul', 70),
('Karthik', 90),
('Divya', 78);
