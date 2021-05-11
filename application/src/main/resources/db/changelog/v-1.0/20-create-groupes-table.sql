create table if not exists groupes (
   id integer not null,
    title varchar(45),
group_wall_id integer,
    primary key (id)
) engine=MyISAM
GO