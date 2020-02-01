package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "guestDao")
public class GuestDao implements IGuestDao {
    @XmlElementWrapper(name = "guestList")
    @XmlElement(name = "guest")
    private final List<Guest> guestList = new ArrayList<>();

    @Override
    public void create(Guest guest) {
        guestList.add(guest);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void delete(int index) {
        guestList.remove(guestList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
    }

    @Override
    public void update(Guest guest) {
        guestList.set(guestList.indexOf(guestList.stream().filter(i -> i.getId() == guest.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), guest);
    }

    @Override
    public List<Guest> readAll() {
        return new ArrayList<>(guestList);
    }

    @Override
    public Guest read(int index) {
        return guestList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }
}
