package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration;

public class QueryConst {
    /**
     * Attendance const query variables
     */
    protected static final String ATTENDANCE_CREATE = "INSERT INTO `Attendance`(`name`, `section`, `price`)"
            + "VALUES (?, ?, ?);";
    protected static final String ATTENDANCE_DELETE = "DELETE FROM `Attendance` WHERE `id` = ?;";
    protected static final String ATTENDANCE_UPDATE = "UPDATE `Attendance` SET `name` = ?, `section` = ?, `price` = ?"
            + "WHERE `id` = ?;";
    protected static final String ATTENDANCE_READ_ALL = "SELECT * FROM `Attendance`;";
    protected static final String ATTENDANCE_READ = "SELECT * FROM `Attendance` WHERE `id` = ?;";
    /**
     * Guest const query variables
     */
    protected static final String GUEST_CREATE = "INSERT INTO `Guest`(`first_name`, `second_name`, `birthday`)"
            + "VALUES (?, ?, ?);";
    protected static final String GUEST_DELETE = "DELETE FROM `Guest` WHERE `id` = ?;";
    protected static final String GUEST_UPDATE = "UPDATE `Guest` SET `first_name` = ?, `second_name` = ?, `birthday` = ?"
            + "WHERE `id` = ?;";
    protected static final String GUEST_READ_ALL = "SELECT * FROM `Guest`;";
    protected static final String GUEST_READ = "SELECT * FROM `Guest` WHERE `id` = ?;";
    protected static final String GUEST_SIZE = "SELECT COUNT(`id`) FROM `Guest`;";
    /**
     * Room const query variables
     */
    protected static final String ROOM_CREATE = "INSERT INTO `Room`(`number`, `classification`, `room_number`, `capacity`, `status`, `price`)"
            + "VALUES (?, ?, ?, ?, ?, ?);";
    protected static final String ROOM_DELETE = "DELETE FROM `Room` WHERE `id` = ?;";
    protected static final String ROOM_UPDATE = "UPDATE `Room` SET `number` = ?, `classification` = ?, `room_number` = ?, `capacity` = ?, `status` = ?, `price` = ?"
            + "WHERE `id` = ?";
    protected static final String ROOM_READ_ALL = "SELECT * FROM `Room`;";
    protected static final String ROOM_READ_ALL_FREE = "SELECT * FROM `Room` WHERE `status` = 'FREE';";
    protected static final String ROOM_READ = "SELECT * FROM `Room` WHERE `id` = ?;";
    protected static final String ROOM_READ_BY_NUMBER = "SELECT * FROM `Room` WHERE `number` = ?;";
    protected static final String ROOM_FREE_SIZE = "SELECT COUNT(`id`) FROM `Room` WHERE `status` = 'FREE';";
    /**
     * Order const query variables
     */
    protected static final String ORDER_CREATE = "INSERT INTO `Order`(`order_date`, `guest_id`, `room_id`, `start_date`, `end_date`, `status`, `price`)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
    protected static final String ORDER_ADD_ATTENDANCE = "INSERT INTO `OrderAttendance`(`order_id`, `attendance_id`)" +
            "VALUES (?, ?);";
    protected static final String ORDER_DELETE = "UPDATE `Order` SET `status` = 'DISABLED' WHERE `id` = ?;";
    protected static final String ORDER_UPDATE = "UPDATE `Order` SET `guest_id` = ?, `room_id` = ?, `end_date` = ?, `status` = ?, `price` = ?"
            + "WHERE `id` = ?;";
    protected static final String ORDER_READ_ALL = "SELECT * FROM `Order`"
            + "INNER JOIN `Room` ON `Room`.`id` = `Order`.`room_id`"
            + "INNER JOIN `Guest` ON `Guest`.`id` = `Order`.`guest_id`;";
    protected static final String ORDER_READ = "SELECT * FROM `Order`"
            + "INNER JOIN `Room` ON `Room`.`id` = `Order`.`room_id`"
            + "INNER JOIN `Guest` ON `Guest`.`id` = `Order`.`guest_id`"
            + "WHERE `Order`.`id` = ?;";
    protected static final String ORDER_READ_ATTENDANCE = "SELECT `Attendance`.`id`, `Attendance`.`name`, `Attendance`.`section`, `Attendance`.`price` FROM `Attendance`"
            + "INNER JOIN `OrderAttendance` ON `OrderAttendance`.`attendance_id` = `Attendance`.`id`"
            + "WHERE `OrderAttendance`.`order_id` = ?;";
    protected static final String ORDER_READ_LAST_ROOM = "SELECT `Room`.`id`, `number`, `classification`, `room_number`, `capacity`, `Room`.`status`, `Room`.`price`, `Order`.`order_date`"
            + "FROM `Room` INNER JOIN `Order` ON `Order`.`room_id` = `Room`.`id` WHERE `Order`.`guest_id` = ? ORDER BY `Order`.`order_date` DESC LIMIT 3;";
    protected static final String ORDER_READ_AFTER_DATE = "SELECT `Room`.`id`, `number`, `classification`, `room_number`, `capacity`, `Room`.`status`, `Room`.`price`" +
            "FROM `Room` INNER JOIN `Order` ON `Order`.`room_id` = `Room`.`id`" +
            "WHERE `Order`.`status` = 'ACTIVE' AND `order`.`end_date` < ? GROUP BY `Room`.`id`;";

    private QueryConst() {
        throw new IllegalStateException("Utility class");
    }
}
