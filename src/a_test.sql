SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Company` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Company` (
  `StockCode` VARCHAR(10) NOT NULL ,
  `CompanyName` VARCHAR(45) NULL ,
  PRIMARY KEY (`StockCode`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ArticleDetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ArticleDetail` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`ArticleDetail` (
  `Company_StockCode` VARCHAR(10) NOT NULL ,
  `Article_Link` VARCHAR(200) NOT NULL ,
  `Headline` VARCHAR(200) NULL ,
  `DateTime` DATETIME NULL ,
  `PositiveScore` INT NULL ,
  `NegativeScore` VARCHAR(45) NULL ,
  PRIMARY KEY (`Company_StockCode`, `Article_Link`) ,
  INDEX `fk_ArticleDetail_Company1_idx` (`Company_StockCode` ASC) ,
  CONSTRAINT `fk_ArticleDetail_Company1`
    FOREIGN KEY (`Company_StockCode` )
    REFERENCES `mydb`.`Company` (`StockCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CompanyAliases`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`CompanyAliases` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`CompanyAliases` (
  `Company_StockCode` VARCHAR(10) NOT NULL ,
  `Alias` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`Company_StockCode`, `Alias`) ,
  INDEX `fk_CompanyAliases_Company_idx` (`Company_StockCode` ASC) ,
  CONSTRAINT `fk_CompanyAliases_Company`
    FOREIGN KEY (`Company_StockCode` )
    REFERENCES `mydb`.`Company` (`StockCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ArticleHit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ArticleHit` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`ArticleHit` (
  `ArticleDetail_Company_StockCode` VARCHAR(10) NOT NULL ,
  `ArticleDetail_Article_Link` VARCHAR(200) NOT NULL ,
  `PositiveNews` BINARY NULL ,
  `HitText` VARCHAR(40) NULL ,
  PRIMARY KEY (`ArticleDetail_Company_StockCode`, `ArticleDetail_Article_Link`) ,
  CONSTRAINT `fk_ArticleHit_ArticleDetail1`
    FOREIGN KEY (`ArticleDetail_Company_StockCode` , `ArticleDetail_Article_Link` )
    REFERENCES `mydb`.`ArticleDetail` (`Company_StockCode` , `Article_Link` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transaction` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Transaction` (
  `Company_StockCode` VARCHAR(10) NOT NULL ,
  `TransactionDateTime` DATETIME NULL ,
  `Price` FLOAT NULL ,
  `Commision` FLOAT NULL ,
  PRIMARY KEY (`Company_StockCode`) ,
  CONSTRAINT `fk_Transaction_Company1`
    FOREIGN KEY (`Company_StockCode` )
    REFERENCES `mydb`.`Company` (`StockCode` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
