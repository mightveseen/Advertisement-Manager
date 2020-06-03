USE AdvertisementManager;
INSERT INTO `user_roles`(`role_description`)
VALUES
('ADMIN'),
('MODERATOR'),
('COMMON');
INSERT INTO `user_data`(`user_login`, `user_password`, `role_id`)
VALUES
('admin', 'admin', 1),
('moderator', 'moderator', 2),
('common', 'common', 3),
('common2', 'common2', 3);
INSERT INTO `users`(`user_id`, `user_first_name`, `user_last_name`, `user_email`, `user_phone`, `user_registration_date`, `user_rating`)
VALUES
(2, 'Caren', 'Johnson', 'caren18@gmail.com', 292350943, '2017-04-02', 9.0),
(1, 'Daniel', 'Dark', 'danieldark@gmail.com', 298674592, '2012-12-12', 5.0),
(3, 'Vasilij', 'Bochkov', 'bochok@gmail.com', 339922111, '2019-09-19', 7.0),
(4, 'Robert', 'Downey Jr', 'stark@gmail.com', 010000001, '2019-09-19', 7.0);
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
('DRAFT'),
('MODERATION'),
('REJECTED'),
('ACTIVE'),
('DISABLED'),
('APPROVED'),
('PAID'),
('CANCELLED'),
('FINISHED');
INSERT INTO `advertisements`(`advertisement_header`, `user_id`, `advertisement_description`, `category_id`, `advertisement_date`, `state_id`)
VALUES
('Audi R8 2010', 4, 'Cool car (starring in Iron Man), but i bought new', 2, '2020-08-02', 1),
('Sony Z1 Ultra', 3, 'With this COVID-19 and 5G towers I decided buy Nokia 6300', 7, '2020-05-28', 4);