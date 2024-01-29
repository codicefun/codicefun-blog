# DROP DATABASE IF EXISTS `article`;
# CREATE DATABASE `article`;
# USE `article`;

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

    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    FOREIGN KEY (`type_id`) REFERENCES `type` (`id`)
);

CREATE TABLE `article_tag` (
    `article_id` INT NOT NULL,
    `tag_id`     INT NOT NULL,

    PRIMARY KEY (`article_id`, `tag_id`),

    FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
    FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
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

    FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
    FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`)
);

INSERT INTO `user` (`username`, `password`, `nickname`, `email`, `avatar`, `role`)
VALUES ('admin', 'admin', 'Admin', 'admin@codicefun.com', 'https://picsum.photos/200', 'admin');

INSERT INTO `type`(`name`)
VALUES ('Test'),
       ('frontend'),
       ('backend');

INSERT INTO `tag` (`name`)
VALUES ('Test'),
       ('Demo'),
       ('Example');

INSERT INTO `article` (`title`, `content`, `description`, `picture`, `user_id`, `type_id`)
VALUES ('Test1', 'Test1 content', 'Test1 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test2', 'Test2 content', 'Test2 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test3', 'Test3 content', 'Test3 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test4', 'Test4 content', 'Test4 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test5', 'Test5 content', 'Test5 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test6', 'Test6 content', 'Test6 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test7', 'Test7 content', 'Test7 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test8', 'Test8 content', 'Test8 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test9', 'Test9 content', 'Test9 description', 'https://picsum.photos/320/180', 1, 1),
       ('Test10', 'Test10 content', 'Test10 description', 'https://picsum.photos/320/180', 1, 1);

INSERT INTO `article_tag` (`article_id`, `tag_id`)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (1, 2),
       (1, 3);
