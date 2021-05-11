create table if not exists users_friends (
   users_id integer not null,
    friends_id integer not null
) engine=MyISAM
GO

alter table users_friends
   add constraint FKo51ktjiheso8mkdd5n4pdf9f3
   foreign key (friends_id)
   references users (users_id)
GO

alter table users_friends
   add constraint FKt5mfh08l5faasks48vcgenvup
   foreign key (users_id)
   references users (users_id)
GO