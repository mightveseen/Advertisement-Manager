package com.senlainc.javacourses.petushokvaliantsin.serviceapi.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.CreateUserDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

public interface IUserService {

    boolean update(String username, UserDto object);

    boolean create(CreateUserDto createUserDto);

    UserDto getUser(Long userIndex);

    UserDto getUser(String username);
}
