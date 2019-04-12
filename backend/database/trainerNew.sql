BEGIN TRANSACTION;

CREATE TABLE users
(
    user_id serial,
    username varchar(25) NOT NULL UNIQUE,
    role varchar(10) NOT NULL,
    password varchar(256) NOT NULL,
    salt varchar(256) NOT NULL,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    city varchar(30),
    state varchar(2),
        
    constraint pk_users primary key (user_id),
    constraint chk_role CHECK (role IN ('Trainer', 'Client'))
);

CREATE TABLE client
(
    user_id int NOT NULL UNIQUE,

    constraint pk_client primary key (user_id),
    constraint fk_client foreign key (user_id) references users (user_id)
);

CREATE TABLE trainer
(
    user_id int NOT NULL UNIQUE,
    hourly_rate int,
    rating decimal(3,2),
    is_public boolean NOT NULL DEFAULT false,
    philosophy varchar(50),
    bio varchar(250),
    certifications text[],

    constraint pk_trainer primary key (user_id),
    constraint fk_trainer foreign key (user_id) references users (user_id)
);

CREATE TABLE client_list 
(
    trainer_id int NOT NULL,
    client_id int NOT NULL,
    private_notes text[],

    constraint pk_client_list primary key (trainer_id, client_id),
    constraint fk_client_list_trainer foreign key (trainer_id) references users (user_id),
    constraint fk_client_list_client foreign key (client_id) references  users (user_id)
);



CREATE TABLE message
(
    message_id serial,
    sender_id int NOT NULL,
    recipient_id int NOT NULL,
    post_date timestamp NOT NULL,
    unread boolean NOT NULL DEFAULT true,
    subject varchar(20),
    message varchar(250),

    constraint pk_private_message primary key (message_id, sender_id, recipient_id, post_date),
    constraint fk_private_message_sender foreign key (sender_id) references users (user_id),
    constraint fk_private_message_recipient foreign key (recipient_id) references  users (user_id)
);

COMMIT TRANSACTION;