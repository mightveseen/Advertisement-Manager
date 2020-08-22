package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;

import java.util.Optional;

public interface IUserDao extends IGenericDao<User, Long> {

    Optional<User> readByUserCred(String username);

    Optional<User> readByEmail(String email);

    Optional<User> readByPhone(Integer phone);
}
