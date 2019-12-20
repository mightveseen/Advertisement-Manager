package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public class RoomDao implements IRoomDao {
    private final MyList<Room> roomMyList = new MyList<>();

    @Override
    public void create(Room room) {
        roomMyList.add(room);
    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < roomMyList.size(); i++) {
            if (roomMyList.get(i).getId() == index) {
                roomMyList.remove(i);
                return;
            }
        }
    }

    @Override
    public void update(Room room) {
        for (int i = 0; i < roomMyList.size(); i++) {
            if (roomMyList.get(i).getId() == room.getId()) {
                roomMyList.add(i, room);
                return;
            }
        }
    }

    @Override
    public MyList<Room> readAll() {
        return roomMyList;
    }

    @Override
    public Room read(int index) {
        for (int i = 0; i < roomMyList.size(); i++) {
            if (roomMyList.get(i).getId() == index) {
                return roomMyList.get(i);
            }
        }
        return null;
    }
}
