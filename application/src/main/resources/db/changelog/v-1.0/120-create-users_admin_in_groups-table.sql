create table if not exists users_admin_in_groups (
   admins_users_id integer not null,
    admin_in_groups_id integer not null,
    primary key (admins_users_id, admin_in_groups_id)
) engine=MyISAM
GO