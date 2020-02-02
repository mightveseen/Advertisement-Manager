package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.LoadConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serialization {
    public static Serialization instance;
    private static final Logger LOGGER = Logger.getLogger(Serialization.class.getSimpleName());

    public static Serialization getInstance() {
        if (instance == null) {
            instance = new Serialization();
        }
        return instance;
    }

    private String getPath(Object clazz) {
        switch (clazz.getClass().getSimpleName()) {
            case "AttendanceDao":
                return LoadConfig.getInstance().getAttendanceDaoPathProperty();
            case "GuestDao":
                return LoadConfig.getInstance().getGuestDaoPathProperty();
            case "OrderDao":
                return LoadConfig.getInstance().getOrderDaoPathProperty();
            case "RoomDao":
                return LoadConfig.getInstance().getRoomDaoPathProperty();
            default:
                throw new RuntimeException("Inappropriate class");
        }
    }

    public <T> void customMarshaller(T clazz) {
        try (OutputStream fileWriter = new FileOutputStream(getPath(clazz))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(clazz, fileWriter);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public <T> T customUnmarshaller(T clazz) {
        try (InputStream fileReader = new FileInputStream(getPath(clazz))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(fileReader);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        throw new RuntimeException("Could load data file");
    }
}
