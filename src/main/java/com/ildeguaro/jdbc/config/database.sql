CREATE DATABASE ildeguaro; 
use ildeguaro; 
CREATE TABLE `todo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `task` VARCHAR(200) NULL,
  `done` TINYINT NULL,
  PRIMARY KEY (`id`));
