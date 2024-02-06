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

INSERT INTO `comment` (`nickname`, `email`, `content`, `article_id`, `parent_id`)
VALUES ('guest', 'guest@codicefun.com', 'Nice blog', 1, NULL)
