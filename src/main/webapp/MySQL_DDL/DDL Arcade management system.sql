CREATE SCHEMA IF NOT EXISTS `arcadeDB` DEFAULT CHARACTER SET utf8 ;
USE `arcadeDB` ;

CREATE TABLE IF NOT EXISTS `arcadeDB`.`users` (
  `id` INT NOT NULL auto_increment,
  `fullName` VARCHAR(60) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `verifiedEmail` VARCHAR(5) NOT NULL,
  `emailVerificationToken` VARCHAR(45) NULL,
  `passwordResetToken` VARCHAR(45) NULL,

  PRIMARY KEY (`id`, `username`));

CREATE TABLE IF NOT EXISTS `arcadeDB`.`store` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `arcadeDB`.`game` (
  `id` INT NOT NULL auto_increment,
  `gameName` VARCHAR(45) NOT NULL,
  `StoreID` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Employee_Stores0`
    FOREIGN KEY (`StoreID`)
    REFERENCES `arcadeDB`.`store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);