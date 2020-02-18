package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization;

import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.LoadConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.FileNotExistException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.jar.JarOutputStream;

@DependencyClass
public class Serialization {
    @DependencyComponent
    private static LoadConfig loadConfig;

    private String getPath(Object clazz) throws java.io.FileNotFoundException {
        switch (clazz.getClass().getSimpleName()) {
            case "AttendanceDao":
                return loadConfig.getAttendanceDaoPath();
            case "GuestDao":
                return loadConfig.getGuestDaoPath();
            case "OrderDao":
                return loadConfig.getOrderDaoPath();
            case "RoomDao":
                return loadConfig.getRoomDaoPath();
            default:
                throw new java.io.FileNotFoundException("Inappropriate class");
        }
    }

    public <T> void customMarshaller(T clazz) {
        try (JarOutputStream fileWriter = new JarOutputStream(new FileOutputStream(getPath(clazz)))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(clazz, fileWriter);
        } catch (JAXBException | IOException e) {
            throw new FileNotExistException("Couldn't upload data file", e);
        }
    }

    public <T> T customUnmarshaller(T clazz) {
        try (InputStream fileReader = Serialization.class.getResourceAsStream(getPath(clazz))) {
            final JAXBContext context = JAXBContext.newInstance(clazz.getClass());
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(fileReader);
        } catch (JAXBException | IOException e) {
            throw new FileNotExistException("Couldn't load data file", e);
        }
    }
}
