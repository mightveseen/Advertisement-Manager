package com.senlainc.javacourses.petushokvaliantsin.serviceapi.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

public interface IUserService extends IGenericService<User, Long> {

    boolean update(User object);

    boolean delete(Long index);

    boolean create(User object);
}
