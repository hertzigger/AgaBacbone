SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `AgaBacbone` ;
CREATE SCHEMA IF NOT EXISTS `AgaBacbone` DEFAULT CHARACTER SET utf8 ;
USE `AgaBacbone` ;

-- -----------------------------------------------------
-- Table `AgaBacbone`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`user` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`user` (
  `uuid` VARCHAR(36) NOT NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` TIMESTAMP NULL,
  `firstLogin` DATETIME NULL,
  `lastLogout` DATETIME NULL,
  `session` BIGINT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`username`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`username` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`username` (
  `idUsername` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NULL,
  `modified` TIMESTAMP NULL,
  `created` TIMESTAMP NULL,
  `firstLogin` DATETIME NULL,
  `lastLogout` DATETIME NULL,
  `uuid` VARCHAR(36) NULL,
  PRIMARY KEY (`idUsername`),
  INDEX `fkUserNamesUser1_idx` (`uuid` ASC),
  CONSTRAINT `fkUserNamesUser1`
    FOREIGN KEY (`uuid`)
    REFERENCES `AgaBacbone`.`user` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`ipAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`ipAddress` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`ipAddress` (
  `idIpAddress` BIGINT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(46) NULL,
  `created` TIMESTAMP NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idIpAddress`),
  UNIQUE INDEX `address_UNIQUE` (`address` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`userHasIpAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`userHasIpAddress` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`userHasIpAddress` (
  `idIpAddress` BIGINT NOT NULL,
  `created` TIMESTAMP NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uuid` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`idIpAddress`, `uuid`),
  INDEX `fkUserHasIpAddressIpAddress1_idx` (`idIpAddress` ASC),
  INDEX `fkUserHasIpAddressUser1_idx` (`uuid` ASC),
  CONSTRAINT `fkUserHasIpAddressIpAddress1`
    FOREIGN KEY (`idIpAddress`)
    REFERENCES `AgaBacbone`.`ipAddress` (`idIpAddress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkUserHasIpAddressUser1`
    FOREIGN KEY (`uuid`)
    REFERENCES `AgaBacbone`.`user` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`server`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`server` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`server` (
  `idServer` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `created` TIMESTAMP NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idServer`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`world`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`world` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`world` (
  `idWorld` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `created` TIMESTAMP NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idServer` BIGINT NULL,
  PRIMARY KEY (`idWorld`),
  INDEX `fkWorldServer1_idx` (`idServer` ASC),
  CONSTRAINT `fkWorldServer1`
    FOREIGN KEY (`idServer`)
    REFERENCES `AgaBacbone`.`server` (`idServer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`session` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`session` (
  `idSession` BIGINT NOT NULL AUTO_INCREMENT,
  `login` TIMESTAMP NULL,
  `logout` TIMESTAMP NULL,
  `created` TIMESTAMP NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idWorld` BIGINT NOT NULL,
  PRIMARY KEY (`idSession`),
  INDEX `fkSessionWorld1_idx` (`idWorld` ASC),
  CONSTRAINT `fkSessionWorld1`
    FOREIGN KEY (`idWorld`)
    REFERENCES `AgaBacbone`.`world` (`idWorld`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`usernameHasSession`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`usernameHasSession` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`usernameHasSession` (
  `idUsername` BIGINT NOT NULL,
  `idSession` BIGINT NOT NULL,
  `created` TIMESTAMP NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUsername`, `idSession`),
  INDEX `fkUserNamesHasSessionSession1_idx` (`idSession` ASC),
  INDEX `fkUserNamesHasSessionUserNames1_idx` (`idUsername` ASC),
  CONSTRAINT `fkUserNamesHasSessionUserNames1`
    FOREIGN KEY (`idUsername`)
    REFERENCES `AgaBacbone`.`username` (`idUsername`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkUserNamesHasSessionSession1`
    FOREIGN KEY (`idSession`)
    REFERENCES `AgaBacbone`.`session` (`idSession`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`location` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`location` (
  `idLocation` BIGINT NOT NULL AUTO_INCREMENT,
  `x` DOUBLE NULL,
  `y` DOUBLE NULL,
  `z` DOUBLE NULL,
  `pitch` DOUBLE NULL,
  `yaw` FLOAT NULL,
  `created` TIMESTAMP NULL,
  `modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idSession` BIGINT NOT NULL,
  PRIMARY KEY (`idLocation`),
  INDEX `fkLocationSession1_idx` (`idSession` ASC),
  CONSTRAINT `fkLocationSession1`
    FOREIGN KEY (`idSession`)
    REFERENCES `AgaBacbone`.`session` (`idSession`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`instruction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`instruction` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`instruction` (
  `idinstructions` BIGINT NOT NULL AUTO_INCREMENT,
  `instruction` VARCHAR(255) NULL,
  `created` TIMESTAMP NULL,
  `idSession` BIGINT NOT NULL,
  PRIMARY KEY (`idinstructions`),
  INDEX `fkInstructionSession1_idx` (`idSession` ASC),
  CONSTRAINT `fkInstructionSession1`
    FOREIGN KEY (`idSession`)
    REFERENCES `AgaBacbone`.`session` (`idSession`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`chat` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`chat` (
  `idChat` BIGINT NOT NULL AUTO_INCREMENT,
  `chat` VARCHAR(255) NULL,
  `created` TIMESTAMP NULL,
  `idSession` BIGINT NOT NULL,
  PRIMARY KEY (`idChat`),
  INDEX `fkChatSession1_idx` (`idSession` ASC),
  CONSTRAINT `fkChatSession1`
    FOREIGN KEY (`idSession`)
    REFERENCES `AgaBacbone`.`session` (`idSession`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`inventory` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`inventory` (
  `idInventory` BIGINT NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NULL,
  PRIMARY KEY (`idInventory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`playerSnapshot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`playerSnapshot` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`playerSnapshot` (
  `idplayerSnapshot` BIGINT NOT NULL AUTO_INCREMENT,
  `idSession` BIGINT NOT NULL,
  `vanished` TINYINT NULL,
  `god` TINYINT NULL,
  `gamemode` TINYINT NULL,
  `idInventory` BIGINT NOT NULL,
  PRIMARY KEY (`idplayerSnapshot`),
  INDEX `fkPlayerSnapshotSession1_idx` (`idSession` ASC),
  INDEX `fkPlayerSnapshotInventory1_idx` (`idInventory` ASC),
  CONSTRAINT `fkPlayerSnapshotSession1`
    FOREIGN KEY (`idSession`)
    REFERENCES `AgaBacbone`.`session` (`idSession`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkPlayerSnapshotInventory1`
    FOREIGN KEY (`idInventory`)
    REFERENCES `AgaBacbone`.`inventory` (`idInventory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`kill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`kill` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`kill` (
  `idKill` BIGINT NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NULL,
  `killerSnapshot` BIGINT NOT NULL,
  `victimSnapshot` BIGINT NOT NULL,
  PRIMARY KEY (`idKill`),
  INDEX `fkKillPlayerSnapshot1_idx` (`killerSnapshot` ASC),
  INDEX `fkKillPlayerSnapshot2_idx` (`victimSnapshot` ASC),
  CONSTRAINT `fkKillPlayerSnapshot1`
    FOREIGN KEY (`killerSnapshot`)
    REFERENCES `AgaBacbone`.`playerSnapshot` (`idplayerSnapshot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkKillPlayerSnapshot2`
    FOREIGN KEY (`victimSnapshot`)
    REFERENCES `AgaBacbone`.`playerSnapshot` (`idplayerSnapshot`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`itemStack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`itemStack` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`itemStack` (
  `idItemStack` BIGINT NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NULL,
  `amount` INT(11) NULL,
  `type` INT(11) NULL,
  `location` INT(11) NULL,
  `materiaData` BINARY NULL,
  `durability` SMALLINT NULL,
  PRIMARY KEY (`idItemStack`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`inventoryHasItemStack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`inventoryHasItemStack` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`inventoryHasItemStack` (
  `idInventory` BIGINT NOT NULL,
  `idItemStack` BIGINT NOT NULL,
  PRIMARY KEY (`idInventory`, `idItemStack`),
  INDEX `fkInventoryHasItemStackItemStack1_idx` (`idItemStack` ASC),
  INDEX `fkInventoryHasItemStackInventory1_idx` (`idInventory` ASC),
  CONSTRAINT `fkInventoryHasItemStackInventory1`
    FOREIGN KEY (`idInventory`)
    REFERENCES `AgaBacbone`.`inventory` (`idInventory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkInventoryHasItemStackItemStack1`
    FOREIGN KEY (`idItemStack`)
    REFERENCES `AgaBacbone`.`itemStack` (`idItemStack`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`enchantment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`enchantment` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`enchantment` (
  `idEnchantment` BIGINT NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NULL,
  `enchantment` INT NULL,
  `level` INT NULL,
  PRIMARY KEY (`idEnchantment`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AgaBacbone`.`itemStackHasEnchantment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AgaBacbone`.`itemStackHasEnchantment` ;

CREATE TABLE IF NOT EXISTS `AgaBacbone`.`itemStackHasEnchantment` (
  `idItemStack` BIGINT NOT NULL,
  `idEnchantment` BIGINT NOT NULL,
  PRIMARY KEY (`idItemStack`, `idEnchantment`),
  INDEX `fkItemStackHasEnchantmentEnchantment1_idx` (`idEnchantment` ASC),
  INDEX `fkItemStackHasEnchantmentItemStack1_idx` (`idItemStack` ASC),
  CONSTRAINT `fkItemStackHasEnchantmentItemStack1`
    FOREIGN KEY (`idItemStack`)
    REFERENCES `AgaBacbone`.`itemStack` (`idItemStack`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkItemStackHasEnchantmentEnchantment1`
    FOREIGN KEY (`idEnchantment`)
    REFERENCES `AgaBacbone`.`enchantment` (`idEnchantment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
