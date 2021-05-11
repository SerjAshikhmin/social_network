create table if not exists user_principal_roles (
   my_user_principal_id integer not null,
    roles_id integer not null,
    primary key (my_user_principal_id, roles_id)
) engine=MyISAM
GO

alter table user_principal_roles
   add constraint FKc1mhkmdmrmpfg2qw6swdv6kk9
   foreign key (roles_id)
   references roles (id)
GO

alter table user_principal_roles
   add constraint FK75l7klqsxu838vmf9h5p53c5u
   foreign key (my_user_principal_id)
   references user_principal (id)
GO
