CREATE DATABASE game;

CREATE SCHEMA data_storage;

DROP SCHEMA data_storage;

CREATE TABLE data_storage.user
(
    id       INT PRIMARY KEY,
    login    VARCHAR(128) UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     VARCHAR(32)
);

DROP TABLE data_storage.user;

INSERT INTO data_storage.user(id, login, password, role)
VALUES (1, 'Ivan', '456', 'ADMIN'),
       (2, 'Andrew', '678', 'GUEST'),
       (3, 'Elena', '123', 'USER');

SELECT DISTINCT id f_id, login f_login, password
  FROM data_storage.user
 WHERE (id > 1 OR id < 3)
   AND id != 2
 ORDER BY id DESC;

DELETE
  FROM data_storage.user
 WHERE id > 1;

DELETE
  FROM data_storage.user
 WHERE id NOT BETWEEN 7 AND 22;

UPDATE data_storage.user
   SET login='ivan',
       password='s555'
 WHERE id = 1;

SELECT *
  FROM data_storage.user;

/* lesson 3 demo */
CREATE TABLE data_storage.game_state
(
    value varchar(64) PRIMARY KEY
);

INSERT INTO game_state(value)
VALUES ('PLAY'),
       ('WIN'),
       ('LOST');

CREATE TABLE quest
(
    id              INT PRIMARY KEY,
    user_id         INT REFERENCES data_storage.user (id),
    name            varchar(256) NOT NULL,
    txt             text         NOT NULL,
    startQuestionId INT DEFAULT NULL
);

CREATE TABLE question
(
    id               INT,
    quest_id         INT REFERENCES data_storage.quest (id),
    txt              text,
    game_state_value varchar(64) REFERENCES data_storage.game_state (value)
);

INSERT INTO quest(id, user_id, name, txt, startQuestionId)
VALUES (10, 1, 'Проверим арифметику', 'исходный текст квеста', -1);

SELECT *
  FROM quest;

DROP TABLE quest;