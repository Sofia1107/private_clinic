CREATE TABLE clients
(
    id            int          NOT NULL AUTO_INCREMENT,
    first_name    varchar(255) NOT NULL,
    last_name     varchar(255) NOT NULL,
    email         varchar(100) UNIQUE,
    phone_number  varchar(50)  NOT NULL UNIQUE,
    pwd           varchar(100) NOT NULL,
    date_of_birth DATE         NOT NULL,
    blood_group   CHAR         NOT NULL,
    RH            CHAR         NOT NULL,
    allergy       CHAR         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE doctors
(
    id             int          NOT NULL AUTO_INCREMENT,
    first_name     varchar(255) NOT NULL,
    last_name      varchar(255) NOT NULL,
    email          varchar(100) UNIQUE,
    phone_number   varchar(50)  NOT NULL UNIQUE,
    pwd            varchar(100) NOT NULL,
    specialization varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE admins
(
    id           int          NOT NULL AUTO_INCREMENT,
    first_name   varchar(255) NOT NULL,
    last_name    varchar(255) NOT NULL,
    email        varchar(100) UNIQUE,
    phone_number varchar(50)  NOT NULL UNIQUE,
    pwd          varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE appointments
(
    id        int      NOT NULL AUTO_INCREMENT,
    client_id int      NOT NULL,
    doctor_id int      NOT NULL,
    date_time DATETIME NOT NULL,
    summary   varchar(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);
