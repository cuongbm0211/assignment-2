-- prepare role
INSERT INTO `role` (id, role_name) VALUES(1, 'ADMIN');
INSERT INTO `role` (id, role_name) VALUES(3, 'APPROVER');
INSERT INTO `role` (id, role_name) VALUES(2, 'EDITOR');

-- prepare group
INSERT INTO group_ (id, name, parent_group_id) VALUES(1, 'CONTENT_GROUP', NULL);
INSERT INTO group_ (id, name, parent_group_id) VALUES(2, 'ADMIN_GROUP', NULL);

-- group role
INSERT INTO group_role (group_id, role_id) VALUES(2, 1);
INSERT INTO group_role (group_id, role_id) VALUES(1, 2);
INSERT INTO group_role (group_id, role_id) VALUES(1, 3);

-- user, password: 123456
INSERT INTO `user` (id, user_name, social_id, authentication_config_id, password_hash, password_updated_at, password_expired_at, title, first_name, last_name, birthday, status, enabled, created_at, created_by, last_modified_at) VALUES(1, 'alex@gmail.com', NULL, NULL, '$2a$10$RYJ8C6STz8v59h1yVaIZGeKHjcgZWjitz2.v7ozFs2Ji1Z//6EiVy', '2021-05-25 12:00:00', '2022-05-25 12:00:00', 'mr', 'Bone', 'Barthe', '1986-05-25', 'ACTIVE', 1, '2021-05-25 12:00:00', 'admin', '2021-05-25 12:00:00');
INSERT INTO `user` (id, user_name, social_id, authentication_config_id, password_hash, password_updated_at, password_expired_at, title, first_name, last_name, birthday, status, enabled, created_at, created_by, last_modified_at) VALUES(2, 'bob@gmail.com', NULL, NULL, '$2a$10$RYJ8C6STz8v59h1yVaIZGeKHjcgZWjitz2.v7ozFs2Ji1Z//6EiVy', '2021-05-25 12:00:00', '2022-05-25 12:00:00', 'mr', 'Bone', 'Barthe', '1986-05-25', 'ACTIVE', 1, '2021-05-25 12:00:00', 'admin', '2021-05-25 12:00:00');
INSERT INTO `user` (id, user_name, social_id, authentication_config_id, password_hash, password_updated_at, password_expired_at, title, first_name, last_name, birthday, status, enabled, created_at, created_by, last_modified_at) VALUES(3, 'peter@gmail.com', NULL, NULL, '$2a$10$RYJ8C6STz8v59h1yVaIZGeKHjcgZWjitz2.v7ozFs2Ji1Z//6EiVy', '2021-05-25 12:00:00', '2022-05-25 12:00:00', 'mr', 'Bone', 'Barthe', '1986-05-25', 'ACTIVE', 1, '2021-05-25 12:00:00', 'admin', '2021-05-25 12:00:00');

-- user_group
INSERT INTO user_group (id, user_id, group_id) VALUES(1, 1, 1);
INSERT INTO user_group (id, user_id, group_id) VALUES(2, 2, 2);
INSERT INTO user_group (id, user_id, group_id) VALUES(3, 3, 2);
