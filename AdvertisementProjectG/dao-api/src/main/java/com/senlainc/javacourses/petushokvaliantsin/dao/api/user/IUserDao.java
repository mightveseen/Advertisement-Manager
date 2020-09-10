package com.senlainc.javacourses.petushokvaliantsin.dao.api.user;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.IGenericDao;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import java.util.Optional;

public interface IUserDao extends IGenericDao<User, Long> {

    //TODO : Fix Optional
    Optional<User> readByUserCred(String username);

    //TODO : Fix Optional
    Optional<User> readByUserCred(String username, String graphName);

    Optional<User> readByEmail(String email);

    Optional<User> readByEmail(String email, String graphName);

    Optional<User> readByPhone(Integer phone);

    Optional<User> readByPhone(Integer phone, String graphName);
}
