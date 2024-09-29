CREATE
    DATABASE rent_sup;

--Таблица пользователи.
-- role - выбор роли пользователя, может быть либо "client" либо "admin".
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR(128)        NOT NULL,
    last_name    VARCHAR(128)        NOT NULL,
    login        VARCHAR(128) UNIQUE NOT NULL,
    password     VARCHAR(128)        NOT NULL,
    phone_number VARCHAR(128)        NOT NULL,
    role         VARCHAR(128)        NOT NULL
);

DROP TABLE users;

--Таблица Sup.
--Где model - модель sup board.
CREATE TABLE sup
(
    id           BIGSERIAL PRIMARY KEY,
    model        VARCHAR(128)     NOT NULL,
    number_seats INT              NOT NULL,
    description  VARCHAR(500)     NOT NULL,
    image        VARCHAR(128),
    price        DOUBLE PRECISION NOT NULL
);

DROP TABLE sup;

--Таблица дополнительное оборудование.
--Где name - наименование доп. об-я (доп. жилет, эл. насос, чехол для телефона, гермомешок...)
CREATE TABLE extras
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(128)     NOT NULL,
    description VARCHAR(500)     NOT NULL,
    image       VARCHAR(128),
    price       DOUBLE PRECISION NOT NULL
);

DROP TABLE extras;

--Таблица заявка.
--поле "status" принимает значения : "open" и "reserve", "paid", "close"
CREATE TABLE claim
(
    id              BIGSERIAL PRIMARY KEY,
    client_id       INT REFERENCES users (id),
    admin_id        INT REFERENCES users (id),
    sup_id          INT REFERENCES sup (id),
    extras_id       INT REFERENCES extras (id),
    data_start_rent DATE             NOT NULL,
    duration_rent   INT              NOT NULL,
    status          VARCHAR(128)     NOT NULL,
    price           DOUBLE PRECISION NOT NULL
);

DROP TABLE claim;


