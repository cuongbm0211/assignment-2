DROP TABLE IF EXISTS group_;
create table group_
(
    id bigint auto_increment
        primary key,
    name varchar(100) not null,
    parent_group_id bigint null,
    constraint name
        unique (name)
);

DROP TABLE IF EXISTS role;
create table role
(
    id bigint auto_increment
        primary key,
    role_name varchar(100) not null,
    constraint role_name
        unique (role_name)
);

DROP TABLE IF EXISTS group_role;
create table group_role
(
    group_id bigint not null,
    role_id bigint not null,
    primary key (group_id, role_id),
    constraint FK_GROUPS_ROLE
        foreign key (group_id) references group_ (id)
            on update cascade on delete cascade,
    constraint FK_ROLE_GROUP
        foreign key (role_id) references role (id)
            on update cascade on delete cascade
);

DROP TABLE IF EXISTS security_token;
create table security_token
(
    id bigint auto_increment
        primary key,
    user_id bigint not null,
    access_token varchar(60) not null,
    refresh_token varchar(60) not null,
    token_status varchar(20) null,
    issued_at datetime null,
    access_token_expires_at datetime not null,
    refresh_token_expires_at datetime not null,
    last_seen_at datetime not null,
    ip_address varchar(20) null,
    agent_information varchar(255) null,
    constraint access_token
        unique (access_token),
    constraint refresh_token
        unique (refresh_token)
);

DROP TABLE IF EXISTS user;
create table user
(
    id bigint auto_increment
        primary key,
    user_name varchar(100) not null,
    social_id varchar(255) null,
    authentication_config_id int null,
    password_hash varchar(255) null,
    password_updated_at datetime null,
    password_expired_at datetime null,
    title varchar(20) null,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    birthday date null,
    status varchar(50) null,
    enabled bit default b'0' not null,
    created_at datetime null,
    created_by varchar(255) not null,
    last_modified_at datetime null,
    constraint user_name
        unique (user_name)
);

DROP TABLE IF EXISTS user_group;
create table user_group
(
    id bigint auto_increment
        primary key,
    user_id bigint not null,
    group_id bigint not null,
    constraint fk_user_groups_many_to_many
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
);