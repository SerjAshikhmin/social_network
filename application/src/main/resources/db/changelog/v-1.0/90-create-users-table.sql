create table if not exists users (
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
) engine=MyISAM
GO