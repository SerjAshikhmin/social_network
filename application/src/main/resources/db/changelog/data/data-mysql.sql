insert into user_principal (id, user_name, password)
     values (1, 'anonymousUser', 'pass1'),
            (2, 'ali_daria', 'pass2'),
            (3, 'lap_ser', 'pass3'),
            (4, 'el_dor', 'pass4'),
            (5, 'kl_irina', 'pass5'),
            (6, 'gomez_i', 'pass6'),
            (7, 'oks_riv', 'pass7'),
            (8, 'evg_sh', 'pass8'),
            (9, 'katya_p', 'pass9'),
            (10, 'sim_orl', 'pass10')
GO

insert into roles (id, name)
     values (1, 'ROLE_USER'),
            (2, 'ROLE_ADMIN')
GO

insert into user_principal_roles (my_user_principal_id, roles_id)
     values (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 2)
GO

insert into user_wall (id)
     values (1), (2), (3), (4), (5), (6), (7), (8), (9), (10)
GO

insert into users (users_id, first_name, last_name, birth_date, personal_info, gender, country, city, user_principal_id, user_wall_id)
     values (1, 'Michael', 'Astakhov', '1993-04-27', 'some info about me', 'male', 'Russia', 'Moscow', 1, 1),
            (2, 'Daria', 'Alimova', '1996-06-13', 'some more info about me', 'female', 'Russia', 'Moscow', 2, 2),
            (3, 'Sergey', 'Lapeev', '1988-11-17', 'some more info about me', 'male', 'Russia', 'St. Petersburg', 3, 3),
            (5, 'Irina', 'Klyaer', '2000-04-23', 'some more info about me', 'female', 'Ukraine', 'Kiev', 5, 5),
            (6, 'Ivan', 'Gomez', '1998-05-05', 'fireman', 'male', 'Belarus', 'Grodno', 6, 6),
            (9, 'Katya', 'Perez', '1999-01-11', 'love cats', 'female', 'Russia', 'Kursk', 9, 9)
GO

insert into users (users_id, first_name, last_name, birth_date, personal_info, gender, user_principal_id, user_wall_id)
     values (4, 'Elena', 'Dorofeeva', '1985-07-15', 'info', 'undefined', 4, 4)
GO

insert into users (users_id, first_name, last_name, birth_date, gender, country, city, user_principal_id, user_wall_id)
     values (7, 'Oksana', 'Riverova', '1997-05-05', 'female', 'Belarus', 'Minsk', 7, 7)
GO

insert into users (users_id, first_name, last_name, birth_date, gender, country, city, user_principal_id, user_wall_id)
     values (8, 'Evgeniy', 'Shateev', '1991-02-04', 'male', 'Russia', 'Belgorod', 8, 8)
GO

insert into users (users_id, first_name, last_name, birth_date, gender, user_principal_id, user_wall_id)
     values (10, 'Simeon', 'Orlov', '2001-09-19', 'undefined', 10, 10)
GO

insert into users_friends (users_id, friends_id)
     values (10, 1), (1, 10), (10, 2), (2, 10), (10, 3), (3, 10), (10, 4), (4, 10), (10, 5), (5, 10),
            (10, 6), (6, 10), (10, 7), (7, 10), (10, 8), (8, 10), (10, 9), (9, 10),
            (1, 2), (2, 1), (2, 3), (3, 2), (1, 3), (3, 1), (1, 4), (4, 1), (7, 8), (8, 7)
GO

insert into user_wall_message (id, content, send_date, user_wall_id)
     values (1, 'user 1, wall message 1', '2020-11-25 10:37:45', 1),
            (2, 'user 1, wall message 2', '2020-11-25 18:39:02', 1),
            (3, 'user 1, wall message 3', '2020-11-27 15:12:35', 1),
            (4, 'user 2, wall message 1', '2020-11-26 13:21:42', 2),
            (5, 'user 3, wall message 1', '2020-11-24 07:56:14', 3)
GO

insert into group_wall (id)
     values (1), (2), (3)
GO

insert into groupes (id, title, group_wall_id)
     values (1, 'Kinomania', 1),
            (2, 'Cherniy umor', 2),
            (3, 'Ostroumnie', 3)
GO

insert into users_groups (users_users_id, groups_id)
     values (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1),
            (3, 2), (6, 2), (7, 2), (8, 2), (9, 2),
            (5, 3), (10, 3)
GO

insert into users_admin_in_groups (admins_users_id, admin_in_groups_id)
     values (1, 1), (2, 1),
            (3, 2),
            (5, 3)
GO

insert into group_wall_message (id, content, send_date, group_wall_id)
     values (1, 'group 1, wall message 1', '2020-10-17 10:37:45', 1),
            (2, 'group 1, wall message 2', '2020-10-18 18:39:02', 1),
            (3, 'group 1, wall message 3', '2020-11-27 15:12:35', 1),
            (4, 'group 2, wall message 1', '2020-11-26 13:21:42', 2),
            (5, 'group 3, wall message 1', '2020-11-24 07:56:14', 3)
GO

insert into private_message (id, content, send_date, sender_id, receiver_id, is_read)
     values (1, 'user 1 to user 2, message 1', '2020-10-17 10:37:45', 1, 2, 1),
            (2, 'user 1 to user 2, message 2', '2020-10-18 18:39:02', 1, 2, 1),
            (3, 'user 2 to user 1, message 1', '2020-11-27 15:12:35', 2, 1, 0),
            (4, 'user 1 to user 2, message 4', '2020-11-26 13:21:42', 1, 2, 0),
            (5, 'user 1 to user 2, message 3', '2020-11-24 07:56:14', 1, 2, 1)
GO