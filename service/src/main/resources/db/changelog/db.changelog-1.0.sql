--liquibase formatted sql

CREATE
    DATABASE rent_sup;

--Таблица пользователи.
-- role - выбор роли пользователя, может быть либо "client" либо "admin".
--changeset viktoria:1
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR(128)        NOT NULL,
    last_name    VARCHAR(128)        NOT NULL,
    login        VARCHAR(128) UNIQUE NOT NULL,
    password     VARCHAR(128)        NOT NULL,
    phone_number VARCHAR(128) UNIQUE NOT NULL,
    role         VARCHAR(128)        NOT NULL
);
--rollback DROP TABLE users;

--Таблица Sup.
--Где model - модель sup board.
--changeset viktoria:2
CREATE TABLE sup
(
    id           BIGSERIAL PRIMARY KEY,
    model        VARCHAR(128)     NOT NULL,
    number_seats INT              NOT NULL,
    description  VARCHAR(500)     NOT NULL,
    image        VARCHAR(128),
    price        NUMERIC(38,2) NOT NULL
);
--rollback DROP TABLE sup;

--Таблица дополнительное оборудование.
--Где name - наименование доп. об-я (доп. жилет, эл. насос, чехол для телефона, гермомешок...)
--changeset viktoria:3
CREATE TABLE extras
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128)     NOT NULL,
    description VARCHAR(500)     NOT NULL,
    image       VARCHAR(128),
    price       NUMERIC(38,2) NOT NULL
);
--rollback DROP TABLE extras;

--Таблица заявка.
--поле "status" принимает значения : "open" и "reserve", "paid", "close"
--changeset viktoria:4
CREATE TABLE claim
(
    id              BIGSERIAL PRIMARY KEY,
    client_id       BIGINT REFERENCES users (id),
    admin_id        BIGINT REFERENCES users (id),
    sup_id          BIGINT REFERENCES sup (id),
    data_start_rent DATE             NOT NULL,
    duration_rent   INT              NOT NULL,
    status          VARCHAR(128)     NOT NULL,
    price           NUMERIC(38,2) NOT NULL
);
--rollback DROP TABLE claim;

--changeset viktoria:5
CREATE TABLE extras_claim
(
    id        BIGSERIAL PRIMARY KEY,
    extras_id BIGINT REFERENCES extras (id),
    claim_id  BIGINT REFERENCES claim (id)
);
--rollback DROP TABLE extras_claim;


