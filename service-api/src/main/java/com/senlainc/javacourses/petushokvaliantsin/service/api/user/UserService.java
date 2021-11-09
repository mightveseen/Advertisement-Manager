package com.senlainc.javacourses.petushokvaliantsin.service.api.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.combination.AccountDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

public interface UserService {

    boolean update(String username, UserDto object);

    boolean create(AccountDto accountDto);

    UserDto read(Long userIndex);

    UserDto readByUsername(String username);
}
