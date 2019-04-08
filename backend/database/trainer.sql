DROP TABLE IF EXISTS trainer;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS trainer_profile;
DROP TABLE IF EXISTS client_list;

BEGIN TRANSACTION

CREATE TABLE trainer
(
    trainerId serial,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    username varchar(25) NOT NULL UNIQUE,
    password varchar(20) NOT NULL,
    
    constraint pk_trainer primary key trainerId
);

CREATE TABLE client
(
    clientId serial,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    username varchar(25) NOT NULL UNIQUE,
    password varchar(20) NOT NULL,
    
    constraint pk_client primary key clientId,
    
);

CREATE TABLE trainer_profile
(
    trainerId int NOT NULL,
    is_public boolean NOT NULL,
    price_per_hour int NOT NULL,
    rating int,
    philosphy varchar(50),
    bio varchar(250),
    city varchar(30),
    state varchar(2),
    certifications varchar(250),

    constraint pk_trainer_profile primary key (trainerId),
    constraint fk_trainer_profile foreign key (trainerId) references trainer(trainerId)

)

CREATE TABLE client_list 
(
    trainerId int NOT NULL,
    clientId int NOT NULL

    constraint pk_client_list primary key (trainerId, clientId),
    constraint fk_client_list foreign key (trainerId) references trainer(trainerId),
    constraint fk_client_list foreign key (clientId) references client(clientId)

)



COMMIT TRANSACTION