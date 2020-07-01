USE AdvertisementManager;
INSERT INTO `user_creds`(`user_username`, `user_password`, `user_role`)
VALUES
('admin', '$2a$12$krxeaT4hAui4ZJYZlK0D9e/rHb6fVehBghY63D1Q1iM//cwnYFOEe', 'ROLE_ADMIN'),
('moderator', '$2a$12$krxeaT4hAui4ZJYZlK0D9e/rHb6fVehBghY63D1Q1iM//cwnYFOEe', 'ROLE_MODERATOR'),
('common', '$2a$12$krxeaT4hAui4ZJYZlK0D9e/rHb6fVehBghY63D1Q1iM//cwnYFOEe', 'ROLE_COMMON'),
('common2', '$2a$12$krxeaT4hAui4ZJYZlK0D9e/rHb6fVehBghY63D1Q1iM//cwnYFOEe', 'ROLE_COMMON');
INSERT INTO `users`(`user_id`, `user_first_name`, `user_last_name`, `user_email`, `user_phone`, `user_registration_date`, `user_rating`)
VALUES
(2, 'Caren', 'Johnson', 'caren18@gmail.com', 292350943, '2017-04-02', 5),
(1, 'Daniel', 'Dark', 'danieldark@gmail.com', 298674592, '2012-12-12', 5),
(3, 'Vasilij', 'Bochkov', 'bochok@gmail.com', 339922111, '2019-09-19', 7),
(4, 'Robert', 'Downey Jr', 'stark@gmail.com', 010000001, '2019-09-19', 5);
INSERT INTO `advertisement_categories`(`category_description`)
VALUES
('Household appliances'),
('Motors'),
('Computers'),
("Women's clothing"),
("Men's clothing"),
('Home'),
('Cell phones and tablets');
INSERT INTO `states`(`state_description`)
VALUES
('MODERATION'),
('REJECTED'),
('ACTIVE'),
('DISABLED'),
('APPROVED'),
('FINISHED');
INSERT INTO `advertisements`(`advertisement_header`, `user_id`, `advertisement_description`, `category_id`, `advertisement_date`, `advertisement_price`,`state_id`)
VALUES
('Audi R8 2010', 4, 'Cool car (starring in Iron Man), but i bought new', 2, '2020-06-02', 230.5, 3),
('Sony Z1 Ultra', 3, 'With this COVID-19 and 5G towers I decided buy Nokia 6300', 7, '2020-05-28', 470.0, 3);
INSERT INTO `advertisement_comments`(`user_id`, `advertisement_id`, `comment_message`, `comment_date`)
VALUES
(4, 1, 'The best car ever made', '2020-06-04'),
(4, 2, 'What trash did you said', '2020-06-03');
INSERT INTO `payment_types`(`type_description`, `type_duration`, `type_price`)
VALUES 
('15 days', 15, 3),
('30 days', 30, 6),
('90 days', 90, 12),
('180 days', 180, 25);
INSERT INTO `payments`(`user_id`, `advertisement_id`, `type_id`, `payment_start_date`, `payment_end_date`, `payment_price`, `state_id`)
VALUES
(3, 2, 2, '2020-06-04', '2020-07-04', 6, 5);
INSERT INTO `chats`(`chat_name`, `chat_last_message`, `chat_update_date`)
VALUES 
('Sony Z1 Ultra', 'Hi', '2020-02-12+16:02:33'),
('Audi R8', 'Hi', '2020-04-12+09:12:33');
INSERT INTO `user_chats`(`chat_id`, `chat_user`)
VALUES
(1, 3),
(1, 4),
(2, 3),
(2, 4);
INSERT INTO `messages`(`user_id`, `chat_id`, `message_text`, `message_date`)
VALUES
(3, 1, 'trash loto', '2020-02-12+16:02:33'),
(4, 1, 'lol', '2020-02-12+16:02:33'),
(3, 1, 'not bad', '2020-02-12+16:02:33'),
(4, 1, 'see you', '2020-02-12+16:02:33');