CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

CREATE TABLE users (
    username varchar(50) NOT NULL,
    password varchar(500) NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users VALUES ('user1', '$2a$10$iTiPH/73.D5CPuWrb6eY2.dKxp/.mrKWqGX3vIxtz0xNcWQhRMf/u', 1);
INSERT INTO users VALUES ('user2', '$2a$10$iTiPH/73.D5CPuWrb6eY2.dKxp/.mrKWqGX3vIxtz0xNcWQhRMf/u', 1);
INSERT INTO users VALUES ('kylin', '$2a$10$iTiPH/73.D5CPuWrb6eY2.dKxp/.mrKWqGX3vIxtz0xNcWQhRMf/u', 1);
insert into users values ('test', 'test', 1);
