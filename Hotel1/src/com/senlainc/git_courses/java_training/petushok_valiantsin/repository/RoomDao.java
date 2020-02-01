package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "roomDao")
public class RoomDao implements IRoomDao {
    @XmlElementWrapper(name = "roomList")
    @XmlElement(name = "room")
    private final List<Room> roomList = new ArrayList<>();

    @Override
    public void create(Room room) {
        roomList.add(room);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void delete(int index) {
        roomList.remove(roomList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
    }

    @Override
    public void update(Room room) {
        roomList.set(roomList.indexOf(roomList.stream().filter(i -> i.getId() == room.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), room);
    }

    @Override
    public List<Room> readAll() {
        return new ArrayList<>(roomList);
    }

    @Override
    public Room read(int index) {
        return roomList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }
}
