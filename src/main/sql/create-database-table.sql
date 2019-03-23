CREATE DATABASE IF NOT EXISTS `product_inventory`;
USE `product_inventory`;

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
	`id` INT(20) NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(50) DEFAULT NULL,
    `lastSold` VARCHAR(10) DEFAULT NULL,
    `shelfLife` VARCHAR(5) DEFAULT NULL,
    `department` VARCHAR(50) DEFAULT NULL,
    `price` VARCHAR(10) DEFAULT NULL,
    `unit` VARCHAR(10) DEFAULT NULL,
    `xFor` INT(5) DEFAULT NULL,
    `cost` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- altering id column to display longer integers
-- -- ALTER TABLE product_inventory.products MODIFY `id` INT(20) 