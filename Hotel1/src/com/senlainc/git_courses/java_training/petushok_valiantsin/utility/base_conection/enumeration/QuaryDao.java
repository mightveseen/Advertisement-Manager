package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;

public enum QuaryDao {
    ATTENDANCE {
        public String getQuary(QuaryType type) {
            switch (type) {
                case CREATE:
                    return "INSERT INTO `Attendance`(`name`, `section`, `price`)"
                            + "VALUES (?, ?, ?);";
                case DELETE:
                    return "DELETE FROM `Attendance` WHERE `id` = ?;";
                case UPDATE:
                    return "UPDATE `Attendance` SET `name` = ?, `section` = ?, `price` = ?"
                            + "WHERE `id` = ?;";
                case READ_ALL:
                    return "SELECT * FROM `Attendance`;";
                case READ:
                    return "SELECT * FROM `Attendance` WHERE `id` = ?;";
                default:
                    return "";
            }
        }
    },
    GUEST {
        public String getQuary(QuaryType type) {
            switch (type) {
                case CREATE:
                    return "INSERT INTO `Guest`(`first_name`, `second_name`, `birthday`, `info_contact`)"
                            + "VALUES (?, ?, ?, ?);";
                case DELETE:
                    return "DELETE FROM `Guest` WHERE `id` = ?;";
                case UPDATE:
                    return "UPDATE `Guest` SET `first_name` = ?, `second_name` = ?, `birthday` = ?, `info_contact` = ?"
                            + "WHERE `id` = ?;";
                case READ_ALL:
                    return "SELECT * FROM `Guest`;";
                case READ:
                    return "SELECT * FROM `Guest` WHERE `id` = ?;";
                default:
                    return "";
            }
        }
    },
    ROOM {
        public String getQuary(QuaryType type) {
            switch (type) {
                case CREATE:
                    return "INSERT INTO `Room`(`number`, `classification`, `room_number`, `capacity`, `status`, `price`)"
                            + "VALUES (?, ?, ?, ?, ?, ?);";
                case DELETE:
                    return "DELETE FROM `Room` WHERE `id` = ?;";
                case UPDATE:
                    return "UPDATE `Room` SET `number` = ?, `classification` = ?, `room_number` = ?, `capacity` = ?, `status` = ?, `price` = ?"
                            + "WHERE `id` = ?";
                case READ_ALL:
                    return "SELECT * FROM `Room`;";
                case READ:
                    return "SELECT * FROM `Room` WHERE `id` = ?;";
                default:
                    return "";
            }
        }
    },
    ORDER {
        public String getQuary(QuaryType type) {
            switch (type) {
                case CREATE:
                    return "INSERT INTO `Order`(`order_date`, `guest_id`, `room_id`, `start_date`, `end_date`, `status`, `price`)"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
                case DELETE:
                    return "UPDATE `Order` SET `status` = " + OrderStatus.DISABLED.name() + "WHERE `id` = ?;";
                case UPDATE:
                    return "UPDATE `Order` SET `order_date` = ?, `guest_id` = ?, `room_id` = ?, `start_date` = ?, `end_date` = ?, `status` = ?, `price` = ?"
                            + "WHERE `id` = ?;";
                case READ_ALL:
                    return "SELECT * FROM `Order`"
                            + "INNER JOIN `Room` ON `room`.`id` = `Order`.`room_id`"
                            + "INNER JOIN `Guest` ON `guest`.`id` = `Order`.`guest_id`;";
                case READ:
                    return "SELECT * FROM `Order`"
                            + "INNER JOIN `Room` ON `room`.`id` = `Order`.`room_id`"
                            + "INNER JOIN `Guest` ON `guest`.`id` = `Order`.`guest_id`"
                            + "WHERE `Order`.`id` = ?;";
                case READ_ORDER_ATTENDANCE:
                    return "SELECT `Attendance`.`id`, `Attendance`.`name`, `Attendance`.`section`, `Attendance`.`price` FROM `Attendance`" +
                            "INNER JOIN `OrderAttendance` ON `OrderAttendance`.`attendance_id` = `Attendance`.`id`" +
                            "WHERE `OrderAttendance`.`order_id` = ?;";
                default:
                    return "";
            }
        }
    };

    public abstract String getQuary(QuaryType type);
}
