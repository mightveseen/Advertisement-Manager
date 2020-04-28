package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementAlreadyExistsException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFormatMessage;

public class Hotel {

    private static final Logger LOGGER = LogManager.getLogger(Hotel.class);

    /**
     * Attendance
     */
    public void createAttendance() {
        try {
            LOGGER.info("Add attendance in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating attendance.", e);
        }
    }

    public void deleteAttendance() {
        try {
            LOGGER.info("Delete attendance with index: from database");
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting attendance.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Attendance with index: don't exists.", e);
        }
    }

    public void showAttendance() {
        LOGGER.warn("Error while read attendance's.");
    }

    public void changePriceAttendance() {
        try {
            LOGGER.info("Change attendance price");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating attendance. Update operation: change price.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Attendance with index don't exists.", e);
        }
    }

    /**
     * Room
     */
    public void createRoom() {
        try {
            LOGGER.info("Add room in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating room", e);
        } catch (ElementAlreadyExistsException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void deleteRoom() {
        try {
            LOGGER.info("Delete room with index: from database");
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting room", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index: don't exists.", e);
        }
    }

    public void changeStatusRoom() {
        try {
            LOGGER.info("Change room status");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating room. Update operation: change status.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index don't exists.", e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void sortRoom() {
        LOGGER.warn("Error while read room's.");
    }

    public void showRoom() {
        LOGGER.warn("Error while read room's.");
    }

    public void numFreeRoom() {
        LOGGER.info("Show umber of free room");
        LOGGER.warn("Error while read room's. Read operation: number of free room's.");
    }

    /**
     * Guest
     */
    public void createGuest() {
        try {
            LOGGER.info("Add guest in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating guest", e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void deleteGuest() {
        try {
            LOGGER.info("Delete guest with index: from database");
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting guest.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Guest with index: don't exists.", e);
        }
    }

    public void numGuest() {
        try {
            LOGGER.info("Show number of guest");
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read guest's. Read operation: number of guest's", e);
        }
    }

    public void showGuest() {
        LOGGER.warn("Error while read all guest's.");
    }

    /**
     * Order
     */
    public void createOrder() {
        try {
            LOGGER.info("Add order in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn(new MessageFormatMessage("Room with index {0} or Guest with index: {1} don't exists.",
                    1, 2), e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void deleteOrder(long orderIndex) {
        try {
            LOGGER.info("Delete order from database");
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Order with index {} don't exists.", orderIndex, e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void sortOrder() {
        LOGGER.warn("Error while read all order's.");
    }

    public void showOrder() {
        LOGGER.warn("Error while read all order's.");
    }

    public void showAfterDate() {
        try {
            LOGGER.info("Show room's will be available after:");
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's. Read operation: room's will be available after date.", e);
        }
    }

    public void showGuestRoom() {
        try {
            LOGGER.info("Show last 3 room's of guest");
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's. Read operation: room's of guest.", e);
        }
    }

    public void showOrderAttendance() {
        try {
            LOGGER.info("Show guest attendance's");
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read order. Read operation: show order attendance's.", e);
        } catch (ElementNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void addOrderAttendance(long orderIndex, long attendanceIndex) {
        try {
            LOGGER.info("Add attendance to order");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating attendance. Update operation: add attendance to order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn(new MessageFormatMessage("Order with index {0} or Attendance with index: " +
                    "{1} don't exists.", orderIndex, attendanceIndex), e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
