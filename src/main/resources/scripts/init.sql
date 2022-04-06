CREATE TABLE IF NOT EXISTS CONTRACT
(
    id              varchar(255) PRIMARY KEY NOT NULL,
    starting_date   VARCHAR(255),
    ending_date     VARCHAR(255),
    contract_number INT,
    contract_owner  INT,
    FOREIGN KEY (contract_owner) REFERENCES person,
    contract_type   VARCHAR(255),
    additional_info VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS PERSON
(
    id              VARCHAR(255) PRIMARY KEY,
    first_name      VARCHAR(255),
    middle_name     VARCHAR(255),
    last_name       VARCHAR(255),
    birth_date      VARCHAR(255),
    passport_series INT,
    passport_number INT,
    age             INT,
    sex             VARCHAR(255)
);