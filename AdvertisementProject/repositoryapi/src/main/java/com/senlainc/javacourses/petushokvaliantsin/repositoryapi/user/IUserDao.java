package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;

public interface IUserDao extends IGenericDao<User, Long> {

    User readByUserCred(String username);
}
