-- CREATE DATABASE game;
-- DROP DATABASE IF EXISTS game;

-- CREATE SCHEMA data_storage;
-- we will use public schema
-- DROP SCHEMA data_storage;

DROP TABLE IF EXISTS t_answer;
DROP TABLE IF EXISTS t_question;
DROP TABLE IF EXISTS t_game;
DROP TABLE IF EXISTS t_game_state;
DROP TABLE IF EXISTS t_quest;
DROP TABLE IF EXISTS t_user_info;
DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(128) UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     VARCHAR(32)
);

INSERT INTO t_user(login, password, role)
VALUES ('Ivan', '456', 'ADMIN'),   --1
       ('Andrew', '789', 'GUEST'), --2
       ('Elena', '123', 'USER'); --3

CREATE TABLE t_user_info
(
    id      BIGSERIAL PRIMARY KEY,
    address VARCHAR(128),
    phone   VARCHAR(128),
    user_id BIGINT REFERENCES t_user
);

INSERT INTO t_user_info(address, phone, user_id)
VALUES ('Urban street 11 23', '+380 (50) 123-45-67', 1),
       ('Lenina 22 34', '+7 (095) 123-45-67', 2),
       ('Very 33 45', '+380 (50) 123-45-67', 3)
;

SELECT DISTINCT id f_id, login f_login, password
  FROM t_user
 WHERE (id > 1 OR id < 3)
   AND id != 2
 ORDER BY id DESC;

-- DELETE
--   FROM "user"
--  WHERE id > 1;
--
-- DELETE
--   FROM "user"
--  WHERE id NOT BETWEEN 7 AND 22;

UPDATE t_user
   SET login='vanya',
       password='654'
 WHERE id = 1;

SELECT *
  FROM t_user
 ORDER BY id;

/* lesson 3 demo */
CREATE TABLE t_quest
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT REFERENCES t_user (id),
    "name"          varchar(256) NOT NULL,
    "text"          text         NOT NULL,
    startQuestionId INT DEFAULT NULL
);

INSERT INTO t_quest(user_id, name, text, startQuestionId)
VALUES (1, 'Проверим арифметику', 'исходный текст квеста', -1),
       (1, 'Второй квест', 'исходный текст второго квеста', -1),
       (1, 'Третий квест', 'исходный текст третьего квеста', -1);

SELECT *
  FROM "t_quest";

CREATE TABLE t_game_state
(
    value varchar(64) PRIMARY KEY
);

INSERT INTO t_game_state(value)
VALUES ('PLAY'),
       ('WIN'),
       ('LOST');

CREATE TABLE t_question
(
    id         BIGSERIAL PRIMARY KEY,
    quest_id   BIGINT REFERENCES t_quest (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    "text"     text,
    game_state varchar(64) REFERENCES t_game_state (value)

);


INSERT INTO t_question(quest_id, text, game_state)
VALUES (1, 'Знаешь арифметику?', 'PLAY'),                                       --1
       (1, 'Сколько будет дважды два?', 'PLAY'),                                --2
       (1, 'Эх... это проигрыш. Надо мне было лучше учиться в школе.', 'LOST'), --3
       (1, 'Ура, победа в сложнейшем квесте!!!', 'WIN'); --4

CREATE TABLE t_answer
(
    id               BIGSERIAL PRIMARY KEY,
    question_id      BIGINT REFERENCES t_question (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    "text"           text,
    next_question_id BIGINT
);

INSERT INTO t_answer(question_id, text, next_question_id)
VALUES (1, 'Да, конечно', 2),
       (1, 'А что это такое?', 3),
       (2, 'Один', 3),
       (2, 'Два', 3),
       (2, 'Три', 3),
       (2, 'Четыре', 4);

CREATE TABLE t_game
(
    id                  BIGSERIAL PRIMARY KEY,
    quest_id            BIGINT REFERENCES t_quest (id),
    user_id             BIGINT REFERENCES t_user (id),
    current_question_id BIGINT,
    game_state          varchar(64) REFERENCES t_game_state (value)
);

INSERT INTO t_game(quest_id, user_id, current_question_id, game_state)
VALUES (1, 1, 1, 'PLAY'),
       (1, 1, NULL, 'WIN'),
       (1, 1, NULL, 'WIN'),
       (1, 1, NULL, 'LOST'),
       (1, 2, 1, 'PLAY'),
       (1, 2, NULL, 'WIN');

SELECT login, password, role
  FROM "t_user"
 WHERE id = 0;

