DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `article_tag`;
DROP TABLE IF EXISTS `article`;
DROP TABLE IF EXISTS `tag`;
DROP TABLE IF EXISTS `type`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `username`    VARCHAR(255) NOT NULL,
    `password`    VARCHAR(255) NOT NULL,
    `nickname`    VARCHAR(255) NOT NULL,
    `email`       VARCHAR(255) NOT NULL,
    `avatar`      VARCHAR(255) NOT NULL,
    `role`        VARCHAR(255) NOT NULL,
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`)
);

CREATE TABLE `type` (
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`)
);

CREATE TABLE `tag` (
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,

    PRIMARY KEY (`id`)
);

CREATE TABLE `article` (
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `content`     LONGTEXT     NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `picture`     VARCHAR(255) NOT NULL,
    `user_id`     INT          NOT NULL,
    `type_id`     INT          NOT NULL,
    `viewed`      INT          NOT NULL DEFAULT 0,
    `liked`       INT          NOT NULL DEFAULT 0,
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `article_tag` (
    `article_id` INT NOT NULL,
    `tag_id`     INT NOT NULL,

    PRIMARY KEY (`article_id`, `tag_id`),

    FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `comment` (
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `nickname`    VARCHAR(255) NOT NULL,
    `email`       VARCHAR(255) NOT NULL,
    `content`     LONGTEXT     NOT NULL,
    `article_id`  INT          NOT NULL,
    `parent_id`   INT                   DEFAULT NULL,
    `liked`       INT          NOT NULL DEFAULT 0,
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
