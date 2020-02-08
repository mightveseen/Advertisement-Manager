package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
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
@XmlRootElement(name = "guestDao")
public class GuestDao implements IGuestDao {
    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());
    @XmlElementWrapper(name = "guestList")
    @XmlElement(name = "guest")
    private List<Guest> guestList;

    @Override
    public void create(Guest guest) {
        guest.setId(guestList.size() + 1);
        guestList.add(guest);
        saveAll();
    }

    @Override
    public void delete(int index) {
        guestList.remove(guestList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
        saveAll();
    }

    @Override
    public void update(Guest guest) {
        guestList.set(guestList.indexOf(guestList.stream().filter(i -> i.getId() == guest.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), guest);
        saveAll();
    }

    @Override
    public List<Guest> readAll() {
        return new ArrayList<>(guestList);
    }

    @Override
    public Guest read(int index) {
        return guestList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    @Override
    public void setAll() {
        try {
            guestList = Serialization.getInstance().customUnmarshaller(this).readAll();
        } catch (RuntimeException e) {
            guestList = new ArrayList<>();
            LOGGER.log(Level.WARNING, e.getMessage() + ", create empty list", e);
        }
    }

    @Override
    public void saveAll() {
        Serialization.getInstance().customMarshaller(this);
    }
}
