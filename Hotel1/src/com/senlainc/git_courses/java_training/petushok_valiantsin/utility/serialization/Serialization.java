package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.LoadConfig;
import sun.dc.path.PathError;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Serialization {
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
                return LoadConfig.getInstance().getAttendanceDaoPathProperty();
            case "GuestDao":
                return LoadConfig.getInstance().getGuestDaoPathProperty();
            case "OrderDao":
                return LoadConfig.getInstance().getOrderDaoPathProperty();
            case "RoomDao":
                return LoadConfig.getInstance().getRoomDaoPathProperty();
            default:
                throw new PathError("Inappropriate class");
        }
    }

    public <T> void customMarshaller(T clazz) {
        try (OutputStream fileWriter = new FileOutputStream(getPath(clazz))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(clazz, fileWriter);
        } catch (JAXBException | IOException | PathError e) {
            throw new RuntimeException("Couldn't upload data file", e);
        }
    }

    public <T> T customUnmarshaller(T clazz) {
        try (InputStream fileReader = new FileInputStream(getPath(clazz))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(fileReader);
        } catch (JAXBException | IOException | PathError e) {
            throw new RuntimeException("Couldn't load data file", e);
        }
    }
}
