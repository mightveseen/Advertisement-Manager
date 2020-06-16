DROP DATABASE IF EXISTS AdvertisementManager;
CREATE DATABASE IF NOT EXISTS AdvertisementManager;
USE AdvertisementManager;
CREATE TABLE IF NOT EXISTS `user_roles` (
`role_id` INT AUTO_INCREMENT PRIMARY KEY,
`role_description` VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS `user_creds` (
`user_id` INT AUTO_INCREMENT PRIMARY KEY,
`user_login` VARCHAR(50) NOT NULL,
`user_password` VARCHAR(120) NOT NULL,
`role_id` INT,
FOREIGN KEY (`role_id`) REFERENCES `user_roles`(`role_id`) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `users` (
`user_id` INT PRIMARY KEY,
`user_first_name` VARCHAR(50) NOT NULL, 
`user_last_name` VARCHAR(50) NOT NULL,
`user_email` VARCHAR(50) NOT NULL,
`user_phone` INT NOT NULL,
`user_registration_date` DATE NOT NULL,
`user_rating` FLOAT,
FOREIGN KEY (`user_id`) REFERENCES `user_creds`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `user_ratings` (
`rating_id` INT AUTO_INCREMENT PRIMARY KEY,
`user_owner_id` INT, 
`user_rated_id` INT, 
`rating_value` TINYINT,
FOREIGN KEY (`user_owner_id`) REFERENCES `users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`user_rated_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `advertisement_categories` (
`category_id` INT AUTO_INCREMENT PRIMARY KEY,
`category_description` VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS `states` (
`state_id` INT AUTO_INCREMENT PRIMARY KEY,
`state_description` VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS `advertisements`(
`advertisement_id` INT AUTO_INCREMENT PRIMARY KEY,
`advertisement_header` VARCHAR(50) NOT NULL,
`user_id` INT NOT NULL,
`advertisement_description` VARCHAR(500),
`category_id` INT,
`advertisement_date` DATE NOT NULL,
`advertisement_price` FLOAT NOT NULL,
`state_id` INT,
FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`category_id`) REFERENCES `advertisement_categories`(`category_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`state_id`) REFERENCES `states`(`state_id`) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `advertisement_photos` (
`photo_id` INT AUTO_INCREMENT PRIMARY KEY,
`advertisement_id` INT NOT NULL,
`photo_url` VARCHAR(260) NOT NULL,
FOREIGN KEY (`advertisement_id`) REFERENCES `advertisements`(`advertisement_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `advertisement_comments` (
`comment_id` INT AUTO_INCREMENT PRIMARY KEY,
`user_id` INT,
`advertisement_id` INT NOT NULL,
`comment_message` VARCHAR(500) NOT NULL,
`comment_date` DATETIME NOT NULL,
FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`advertisement_id`) REFERENCES `advertisements`(`advertisement_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `payment_types` (
`type_id` INT AUTO_INCREMENT PRIMARY KEY,
`type_description` VARCHAR(100) NOT NULL, 
`type_duration` INT NOT NULL,
`type_price` DOUBLE NOT NULL
);
CREATE TABLE IF NOT EXISTS `payments` (
`payment_id` INT AUTO_INCREMENT PRIMARY KEY,
`advertisement_id` INT NOT NULL,
`type_id` INT,
`payment_start_date` DATE NOT NULL,
`payment_end_date` DATE NOT NULL,
`payment_price` DOUBLE NOT NULL,
`state_id` INT,
FOREIGN KEY (`advertisement_id`) REFERENCES `advertisements`(`advertisement_id`) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`type_id`) REFERENCES `payment_types`(`type_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`state_id`) REFERENCES `states`(`state_id`) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `chats` (
`chat_id` INT AUTO_INCREMENT PRIMARY KEY,
`chat_name` VARCHAR(50) NOT NULL,
`chat_last_message` VARCHAR(100) NOT NULL,
`chat_update_date` DATETIME NOT NULL
);
CREATE TABLE IF NOT EXISTS `user_chats` (
`chat_id` INT,
`chat_user` INT,
FOREIGN KEY (`chat_id`) REFERENCES `chats`(`chat_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`chat_user`) REFERENCES `users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `messages` (
`message_id` INT AUTO_INCREMENT PRIMARY KEY,
`user_id` INT,
`chat_id` INT NOT NULL, 
`message_text` VARCHAR(500) NOT NULL,
`message_date` DATETIME NOT NULL,
FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`chat_id`) REFERENCES `chats`(`chat_id`) ON DELETE CASCADE ON UPDATE CASCADE
);


 