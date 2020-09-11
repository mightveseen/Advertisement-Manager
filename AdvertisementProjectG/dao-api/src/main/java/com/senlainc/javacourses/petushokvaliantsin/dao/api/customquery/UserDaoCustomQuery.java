package com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import java.util.Optional;

public interface UserDaoCustomQuery {

    Optional<User> readByUserCred(String username);

    Optional<User> readByUserCred(String username, String graphName);

    Optional<User> readByEmail(String email);

    Optional<User> readByEmail(String email, String graphName);

    Optional<User> readByPhone(Integer phone);

    Optional<User> readByPhone(Integer phone, String graphName);
}
