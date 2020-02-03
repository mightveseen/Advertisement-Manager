package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "roomDao")
public class RoomDao implements IRoomDao {
    private static final Logger LOGGER = Logger.getLogger(RoomDao.class.getName());
    @XmlElementWrapper(name = "roomList")
    @XmlElement(name = "room")
    private List<Room> roomList;

    @Override
    public void create(Room room) {
        roomList.add(room);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void delete(int index) {
        roomList.remove(roomList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void update(Room room) {
        roomList.set(roomList.indexOf(roomList.stream().filter(i -> i.getId() == room.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), room);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public List<Room> readAll() {
        return new ArrayList<>(roomList);
    }

    @Override
    public Room read(int index) {
        return roomList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    @Override
    public void setAll() {
        try {
            roomList = Serialization.getInstance().customUnmarshaller(this).readAll();
        } catch (RuntimeException e) {
            roomList = new ArrayList<>();
            LOGGER.log(Level.WARNING, e.getMessage() + ", create empty list", e);
        }
    }
}
