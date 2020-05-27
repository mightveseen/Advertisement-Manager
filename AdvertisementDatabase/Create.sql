DROP DATABASE IF EXISTS AdvertisementManager;
CREATE DATABASE IF NOT EXISTS AdvertisementManager;
USE AdvertisementManager;
CREATE TABLE IF NOT EXISTS `User_data` (
`user_id` INT AUTO_INCREMENT PRIMARY KEY,
`user_login` VARCHAR(50) NOT NULL,
`user_password` VARCHAR(120) NOT NULL
);
CREATE TABLE IF NOT EXISTS `Users` (
`user_id` INT PRIMARY KEY,
`user_first_name` VARCHAR(50) NOT NULL, 
`user_last_name` VARCHAR(50) NOT NULL,
`user_email` VARCHAR(50) NOT NULL,
`user_phone` INT NOT NULL,
`user_registration_date` DATE NOT NULL,
`user_raiting` FLOAT NOT NULL,
`user_role` VARCHAR(40) NOT NULL,
FOREIGN KEY (`user_id`) REFERENCES `User_data`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `Advertisement_categories` (
`cathegory_id` INT AUTO_INCREMENT PRIMARY KEY,
`cathegory_description` VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS `Advertisement_states` (
`state_id` INT AUTO_INCREMENT PRIMARY KEY,
`state_description` VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS `Advertisements`(
`advertisement_id` INT AUTO_INCREMENT PRIMARY KEY,
`advertisement_header` VARCHAR(50) NOT NULL,
`advertisement_user` INT,
`advertisement_description` VARCHAR(500),
`advertisement_cathegory` INT,
`advertisement_date` DATE NOT NULL,
`advertisement_state` INT,
FOREIGN KEY (`advertisement_user`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`advertisement_cathegory`) REFERENCES `Advertisement_categories`(`cathegory_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`advertisement_state`) REFERENCES `Advertisement_states`(`state_id`) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `Advertisement_photos` (
`photo_id` INT AUTO_INCREMENT PRIMARY KEY,
`photo_advertisement` INT,
`photo_url` VARCHAR(260) NOT NULL,
FOREIGN KEY (`photo_advertisement`) REFERENCES `Advertisements`(`advertisement_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `Advertisement_comments` (
`comment_id` INT AUTO_INCREMENT PRIMARY KEY,
`comment_user` INT,
`comment_advertisement` INT,
`comment_message` VARCHAR(500) NOT NULL,
`comment_date` DATE NOT NULL,
FOREIGN KEY (`comment_user`) REFERENCES `Users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`comment_advertisement`) REFERENCES `Advertisements`(`advertisement_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `Payment_types` (
`payment_type_id` INT AUTO_INCREMENT PRIMARY KEY,
`payment_type_description` VARCHAR(100) NOT NULL, 
`payment_type_duration` INT NOT NULL,
`payment_type_price` DOUBLE NOT NULL
);
CREATE TABLE IF NOT EXISTS `Payment_states` (
`state_id` INT AUTO_INCREMENT PRIMARY KEY,
`state_description` VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS `Payments` (
`payment_id` INT AUTO_INCREMENT PRIMARY KEY,
`payment_advertisement` INT,
`payment_type` INT,
`payment_start_date` DATE NOT NULL,
`payment_end_date` DATE NOT NULL,
`payment_price` DOUBLE NOT NULL,
`payment_state` INT,
FOREIGN KEY (`payment_advertisement`) REFERENCES `Advertisements`(`advertisement_id`) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`payment_type`) REFERENCES `Payment_types`(`payment_type_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`payment_state`) REFERENCES `Payment_states`(`state_id`) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `Chats` (
`chat_id` INT AUTO_INCREMENT PRIMARY KEY,
`chat_name` VARCHAR(50) NOT NULL,
`chat_top_message` VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS `Messages` (
`message_id` INT AUTO_INCREMENT PRIMARY KEY,
`message_user` INT,
`message_chat` INT, 
`message_text` VARCHAR(500) NOT NULL,
`message_date` DATE NOT NULL,
FOREIGN KEY (`message_user`) REFERENCES `Users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`message_chat`) REFERENCES `Chats`(`chat_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS `Chat_users` (
`chat_id` INT,
`chat_user` INT,
FOREIGN KEY (`chat_user`) REFERENCES `Users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (`chat_id`) REFERENCES `Chats`(`chat_id`) ON DELETE SET NULL ON UPDATE CASCADE
);


 