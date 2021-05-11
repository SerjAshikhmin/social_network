create table if not exists group_wall_message (
   id integer not null,
    content varchar(1000),
    send_date datetime,
    group_wall_id integer,
    primary key (id)
) engine=MyISAM
GO

alter table group_wall_message
   add constraint FKe5xm90e6iufkxgdyv9npp0wq7
   foreign key (group_wall_id)
   references group_wall (id)
GO

