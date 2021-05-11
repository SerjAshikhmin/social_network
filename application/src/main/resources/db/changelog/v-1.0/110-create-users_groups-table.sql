create table if not exists users_groups (
   users_users_id integer not null,
    groups_id integer not null,
    primary key (users_users_id, groups_id)
) engine=MyISAM
GO

alter table users_groups
   add constraint FK81ppoq45chrjspnds0bj6rttd
   foreign key (groups_id)
   references groupes (id)
GO

alter table users_groups
   add constraint FK6128l10aeu2a72kjqch1voe9q
   foreign key (users_users_id)
   references users (users_id)
GO
