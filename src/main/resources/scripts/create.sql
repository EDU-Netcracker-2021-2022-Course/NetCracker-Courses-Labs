-- DROP TABLE IF EXISTS PUBLIC.CONTRACT CASCADE;
-- DROP TABLE IF EXISTS PUBLIC.PERSON CASCADE;

CREATE TABLE IF NOT EXISTS PUBLIC.PERSON
(
    id              VARCHAR PRIMARY KEY,
    first_name      VARCHAR,
    middle_name     VARCHAR,
    last_name       VARCHAR,
    birth_date      VARCHAR,
    passport_series INT,
    passport_number INT,
    age             INT,
    sex             VARCHAR
);

CREATE TABLE IF NOT EXISTS PUBLIC.CONTRACT
(
    id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    site VARCHAR
);