BEGIN TRANSACTION;   

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Lance', 'Armstrong', 'LanceArm', 'roidcheater', 'salt1', 'Trainer');

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Terry', 'Reily', 'TerryR1', 'catLuvnum1', 'salt2', 'Client');

INSERT INTO trainer_profile(user_id, is_public, first_name, last_name, price_per_hour, rating, philosphy, bio, city, state, certifications)
VALUES((SELECT user_id FROM users WHERE username = 'LanceArm'), 'false', 'Lance', 'Armstrong', '100', '3', 'I like to bike', 'former Tour De France winner', 'Austin', 'TX', 'retacted');

INSERT INTO client_list(trainerId, clientId)
VALUES ((SELECT user_id FROM users WHERE username = 'LanceArm'), (SELECT user_id FROM users WHERE username = 'TerryR1'));

COMMIT TRANSACTION;