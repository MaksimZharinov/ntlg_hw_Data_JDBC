CREATE SCHEMA netology;

CREATE TABLE netology.customers
(
    id           BIGSERIAL PRIMARY KEY,
    name         TEXT NOT NULL,
    surname      TEXT NOT NULL,
    age          INT CHECK ( age >= 0) CHECK (age <= 120),
    phone_number TEXT NOT NULL
);

CREATE TABLE netology.orders
(
    id           BIGSERIAL PRIMARY KEY,
    date         DATE DEFAULT CURRENT_DATE,
    customer_id  INT REFERENCES CUSTOMERS (id),
    product_name TEXT NOT NULL,
    amount       DECIMAL(10, 2) CHECK (amount > 0)
);