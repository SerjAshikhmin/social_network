-- DROP DATABASE socialnetwork;
-- CREATE DATABASE socialnetwork WITH OWNER = postgres ENCODING = 'UTF8' TABLESPACE = pg_default LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251' CONNECTION LIMIT = -1;

create table group_wall (
   id int4 not null,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE group_wall OWNER TO postgres;

create table group_wall_message (
   id int4 not null,
    content varchar(1000) not null,
    send_date timestamp not null,
    group_wall_id int4,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE group_wall_message OWNER TO postgres;

create table groupes (
   id int4 not null,
    title varchar(45) not null,
    group_wall_id int4,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE groupes OWNER TO postgres;

create table private_message (
   id int4 not null,
    content varchar(1000) not null,
    send_date timestamp not null,
    is_read boolean not null,
    receiver_id int4,
    sender_id int4,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE private_message OWNER TO postgres;

create table roles (
   id int4 not null,
    name varchar(45) not null,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE roles OWNER TO postgres;

create table user_principal (
   id int4 not null,
    password varchar(45) not null,
    user_name varchar(45) not null,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE user_principal OWNER TO postgres;

create table user_principal_roles (
   my_user_principal_id int4 not null,
    roles_id int4 not null,
    primary key (my_user_principal_id, roles_id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE user_principal_roles OWNER TO postgres;

create table user_wall (
   id int4 not null,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE user_wall OWNER TO postgres;

create table user_wall_message (
   id int4 not null,
    content varchar(1000) not null,
    send_date timestamp not null,
    user_wall_id int4,
    primary key (id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE user_wall_message OWNER TO postgres;

create table users (
   users_id int4 not null,
    birth_date date,
    city varchar(20),
    country varchar(45),
    first_name varchar(20) not null,
    gender varchar(10) not null,
    last_name varchar(20) not null,
    personal_info varchar(1000),
    user_principal_id int4,
    user_wall_id int4,
    primary key (users_id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE users OWNER TO postgres;

create table users_friends (
   users_id int4 not null,
    friends_id int4 not null
) WITH (
  OIDS=FALSE
);
ALTER TABLE users_friends OWNER TO postgres;

create table users_groups (
   users_users_id int4 not null,
    groups_id int4 not null,
    primary key (users_users_id, groups_id)
) WITH (
  OIDS=FALSE
);
ALTER TABLE users_groups OWNER TO postgres;
