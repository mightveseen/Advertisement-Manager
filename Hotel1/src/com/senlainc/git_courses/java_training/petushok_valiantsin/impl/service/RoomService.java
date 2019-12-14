package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Free;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Status;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.RoomDao;

public class RoomService implements IRoomService {
    private IRoomDao roomDao = new RoomDao();

    @Override
    public void add(Room room) {
        try {
            roomDao.create(room);
        } catch (Exception e) {
            System.err.println("Wrong data.");
        }
    }

    @Override
    public void delete(int index) {
        try {
            roomDao.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Room with index: " + index + " dont exists.");
        }
    }

    @Override
    public void changePrice(int index, double price) {
        Room room = new Room(roomDao.read(index));
        try {
            room.setPrice(price);
            roomDao.update(index, room);
        }catch (Exception e) {
            System.err.println("Wrong data.");
        }
    }

    @Override
    public void changeStatus(int index, Status status) {
        Room room = new Room(roomDao.read(index));
        try {
            room.setStatus(status);
            roomDao.update(index, room);
        }catch (Exception e) {
            System.err.println("Wrong data.");
        }
    }

    @Override
    public void showRoom(String parameter) {
        switch (parameter) {
            case "all":
                showAllRoom();
                break;
            case "free":
                showFreeRoom();
                break;
        }
    }

    private void showAllRoom() {
        for(int i = 0; i < roomDao.readAll().size(); i++) {
            System.out.print(roomDao.read(i));
        }
    }

    private void showFreeRoom() {
        for(int i = 0; i < roomDao.readAll().size(); i++) {
            if(roomDao.read(i).getStatus() instanceof Free){
                System.out.print(roomDao.read(i));
            }
        }
    }

    @Override
    public int numFreeRoom() {
        int counter = 0;
        for(int i = 0; i < roomDao.readAll().size(); i++) {
            if(roomDao.read(i).getStatus() instanceof Free){
                counter++;
            }
        }
        return counter;
    }
}
