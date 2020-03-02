CREATE DATABASE IF NOT EXISTS HotelBase;
USE HotelBase;
CREATE TABLE IF NOT EXISTS `Attendance` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    `section` VARCHAR(50),
    `price` DOUBLE NOT NULL
);
CREATE TABLE IF NOT EXISTS `Guest` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50),
    `second_name` VARCHAR(50),
    `birthday` DATE,
    `info_contact` VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS `Room` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `number` INT,
    `classification` VARCHAR(50),
    `room_number` SMALLINT,
    `capacity` SMALLINT,
    `status` VARCHAR(20),
    `price` DOUBLE NOT NULL
);
CREATE TABLE IF NOT EXISTS `Order` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_date` DATETIME,
    `guest_id` INT,
    `room_id` INT,
    `start_date` DATE,
    `end_date` DATE,
    `status` VARCHAR(20),
    `price` DOUBLE NOT NULL,
	FOREIGN KEY (`room_id`) REFERENCES `Room`(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`guest_id`) REFERENCES `Guest`(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS `OrderAttendance` (
	`order_id` INT,
    `attendance_id` INT,
	FOREIGN KEY (`attendance_id`) REFERENCES `Attendance`(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`order_id`) REFERENCES `Order`(`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

	