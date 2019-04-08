BEGIN TRANSACTION;   

INSERT INTO trainer (first_name, last_name, username, password, salt)
VALUES ('Lance', 'Armstrong', 'LanceArm', 'roidcheater', 'salt1');

INSERT INTO client(first_name, last_name, username, password, salt)
VALUES ('Terry', 'Reily', 'TerryR1', 'catLuvnum1', 'salt2');

INSERT INTO trainer_profile(trainerId, is_public, price_per_hour, rating, philosphy, bio, city, state, certifications)
VALUES((SELECT trainerId FROM trainer WHERE username = 'LanceArm'), 'false', '100', '3', 'I like to bike', 'former Tour De France winner', 'Austin', 'TX', 'retacted');

INSERT INTO client_list(trainerId, clientId)
VALUES ((SELECT trainerId FROM trainer WHERE username = 'LanceArm'), (SELECT clientId FROM client WHERE username = 'TerryR1'));

COMMIT TRANSACTION;