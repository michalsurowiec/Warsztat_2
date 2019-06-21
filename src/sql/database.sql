CREATE TABLE user_group
(id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
PRIMARY KEY(id))

CREATE TABLE users
(id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(255),
email VARCHAR(255) UNIQUE,
password VARCHAR(60),
skills VARCHAR(255),
id_user_group INT,
PRIMARY KEY(id),
FOREIGN KEY(id_user_group) REFERENCES user_group(id))

CREATE TABLE exercise
(id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(255),
description TEXT,
PRIMARY KEY(id))

CREATE TABLE solution
(id INT NOT NULL AUTO_INCREMENT,
created DATETIME,
updated DATETIME,
description TEXT,
rate DECIMAL(2,1),
commentary VARCHAR(255),
id_exercise INT,
id_users INT,
PRIMARY KEY(id),
FOREIGN KEY (id_exercise) REFERENCES exercise(id),
FOREIGN KEY (id_users) REFERENCES users(id))