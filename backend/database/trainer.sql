DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_profile;
DROP TABLE IF EXISTS trainer_profile;
DROP TABLE IF EXISTS client_list;
DROP TABLE IF EXISTS private_message;

BEGIN TRANSACTION;

CREATE TABLE users
(
    user_id serial,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    username varchar(25) NOT NULL UNIQUE,
    password varchar(1000) NOT NULL,
    salt varchar(256) NOT NULL,
    role varchar(10) NOT NULL,
        
    constraint pk_trainer primary key (user_id),
    constraint chk_role CHECK (role IN ('Trainer', 'Client'))
);

CREATE TABLE user_profile
(
    user_id int NOT NULL UNIQUE,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    is_public boolean NOT NULL DEFAULT false,
    role varchar(10) NOT NULL,
    city varchar(30),
    state varchar(2),

    constraint pk_client_profile primary key (user_id),
    constraint fk_client_profile foreign key (user_id) references users (user_id)

);

CREATE TABLE trainer_profile
(
    user_id int NOT NULL UNIQUE,
    hourly_rate int,
    rating decimal(3,2),
    philosphy varchar(50),
    bio_info varchar(250),
    certifications varchar(250),

    constraint pk_trainer_profile primary key (user_id),
    constraint fk_trainer_profile foreign key (user_id) references users (user_id)

);

CREATE TABLE client_list 
(
    trainer_id int NOT NULL,
    client_id int NOT NULL,
    privateNotes text[],

    constraint pk_client_list primary key (trainer_id, client_id),
    constraint fk_client_list_trainer foreign key (trainer_id) references users (user_id),
    constraint fk_client_list_client foreign key (client_id) references  users (user_id)

);
CREATE TABLE private_message
(
    sender_id int NOT NULL,
    recipient_id int NOT NULL,
    date_sent timestamp NOT NULL,
    unread  boolean NOT NULL DEFAULT true,
    subject varchar(20),
    message varchar(250),

    constraint pk_private_message primary key (sender_id, recipient_id, date_sent),
    constraint fk_private_message_sender foreign key (sender_id) references users (user_id),
    constraint fk_private_message_recipient foreign key (recipient_id) references  users (user_id)
);




COMMIT TRANSACTION;