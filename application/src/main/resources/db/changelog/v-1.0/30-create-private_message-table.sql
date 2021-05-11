create table if not exists private_message (
   id integer not null,
    content varchar(1000),
    send_date datetime,
    is_read bit not null,
    receiver_id integer,
    sender_id integer,
    primary key (id)
) engine=MyISAM
GO

alter table private_message
   add constraint FKq4f43ef8dtn1m1eglg64mkp9x
   foreign key (receiver_id)
   references users (users_id)
GO

alter table private_message
   add constraint FKf6nbmipk0d9vln6rpcml7x883
   foreign key (sender_id)
   references users (users_id)
GO