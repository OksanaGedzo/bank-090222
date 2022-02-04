-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-01-28 07:52:01.454

-- tables
-- Table: bank_account
CREATE TABLE bank_account
(
    id             serial      NOT NULL,
    customer_id    int         NOT NULL,
    account_number varchar(11) NOT NULL,
    balance        int         NOT NULL,
    locked         boolean     NOT NULL,
    CONSTRAINT bank_account_pk PRIMARY KEY (id)
);

-- Table: bank_transaction
CREATE TABLE bank_transaction
(
    id                      serial    NOT NULL,
    bank_account_id         int       NOT NULL,
    sender_account_number   varchar(11) NULL,
    receiver_account_number varchar(11) NULL,
    amount                  int       NOT NULL,
    balance                 int       NOT NULL,
    type                    char(1)   NOT NULL,
    transaction_date_time   timestamp NOT NULL,
    CONSTRAINT bank_transaction_pk PRIMARY KEY (id)
);

-- Table: customer
CREATE TABLE customer
(
    id                           serial      NOT NULL,
    first_name                   varchar(50) NOT NULL,
    last_name                    varchar(50) NOT NULL,
    personal_identification_code varchar(11) NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: bank_account_customer (table: bank_account)
ALTER TABLE bank_account
    ADD CONSTRAINT bank_account_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: bank_transaction_bank_account (table: bank_transaction)
ALTER TABLE bank_transaction
    ADD CONSTRAINT bank_transaction_bank_account
        FOREIGN KEY (bank_account_id)
            REFERENCES bank_account (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- End of file.

