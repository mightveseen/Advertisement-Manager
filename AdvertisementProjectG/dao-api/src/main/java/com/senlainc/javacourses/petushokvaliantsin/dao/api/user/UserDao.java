package com.senlainc.javacourses.petushokvaliantsin.dao.api.user;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.GenericDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.UserDaoCustomQuery;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface UserDao extends UserDaoCustomQuery, GenericDao<User, Long> {

    @EntityGraph(value = GraphProperty.User.DEFAULT)
    Optional<User> readById(Long id);

    //TODO: Find solution
//    @EntityGraph(value = GraphProperty.User.USER_CRED_AND_RATE)
//    Optional<User> readByIdWithSecondGraph(Long id);
}
