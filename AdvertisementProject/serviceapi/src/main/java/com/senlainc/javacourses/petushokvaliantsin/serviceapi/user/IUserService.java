package com.senlainc.javacourses.petushokvaliantsin.serviceapi.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

public interface IUserService {

    boolean update(String username, UserDto object);

    boolean create(User object);

    UserDto getUser(Long userIndex);

    UserDto getUser(String username);
}
