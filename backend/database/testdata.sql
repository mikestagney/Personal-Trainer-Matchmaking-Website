BEGIN TRANSACTION;   

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Lance', 'Armstrong', 'LanceArm', 'roidcheater', 'salt1', 'Trainer');

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Paige', 'Drees', 'paigeIsATrainerNow', 'paigesPassword', 'salt2', 'Trainer');

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('David', 'P', 'pDog', 'davidsPassword', 'salt3', 'Trainer');

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Terry', 'Reily', 'TerryR1', 'catLuvnum1', 'salt3', 'Client');

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Quinn', 'Hebert', 'Quinn4daWin', 'password1', 'salt4', 'Client');

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Mike', 'Stagney', 'MikeTHEman', 'mikesPassword', 'salt5', 'Client');

INSERT INTO users(first_name, last_name, username, password, salt, role)
VALUES ('Bernard', 'M', 'bernardM', 'bernardsPassword', 'salt6', 'Client');


INSERT INTO user_profile(user_id, is_public, first_name, last_name, city, state, role)
VALUES((SELECT user_id FROM users WHERE username = 'LanceArm'), 'true', 'Lance', 'Armstrong', 'Austin', 'TX', 'Trainer');

INSERT INTO trainer_profile(user_id, hourly_rate, rating, philosphy, bio_info, certifications)
VALUES((SELECT user_id FROM users WHERE username = 'LanceArm'), '100', '3', 'I like to bike', 'former Tour De France winner', 'redacted');

INSERT INTO user_profile(user_id, is_public, first_name, last_name, city, state, role)
VALUES((SELECT user_id FROM users WHERE username = 'paigeIsATrainerNow'), 'true', 'Paige', 'Drees', 'Covington', 'KY', 'Trainer');

INSERT INTO trainer_profile(user_id, hourly_rate, rating, philosphy, bio_info, certifications)
VALUES((SELECT user_id FROM users WHERE username = 'paigeIsATrainerNow'), '50', '5', 'Time to get in shape', 'Got workout plans for DAYS', 'Born Ready');

INSERT INTO user_profile(user_id, is_public, first_name, last_name, city, state, role)
VALUES((SELECT user_id FROM users WHERE username = 'pDog'), 'false', 'David', 'P', 'Ohio Town', 'OH', 'Trainer');

INSERT INTO trainer_profile(user_id, hourly_rate, rating, philosphy, bio_info, certifications)
VALUES((SELECT user_id FROM users WHERE username = 'pDog'), '1', '1', 'I am not a good personal trainer', 'Lets play super smash', 'Coding Champion');


INSERT INTO user_profile(user_id, is_public, first_name, last_name, city, state, role)
VALUES((SELECT user_id FROM users WHERE username = 'TerryR1'), 'true', 'Terry', 'Reily', 'Cincinnati', 'OH', 'Client');

INSERT INTO user_profile(user_id, is_public, first_name, last_name, city, state, role)
VALUES((SELECT user_id FROM users WHERE username = 'Quinn4daWin'), 'true', 'Quinn', 'Hebert', 'Columbus', 'OH', 'Client');

INSERT INTO user_profile(user_id, is_public, first_name, last_name, city, state, role)
VALUES((SELECT user_id FROM users WHERE username = 'MikeTHEman'), 'true', 'Mike', 'Stagney', 'Cincinnati', 'OH', 'Client');

INSERT INTO user_profile(user_id, is_public, first_name, last_name, city, state, role)
VALUES((SELECT user_id FROM users WHERE username = 'bernardM'), 'false', 'Bernard', 'M', 'Cincinnati', 'OH', 'Client');


INSERT INTO client_list(trainer_id, client_id)
VALUES ((SELECT user_id FROM users WHERE username = 'LanceArm'), (SELECT user_id FROM users WHERE username = 'TerryR1'));

INSERT INTO client_list(trainer_id, client_id)
VALUES ((SELECT user_id FROM users WHERE username = 'paigeIsATrainerNow'), (SELECT user_id FROM users WHERE username = 'Quinn4daWin'));

INSERT INTO client_list(trainer_id, client_id)
VALUES ((SELECT user_id FROM users WHERE username = 'paigeIsATrainerNow'), (SELECT user_id FROM users WHERE username = 'MikeTHEman'));


COMMIT TRANSACTION;