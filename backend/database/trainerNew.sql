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
    philosophy varchar(250),
    bio varchar(1000),
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
    sender_delete boolean NOT NULL DEFAULT false,
    recipient_delete boolean NOT NULL DEFAULT false,

    constraint pk_private_message primary key (message_id, sender_id, recipient_id, post_date),
    constraint fk_private_message_sender foreign key (sender_id) references users (user_id),
    constraint fk_private_message_recipient foreign key (recipient_id) references  users (user_id)
);

COMMIT TRANSACTION;




-- user data
INSERT INTO users (user_id,first_name,last_name,username,password,salt,role,city,state) VALUES
 (1,'Waylon','O''Hara','Kari78','pKkjz5CLGGIh4ND','fOzycrWX4cXAGGV','Client','Cincinnati','OH')
,(2,'Elise','Mayert','Lewis.Paucek','UAs6zyKsgghmcZw','cmvaE2gYpCkM59d','Client','Cincinnati','OH')
,(3,'Zella','Hilpert','Arvel.Ruecker','AjQc1h8S3cPyKTm','zPa43KHWCeNl9yi','Client','Cincinnati','OH')
,(4,'Darius','Thompson','Ebba.Mueller47','1RQFACvS2fLeeup','GCXIVxeMYZVfxZn','Client','Cincinnati','OH')
,(5,'Cristina','Russel','Bradford.Ziemann','09X7f1tKIgbIND7','rMirr4sFnQ0bkAW','Client','Cincinnati','OH')
,(6,'Floy','Shanahan','Daniela_Graham37','ttuMvaHBZ0HWVCX','fPpyI1PLJaFOvSQ','Client','Cincinnati','OH')
,(7,'Ellsworth','Rutherford','Maximillian.Schamberger','3aTCI28burpURRh','PfvkPqKIwMN1Wwd','Client','Cincinnati','OH')
,(8,'Eloise','Skiles','Catherine.Moore','Skeor05X3nJBcwB','LwkU4KSFfhDRc4E','Client','Cincinnati','OH')
,(9,'Wilhelm','Effertz','Skyla_Rowe46','oU_PkXeGP_A4q9x','T_EVPauG96wGJsD','Client','Cincinnati','OH')
,(10,'Bridgette','Wisozk','Jerrod55','AidVtlC0bclToof','mM6i_tySQ4xMWe_','Client','Cincinnati','OH')
,(11,'Lesley','Harris','Carter29','kkQUtIpwSg6uqQA','vt_ZpYEQ06eZ2KR','Client','Cincinnati','OH')
,(12,'Dayna','Rohan','Sheila67','EGli40Llqzw_HE8','fbDL72CvOFoffcF','Client','Cincinnati','OH')
,(13,'Morton','Schaefer','Obie_Rath','qW6ZQRlKxFlh7Sx','mg3CjDywPaWJFQ_','Client','Cincinnati','OH')
,(14,'Amira','Sawayn','Jenifer.Bernier','MtYx92ZZ7vsodi9','B5EfSZjGr3OKjLX','Client','Cincinnati','OH')
,(15,'Gino','Gutmann','Warren79','ICDkwylkzVqI2nx','5DeAEIONGxwVk8e','Client','Cincinnati','OH')
,(16,'Reilly','Mayer','Keegan_Maggio','Q3VEYXYJuwt7oWT','44EsZaKbLXv7kIp','Client','Cincinnati','OH')
,(17,'Sonny','Mohr','Bernadette_Gorczany','TI4e73cmara8rPb','7E1tC3bFiDkRajp','Client','Cincinnati','OH')
,(18,'Hayley','Anderson','Ellis_Brakus','hklek6OycU3upx3','S38vsXgq7IpbLN7','Client','Cincinnati','OH')
,(19,'Hailee','Glover','Aida78','9QC6mQdcGruBUOq','nhdMcQNuoH5rRHa','Client','Cincinnati','OH')
,(20,'Conner','Abshire','Lessie.McKenzie98','mmBLag7Dt9ovgqb','1i68Ns09XorOkrK','Client','Cincinnati','OH')
,(21,'Isaias','Medhurst','Brendon_Hegmann','fDWN7L_aNElDWlB','pcggOUXY36ScmdZ','Client','Cincinnati','OH')
,(22,'Christine','Tillman','Unique_Davis','4oBQoYM7mceodjI','kDhbkGpIowia4n9','Client','Cincinnati','OH')
,(23,'Dandre','Romaguera','Monserrate.Yost81','d7ZDXioVEs7TsxT','UehUbtLP2dGdzH_','Client','Cincinnati','OH')
,(24,'Gayle','Dare','Emerson_Labadie','EtPDoxbFcaQyUZ8','JtfZoOPeo3zWxEP','Client','Cincinnati','OH')
,(25,'Ezra','Zemlak','Irving.Pouros','39ZRF8UP0f4iX8E','8Lin5ITYfmWgolI','Client','Cincinnati','OH')
,(26,'Maiya','Grady','Leonor.Sawayn38','nCqtVzqSSdq8uhv','G5EDeUR_OyGbMwt','Client','Cincinnati','OH')
,(27,'Jonatan','Feeney','Maybelle_Lang92','TUeJ0uWqaTRDi0Z','29JqvAZsSnCbQMI','Client','Cincinnati','OH')
,(28,'Jalon','Robel','Felicita1','ExDfSHfnWFxxJuY','PZuWr5xdzq9aoQt','Client','Cincinnati','OH')
,(29,'Einar','Herzog','Rusty.Cormier18','8bF1iWNjqJg9ICL','AXbu_Zx26UWc3Rf','Client','Cincinnati','OH')
,(30,'Casandra','Reichel','Elisabeth.Auer','oawIZfF9NvJhIGh','agQ9E6kyL1tIMau','Client','Cincinnati','OH')
,(31,'first','last','username','password','agQ9E6kyL1tIMau','Client','Cincinnati','OH')
,(32,'Quinn','Hebert','traienr1','password','agQ9E6kyL1tIMau','Trainer','Cincinnati','OH')
,(33,'Paige','Drees','traienr2','password','agQ9E6kyL1tIMau','Trainer','Cincinnati','OH')
,(34,'Mide','Stagney','traienr3','password','agQ9E6kyL1tIMau','Trainer','Cincinnati','OH')
,(35,'TrainerFirstName','TrainerLastName','traienr4','password','agQ9E6kyL1tIMau','Trainer','Cincinnati','OH')
,(36,'TrainerFirstName','TrainerLastName','traienr5','password','agQ9E6kyL1tIMau','Trainer','Cincinnati','OH');



INSERT INTO trainer (user_id,hourly_rate,rating,philosophy,bio,is_public) VALUES
 (31,805.40,1,'We need to quantify the solid state SSL protocol!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(32,773.36,5,'You can''t back up the firewall without synthesizing the 1080p XSS interface!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(33,158.47,2,'I''ll calculate the bluetooth AGP transmitter, that should sensor the FTP driver!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(34,743.06,4,'Try to transmit the IB firewall, maybe it will transmit the primary transmitter!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(35,354.70,2,'You can''t index the pixel without copying the optical ADP matrix!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true);
