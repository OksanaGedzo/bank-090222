-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-02-02 07:39:49.854

-- tables
-- Table: end_customer
CREATE TABLE end_customer
(
    id         serial       NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    address    varchar(255) NOT NULL,
    CONSTRAINT end_customer_pk PRIMARY KEY (id)
);

-- Table: item
CREATE TABLE item
(
    id             serial       NOT NULL,
    name           varchar(255) NOT NULL,
    description    varchar(255) NOT NULL,
    price_per_item int          NOT NULL,
    CONSTRAINT item_pk PRIMARY KEY (id)
);

-- Table: order
CREATE TABLE "order"
(
    id                serial    NOT NULL,
    end_customer_id   int       NOT NULL,
    order_date        timestamp NOT NULL,
    order_total_price int       NOT NULL,
    status            char(1)   NOT NULL,
    CONSTRAINT order_pk PRIMARY KEY (id)
);

-- Table: order_item
CREATE TABLE order_item
(
    id               serial NOT NULL,
    order_id         int    NOT NULL,
    item_id          int    NOT NULL,
    quantity         int    NOT NULL,
    item_total_price int    NOT NULL,
    CONSTRAINT order_item_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: order_end_customer (table: order)
ALTER TABLE "order"
    ADD CONSTRAINT order_end_customer
        FOREIGN KEY (end_customer_id)
            REFERENCES end_customer (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: order_item_item (table: order_item)
ALTER TABLE order_item
    ADD CONSTRAINT order_item_item
        FOREIGN KEY (item_id)
            REFERENCES item (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: order_item_order (table: order_item)
ALTER TABLE order_item
    ADD CONSTRAINT order_item_order
        FOREIGN KEY (order_id)
            REFERENCES "order" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- sequences
-- Sequence: product_seq
CREATE SEQUENCE product_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    START WITH 1
    NO CYCLE
;

-- Sequence: supplier_seq
CREATE SEQUENCE supplier_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    START WITH 1
    NO CYCLE
;

-- End of file.

