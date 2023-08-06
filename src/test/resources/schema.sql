CREATE SCHEMA IF NOT EXISTS `e-commerce` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `e-commerce`;

CREATE TABLE IF NOT EXISTS `product_category`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `category_name` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `product`
(
    `id`             BIGINT     NOT NULL AUTO_INCREMENT,
    `sku`            VARCHAR(255),
    `name`           VARCHAR(255),
    `description`    VARCHAR(255),
    `unit_price`     DECIMAL(13, 2),
    `image_url`      VARCHAR(255),
    `active`         BIT DEFAULT 1,
    `units_in_stock` INT(11),
    `date_created`   DATETIME(6),
    `last_updated`   DATETIME(6),
    `category_id`    BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_category` (`category_id`),
    CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `country`
(
    `id`   SMALLINT UNSIGNED NOT NULL,
    `code` VARCHAR(2),
    `name` VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `state`
(
    `id`         SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255),
    `country_id` SMALLINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_country` (`country_id`),
    CONSTRAINT `fk_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `address`
(
    `id`       BIGINT NOT NULL AUTO_INCREMENT,
    `city`     VARCHAR(255),
    `country`  VARCHAR(255),
    `state`    VARCHAR(255),
    `street`   VARCHAR(255),
    `zip_code` VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `customer`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255),
    `last_name`  VARCHAR(255),
    `email`      VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `orders`
(
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `order_tracking_number` VARCHAR(255),
    `total_price`           DECIMAL(19, 2),
    `total_quantity`        INT,
    `billing_address_id`    BIGINT,
    `customer_id`           BIGINT,
    `shipping_address_id`   BIGINT,
    `status`                VARCHAR(128),
    `date_created`          DATETIME(6),
    `last_updated`          DATETIME(6),
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_billing_address_id` (`billing_address_id`),
    UNIQUE KEY `UK_shipping_address_id` (`shipping_address_id`),
    KEY `K_customer_id` (`customer_id`),
    CONSTRAINT `FK_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `FK_billing_address_id` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
    CONSTRAINT `FK_shipping_address_id` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `order_item`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `image_url`  VARCHAR(255),
    `quantity`   INT,
    `unit_price` DECIMAL(19, 2),
    `order_id`   BIGINT,
    `product_id` BIGINT,
    PRIMARY KEY (`id`),
    KEY `K_order_id` (`order_id`),
    CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
    CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE = InnoDB;
