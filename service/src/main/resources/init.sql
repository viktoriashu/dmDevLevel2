CREATE
    DATABASE rent_sup;

--Таблица пользователи.
-- Где number - номер телефона,
-- role - выбор роли пользователя, может быть либо "client" либо "admin".
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128)        NOT NULL,
    last_name  VARCHAR(128)        NOT NULL,
    login      VARCHAR(128) UNIQUE NOT NULL,
    password   VARCHAR(128)        NOT NULL,
    number     VARCHAR(128)        NOT NULL,
    role       VARCHAR(128)        NOT NULL
);

--Таблица Sup.
-- Где model - модель sup board.
CREATE TABLE sup
(
    id    BIGSERIAL PRIMARY KEY,
    model VARCHAR(128) NOT NULL
);

--Таблица заявка.
-- поле "status_sup" принимает значения : "free", "busy", "reserve".
--поле "status" принимает значения : "open" и "reserve", "paid", "close"
CREATE TABLE claim
(
    id              BIGSERIAL PRIMARY KEY,
    id_client       INT REFERENCES users (id),
    id_admin        INT REFERENCES users (id),
    id_sup          INT REFERENCES sup (id),
    data_start_rent DATE             NOT NULL,
    status_sup      VARCHAR(128)     NOT NULL,
    duration_rent   INT              NOT NULL,
    status          VARCHAR(128)     NOT NULL,
    price           DOUBLE PRECISION NOT NULL
);



