CREATE DATABASE  IF NOT EXISTS clinic_db;
USE clinic_db;

DROP TABLE IF EXISTS appointments;
CREATE TABLE appointments
(
    id        int      NOT NULL AUTO_INCREMENT,
    client_id int      NOT NULL,
    doctor_id int      NOT NULL,
    date_time DATETIME NOT NULL,
    summary   varchar(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES users (id),
    FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);

DROP TABLE IF EXISTS doctors;
CREATE TABLE doctors
(
    id             int          NOT NULL AUTO_INCREMENT,
    first_name     varchar(255) NOT NULL,
    last_name      varchar(255) NOT NULL,
    email          varchar(100) UNIQUE,
    phone_number   varchar(50)  NOT NULL UNIQUE,
    specialization varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id            int          NOT NULL AUTO_INCREMENT,
    first_name    varchar(255) NOT NULL,
    last_name     varchar(255) NOT NULL,
    email         varchar(100) UNIQUE,
    phone_number  varchar(50)  NOT NULL UNIQUE,
    pwd           varchar(100) NOT NULL,
    date_of_birth DATE,
    blood_group   int,
    RH            CHAR,
    allergy       varchar(255),
    user_role     varchar(50)  not null,
    PRIMARY KEY (id)
);