DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS trainer_profile;
DROP TABLE IF EXISTS client_list;

BEGIN TRANSACTION;

CREATE TABLE users
(
    user_id serial,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    username varchar(25) NOT NULL UNIQUE,
    password varchar(20) NOT NULL,
    salt varchar(256) NOT NULL,
    role varchar(10) NOT NULL,
        
    constraint pk_trainer primary key (user_id),
    constraint chk_role CHECK (role IN ('Trainer', 'Client'))
);

CREATE TABLE trainer_profile
(
    user_id int NOT NULL UNIQUE,
    is_public boolean NOT NULL DEFAULT false,
    price_per_hour int NOT NULL,
    rating decimal(3,2),
    philosphy varchar(50),
    bio varchar(250),
    city varchar(30),
    state varchar(2),
    certifications varchar(250),

    constraint pk_trainer_profile primary key (user_id),
    constraint fk_trainer_profile foreign key (user_id) references users (user_id)

);

CREATE TABLE client_list 
(
    trainerId int NOT NULL,
    clientId int NOT NULL,

    constraint pk_client_list primary key (trainerId, clientId),
    constraint fk_client_list_trainer foreign key (trainerId) references users (user_id),
    constraint fk_client_list_client foreign key (clientId) references  users (user_id)

);
CREATE TABLE private_message
(
    trainerId int NOT NULL,
    clientId int NOT NULL,
    postDate timestamp NOT NULL,
    subject varchar(20),
    message varchar(250),

    constraint pk_client_list primary key (trainerId, clientId, postDate),
    constraint fk_client_list_trainer foreign key (trainerId) references users (user_id),
    constraint fk_client_list_client foreign key (clientId) references  users (user_id)
);




COMMIT TRANSACTION;