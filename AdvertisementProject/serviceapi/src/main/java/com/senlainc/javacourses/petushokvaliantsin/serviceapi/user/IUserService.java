package com.senlainc.javacourses.petushokvaliantsin.serviceapi.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.combination.AccountDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

public interface IUserService {

    boolean update(String username, UserDto object);

    boolean create(AccountDto accountDto);

    UserDto getUser(Long userIndex);

    UserDto getUser(String username);
}
