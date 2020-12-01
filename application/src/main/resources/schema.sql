--create schema if not exists socialnetwork;
--drop schema socialnetwork;

create table group_wall (
   id integer not null,
    primary key (id)
) engine=MyISAM;

create table group_wall_message (
   id integer not null,
    content varchar(1000),
    send_date datetime,
    group_wall_id integer,
    primary key (id)
) engine=MyISAM;

create table groupes (
   id integer not null,
    title varchar(45),
group_wall_id integer,
    primary key (id)
) engine=MyISAM;

create table private_message (
   id integer not null,
    content varchar(1000),
    send_date datetime,
    receiver_id integer,
    sender_id integer,
    primary key (id)
) engine=MyISAM;

create table roles (
   id integer not null,
    name varchar(45),
    primary key (id)
) engine=MyISAM;

create table user_principal (
   id integer not null,
    password varchar(45),
    user_name varchar(45),
    primary key (id)
) engine=MyISAM;

create table user_principal_roles (
   my_user_principal_id integer not null,
    roles_id integer not null,
    primary key (my_user_principal_id, roles_id)
) engine=MyISAM;

create table user_wall (
   id integer not null,
    primary key (id)
) engine=MyISAM;

create table user_wall_message (
   id integer not null,
    content varchar(1000),
    send_date datetime,
    user_wall_id integer,
    primary key (id)
) engine=MyISAM;

create table users (
   users_id integer not null,
    birth_date date,
    city varchar(20),
    country varchar(45),
    first_name varchar(20),
    gender varchar(10),
    last_name varchar(20),
    personal_info varchar(1000),
    user_principal_id integer,
    user_wall_id integer,
    primary key (users_id)
) engine=MyISAM;

create table users_friends (
   users_id integer not null,
    friends_id integer not null
) engine=MyISAM;

create table users_groups (
   users_users_id integer not null,
    groups_id integer not null,
    primary key (users_users_id, groups_id)
) engine=MyISAM;

alter table group_wall_message
   add constraint FKe5xm90e6iufkxgdyv9npp0wq7
   foreign key (group_wall_id)
   references group_wall (id);

alter table private_message
   add constraint FKq4f43ef8dtn1m1eglg64mkp9x
   foreign key (receiver_id)
   references users (users_id);

alter table private_message
   add constraint FKf6nbmipk0d9vln6rpcml7x883
   foreign key (sender_id)
   references users (users_id);

alter table user_principal_roles
   add constraint FKc1mhkmdmrmpfg2qw6swdv6kk9
   foreign key (roles_id)
   references roles (id);

alter table user_principal_roles
   add constraint FK75l7klqsxu838vmf9h5p53c5u
   foreign key (my_user_principal_id)
   references user_principal (id);

alter table user_wall_message
   add constraint FKei0anq3thjeepkkfnv22d3xes
   foreign key (user_wall_id)
   references user_wall (id);

alter table users_friends
   add constraint FKo51ktjiheso8mkdd5n4pdf9f3
   foreign key (friends_id)
   references users (users_id);

alter table users_friends
   add constraint FKt5mfh08l5faasks48vcgenvup
   foreign key (users_id)
   references users (users_id);

alter table users_groups
   add constraint FK81ppoq45chrjspnds0bj6rttd
   foreign key (groups_id)
   references groupes (id);

alter table users_groups
   add constraint FK6128l10aeu2a72kjqch1voe9q
   foreign key (users_users_id)
   references users (users_id);