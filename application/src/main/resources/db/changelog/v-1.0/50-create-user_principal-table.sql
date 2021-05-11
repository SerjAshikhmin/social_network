create table if not exists user_principal (
   id integer not null,
    password varchar(45),
    user_name varchar(45),
    primary key (id)
) engine=MyISAM
GO

