-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema game
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `game` ;

-- -----------------------------------------------------
-- Schema game
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `game` DEFAULT CHARACTER SET utf8 ;
USE `game` ;

-- -----------------------------------------------------
-- Table `game`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`role` (
                                             `value` VARCHAR(50) NOT NULL,
                                             PRIMARY KEY (`value`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`user` (
                                             `id` INT NOT NULL,
                                             `login` VARCHAR(45) NULL,
                                             `password` VARCHAR(45) NULL,
                                             `role_value` VARCHAR(50) NOT NULL,
                                             PRIMARY KEY (`id`),
                                             UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
                                             INDEX `fk_user_role_idx` (`role_value` ASC) VISIBLE,
                                             CONSTRAINT `fk_user_role`
                                                 FOREIGN KEY (`role_value`)
                                                     REFERENCES `game`.`role` (`value`)
                                                     ON DELETE CASCADE
                                                     ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`quest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`quest` (
                                              `id` INT NOT NULL,
                                              `text` VARCHAR(1024) NULL,
                                              `name` VARCHAR(256) NULL,
                                              `start_question_id` INT NULL,
                                              `author_id` INT NOT NULL,
                                              PRIMARY KEY (`id`),
                                              INDEX `fk_quest_user1_idx` (`author_id` ASC) VISIBLE,
                                              CONSTRAINT `fk_quest_user1`
                                                  FOREIGN KEY (`author_id`)
                                                      REFERENCES `game`.`user` (`id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`game_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`game_state` (
                                                   `value` VARCHAR(50) NOT NULL,
                                                   PRIMARY KEY (`value`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`question` (
                                                 `id` INT NOT NULL,
                                                 `text` VARCHAR(256) NULL,
                                                 `quest_id` INT NOT NULL,
                                                 `game_state` VARCHAR(50) NOT NULL,
                                                 PRIMARY KEY (`id`),
                                                 INDEX `fk_question_quest1_idx` (`quest_id` ASC) VISIBLE,
                                                 INDEX `fk_question_game_state1_idx` (`game_state` ASC) VISIBLE,
                                                 CONSTRAINT `fk_question_quest1`
                                                     FOREIGN KEY (`quest_id`)
                                                         REFERENCES `game`.`quest` (`id`)
                                                         ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
                                                 CONSTRAINT `fk_question_game_state1`
                                                     FOREIGN KEY (`game_state`)
                                                         REFERENCES `game`.`game_state` (`value`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`answer` (
                                               `id` INT NOT NULL,
                                               `text` VARCHAR(45) NULL,
                                               `next_question_id` INT NULL,
                                               `question_id` INT NOT NULL,
                                               PRIMARY KEY (`id`),
                                               INDEX `fk_answer_question1_idx` (`question_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_answer_question1`
                                                   FOREIGN KEY (`question_id`)
                                                       REFERENCES `game`.`question` (`id`)
                                                       ON DELETE CASCADE
                                                       ON UPDATE CASCADE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`game` (
                                             `id` INT NOT NULL,
                                             `current_quesion_id` VARCHAR(45) NULL,
                                             `game_state_value` VARCHAR(50) NOT NULL,
                                             `user_id` INT NOT NULL,
                                             `quest_id` INT NOT NULL,
                                             PRIMARY KEY (`id`),
                                             INDEX `fk_game_game_state1_idx` (`game_state_value` ASC) VISIBLE,
                                             INDEX `fk_game_user1_idx` (`user_id` ASC) VISIBLE,
                                             INDEX `fk_game_quest1_idx` (`quest_id` ASC) VISIBLE,
                                             CONSTRAINT `fk_game_game_state1`
                                                 FOREIGN KEY (`game_state_value`)
                                                     REFERENCES `game`.`game_state` (`value`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION,
                                             CONSTRAINT `fk_game_user1`
                                                 FOREIGN KEY (`user_id`)
                                                     REFERENCES `game`.`user` (`id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION,
                                             CONSTRAINT `fk_game_quest1`
                                                 FOREIGN KEY (`quest_id`)
                                                     REFERENCES `game`.`quest` (`id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `game`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `game`;
INSERT INTO `game`.`role` (`value`) VALUES ('ADMIN');
INSERT INTO `game`.`role` (`value`) VALUES ('USER');
INSERT INTO `game`.`role` (`value`) VALUES ('GUEST');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `game`;
INSERT INTO `game`.`user` (`id`, `login`, `password`, `role_value`) VALUES (1, 'Ivan', '456', 'ADMIN');
INSERT INTO `game`.`user` (`id`, `login`, `password`, `role_value`) VALUES (2, 'Andrew', '789', 'GUEST');
INSERT INTO `game`.`user` (`id`, `login`, `password`, `role_value`) VALUES (3, 'Elena', '123', 'USER');
INSERT INTO `game`.`user` (`id`, `login`, `password`, `role_value`) VALUES (4, 'Sergeii', '987654321', 'USER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`.`game_state`
-- -----------------------------------------------------
START TRANSACTION;
USE `game`;
INSERT INTO `game`.`game_state` (`value`) VALUES ('PLAY');
INSERT INTO `game`.`game_state` (`value`) VALUES ('WIN');
INSERT INTO `game`.`game_state` (`value`) VALUES ('LOST');

COMMIT;

