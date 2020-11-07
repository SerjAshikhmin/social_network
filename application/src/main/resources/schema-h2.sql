CREATE TABLE users (
	users_id integer NOT NULL,
	birth_date date,
	first_name varchar(20),
	last_name varchar(20),
	password varchar(20),
	personal_info varchar(1000),
	user_name varchar(20),
	primary key (users_id)
);

CREATE TABLE users_friends (
	users_id integer NOT NULL,
	friends_id integer NOT NULL,
	PRIMARY KEY (users_id, friends_id),
	CONSTRAINT FK_USERS FOREIGN KEY (users_id) REFERENCES users (users_id),
	CONSTRAINT FK_FRIENDS FOREIGN KEY (friends_id) REFERENCES users (users_id)
);