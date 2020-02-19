package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.FileNotExistException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
@DependencyPrimary
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "roomDao")
public class RoomDao implements IRoomDao {
    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());
    @DependencyComponent
    private static Serialization serialization;
    @XmlElementWrapper(name = "roomList")
    @XmlElement(name = "room")
    private List<Room> roomList;

    @Override
    public void create(Room room) {
        room.setId(roomList.size() + 1);
        roomList.add(room);
        saveAll();
    }

    @Override
    public void delete(int index) {
        roomList.remove(roomList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
        saveAll();
    }

    @Override
    public void update(Room room) {
        roomList.set(roomList.indexOf(roomList.stream()
                .filter(i -> i.getId() == room.getId())
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), room);
        saveAll();
    }

    @Override
    public List<Room> readAll() {
        return new ArrayList<>(roomList);
    }

    @Override
    public Room read(int index) {
        return roomList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    @Override
    public void setAll() {
        try {
            roomList = serialization.customUnmarshaller(this).readAll();
        } catch (FileNotExistException e) {
            roomList = new ArrayList<>();
            LOGGER.log(Level.WARNING, e.getMessage() + ", create empty list", e);
        }
    }

    @Override
    public void saveAll() {
        try {
            serialization.customMarshaller(this);
        } catch (FileNotExistException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
