package com.senlainc.javacourses.petushokvaliantsin.dao.api.user;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.GenericDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.UserDaoChild;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface UserDao extends UserDaoChild, GenericDao<User, Long> {

    @EntityGraph(value = GraphProperty.User.DEFAULT)
    Optional<User> readById(Long id);
}
