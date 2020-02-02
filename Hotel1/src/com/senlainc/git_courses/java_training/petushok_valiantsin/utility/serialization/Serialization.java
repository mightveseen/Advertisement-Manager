package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.sun.tools.hat.internal.model.JavaObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serialization {
    private static final Logger LOGGER = Logger.getLogger(Serialization.class.getSimpleName());
    private static final String ATTENDANCE_DAO_PATH = "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/serialization/xml/AttendanceDao.xml";
    private static final String GUEST_DAO_PATH = "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/serialization/xml/GuestDao.xml";
    private static final String ROOM_DAO_PATH = "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/serialization/xml/RoomDao.xml";
    private static final String ORDER_DAO_PATH = "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/serialization/xml/OrderDao.xml";
    public static Serialization instance;

    public static Serialization getInstance() {
        if (instance == null) {
            instance = new Serialization();
        }
        return instance;
    }

    private String getPath(Object clazz) {
        switch (clazz.getClass().getSimpleName()) {
            case "AttendanceDao":
                return ATTENDANCE_DAO_PATH;
            case "GuestDao":
                return GUEST_DAO_PATH;
            case "OrderDao":
                return ORDER_DAO_PATH;
            case "RoomDao":
                return ROOM_DAO_PATH;
            default:
                throw new RuntimeException("Inappropriate class");
        }
    }

    public void customMarshaller(Object clazz) {
        try (OutputStream fileWriter = new FileOutputStream(getPath(clazz))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(clazz, fileWriter);
        } catch (JAXBException | IOException | RuntimeException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public Object customUnmarshaller(Object clazz) {
        try (InputStream fileReader = new FileInputStream(getPath(clazz))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(fileReader);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }
}
