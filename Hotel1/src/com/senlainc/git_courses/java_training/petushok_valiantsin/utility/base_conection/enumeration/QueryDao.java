package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration;

public enum QueryDao {
    ATTENDANCE {
        public String getQuery(QueryType type) {
            switch (type) {
                case CREATE:
                    return QueryConst.ATTENDANCE_CREATE;
                case DELETE:
                    return QueryConst.ATTENDANCE_DELETE;
                case UPDATE:
                    return QueryConst.ATTENDANCE_UPDATE;
                case READ_ALL:
                    return QueryConst.ATTENDANCE_READ_ALL;
                case READ:
                    return QueryConst.ATTENDANCE_READ;
                default:
                    return "";
            }
        }
    },
    GUEST {
        public String getQuery(QueryType type) {
            switch (type) {
                case CREATE:
                    return QueryConst.GUEST_CREATE;
                case DELETE:
                    return QueryConst.GUEST_DELETE;
                case UPDATE:
                    return QueryConst.GUEST_UPDATE;
                case READ_ALL:
                    return QueryConst.GUEST_READ_ALL;
                case READ:
                    return QueryConst.GUEST_READ;
                case SIZE:
                    return QueryConst.GUEST_SIZE;
                default:
                    return "";
            }
        }
    },
    ROOM {
        public String getQuery(QueryType type) {
            switch (type) {
                case CREATE:
                    return QueryConst.ROOM_CREATE;
                case DELETE:
                    return QueryConst.ROOM_DELETE;
                case UPDATE:
                    return QueryConst.ROOM_UPDATE;
                case READ_ALL:
                    return QueryConst.ROOM_READ_ALL;
                case READ_ALL_FREE:
                    return QueryConst.ROOM_READ_ALL_FREE;
                case READ:
                    return QueryConst.ROOM_READ;
                case READ_BY_NUMBER:
                    return QueryConst.ROOM_READ_BY_NUMBER;
                case FREE_SIZE:
                    return QueryConst.ROOM_FREE_SIZE;
                default:
                    return "";
            }
        }
    },
    ORDER {
        public String getQuery(QueryType type) {
            switch (type) {
                case CREATE:
                    return QueryConst.ORDER_CREATE;
                case DELETE:
                    return QueryConst.ORDER_DELETE;
                case UPDATE:
                    return QueryConst.ORDER_UPDATE;
                case READ_ALL:
                    return QueryConst.ORDER_READ_ALL;
                case READ:
                    return QueryConst.ORDER_READ;
                case READ_ORDER_ATTENDANCE:
                    return QueryConst.READ_ORDER_ATTENDANCE;
                case ADD_ORDER_ATTENDANCE:
                    return QueryConst.ADD_ORDER_ATTENDANCE;
                default:
                    return "";
            }
        }
    };

    public abstract String getQuery(QueryType type);
}
