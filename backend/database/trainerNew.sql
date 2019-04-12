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
INSERT INTO users (user_id,first_name,last_name,username,password,salt,role) VALUES
 (1,'Waylon','O''Hara','Kari78','pKkjz5CLGGIh4ND','fOzycrWX4cXAGGV','Client')
,(2,'Elise','Mayert','Lewis.Paucek','UAs6zyKsgghmcZw','cmvaE2gYpCkM59d','Client')
,(3,'Zella','Hilpert','Arvel.Ruecker','AjQc1h8S3cPyKTm','zPa43KHWCeNl9yi','Client')
,(4,'Darius','Thompson','Ebba.Mueller47','1RQFACvS2fLeeup','GCXIVxeMYZVfxZn','Client')
,(5,'Cristina','Russel','Bradford.Ziemann','09X7f1tKIgbIND7','rMirr4sFnQ0bkAW','Client')
,(6,'Floy','Shanahan','Daniela_Graham37','ttuMvaHBZ0HWVCX','fPpyI1PLJaFOvSQ','Client')
,(7,'Ellsworth','Rutherford','Maximillian.Schamberger','3aTCI28burpURRh','PfvkPqKIwMN1Wwd','Client')
,(8,'Eloise','Skiles','Catherine.Moore','Skeor05X3nJBcwB','LwkU4KSFfhDRc4E','Client')
,(9,'Wilhelm','Effertz','Skyla_Rowe46','oU_PkXeGP_A4q9x','T_EVPauG96wGJsD','Client')
,(10,'Bridgette','Wisozk','Jerrod55','AidVtlC0bclToof','mM6i_tySQ4xMWe_','Client')
,(11,'Lesley','Harris','Carter29','kkQUtIpwSg6uqQA','vt_ZpYEQ06eZ2KR','Client')
,(12,'Dayna','Rohan','Sheila67','EGli40Llqzw_HE8','fbDL72CvOFoffcF','Client')
,(13,'Morton','Schaefer','Obie_Rath','qW6ZQRlKxFlh7Sx','mg3CjDywPaWJFQ_','Client')
,(14,'Amira','Sawayn','Jenifer.Bernier','MtYx92ZZ7vsodi9','B5EfSZjGr3OKjLX','Client')
,(15,'Gino','Gutmann','Warren79','ICDkwylkzVqI2nx','5DeAEIONGxwVk8e','Client')
,(16,'Reilly','Mayer','Keegan_Maggio','Q3VEYXYJuwt7oWT','44EsZaKbLXv7kIp','Client')
,(17,'Sonny','Mohr','Bernadette_Gorczany','TI4e73cmara8rPb','7E1tC3bFiDkRajp','Client')
,(18,'Hayley','Anderson','Ellis_Brakus','hklek6OycU3upx3','S38vsXgq7IpbLN7','Client')
,(19,'Hailee','Glover','Aida78','9QC6mQdcGruBUOq','nhdMcQNuoH5rRHa','Client')
,(20,'Conner','Abshire','Lessie.McKenzie98','mmBLag7Dt9ovgqb','1i68Ns09XorOkrK','Client')
,(21,'Isaias','Medhurst','Brendon_Hegmann','fDWN7L_aNElDWlB','pcggOUXY36ScmdZ','Client')
,(22,'Christine','Tillman','Unique_Davis','4oBQoYM7mceodjI','kDhbkGpIowia4n9','Client')
,(23,'Dandre','Romaguera','Monserrate.Yost81','d7ZDXioVEs7TsxT','UehUbtLP2dGdzH_','Client')
,(24,'Gayle','Dare','Emerson_Labadie','EtPDoxbFcaQyUZ8','JtfZoOPeo3zWxEP','Client')
,(25,'Ezra','Zemlak','Irving.Pouros','39ZRF8UP0f4iX8E','8Lin5ITYfmWgolI','Client')
,(26,'Maiya','Grady','Leonor.Sawayn38','nCqtVzqSSdq8uhv','G5EDeUR_OyGbMwt','Client')
,(27,'Jonatan','Feeney','Maybelle_Lang92','TUeJ0uWqaTRDi0Z','29JqvAZsSnCbQMI','Client')
,(28,'Jalon','Robel','Felicita1','ExDfSHfnWFxxJuY','PZuWr5xdzq9aoQt','Client')
,(29,'Einar','Herzog','Rusty.Cormier18','8bF1iWNjqJg9ICL','AXbu_Zx26UWc3Rf','Client')
,(30,'Casandra','Reichel','Elisabeth.Auer','oawIZfF9NvJhIGh','agQ9E6kyL1tIMau','Client')
,(31,'first','last','username','password','agQ9E6kyL1tIMau','Client');


INSERT INTO trainer (user_id,hourly_rate,rating,philosophy,bio,is_public) VALUES
 (1,805.40,1,'We need to quantify the solid state SSL protocol!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(2,773.36,5,'You can''t back up the firewall without synthesizing the 1080p XSS interface!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(3,158.47,2,'I''ll calculate the bluetooth AGP transmitter, that should sensor the FTP driver!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(4,743.06,4,'Try to transmit the IB firewall, maybe it will transmit the primary transmitter!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true)
,(5,354.70,2,'You can''t index the pixel without copying the optical ADP matrix!','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',true);
