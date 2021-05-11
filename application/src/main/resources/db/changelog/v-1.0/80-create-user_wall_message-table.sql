create table if not exists user_wall_message (
   id integer not null,
    content varchar(1000),
    send_date datetime,
    user_wall_id integer,
    primary key (id)
) engine=MyISAM
GO

alter table user_wall_message
   add constraint FKei0anq3thjeepkkfnv22d3xes
   foreign key (user_wall_id)
   references user_wall (id)
GO