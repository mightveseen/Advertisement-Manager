package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@DependencyClass
@DependencyPrimary
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "guestDao")
public class GuestDao implements IGuestDao {
    private static final Logger LOGGER = Logger.getLogger(GuestDao.class.getName());
    @DependencyComponent
    private static Serialization serialization;
    @DependencyComponent
    private ConnectionManager connectionManager;
    @XmlElementWrapper(name = "guestList")
    @XmlElement(name = "guest")
    private List<Guest> guestList;

    @Override
    public void create(Guest guest) {
        guest.setId(guestList.size() + 1);
        guestList.add(guest);
    }

    @Override
    public void delete(Integer index) {
        guestList.remove(guestList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
    }

    @Override
    public void update(Guest guest) {
        guestList.set(guestList.indexOf(guestList.stream()
                .filter(i -> i.getId() == guest.getId())
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), guest);
    }

    @Override
    public List<Guest> readAll() {
        return new ArrayList<>(guestList);
    }

    @Override
    public Guest read(Integer index) {
        return guestList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }
}
