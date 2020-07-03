package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.CreateUserDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService extends AbstractService implements IUserService {

    private final IUserDao userDao;
    private final IUserCredDao userCredDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserDao userDao, IUserCredDao userCredDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userCredDao = userCredDao;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO : Check username and email
    @Override
    @Transactional
    public boolean create(CreateUserDto createUserDto) {
        final UserCred userCred = userCredCreateOperation(createUserDto);
        userCred.setPassword(passwordEncoder.encode(userCred.getPassword()));
        userCredDao.create(userCred);
        final User user = userCreateOperation(createUserDto, userCred);
        userDao.create(user);
        return true;
    }

    @Override
    @Transactional
    public boolean update(String username, UserDto object) {
        final User user = userDao.readByUserCred(username);
        checkPermissionUpdateOperation(user, object);
        object.setRating(user.getRating());
        userDao.update(dtoMapper.map(object, User.class));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long userIndex) {
        return dtoMapper.map(userDao.read(userIndex), UserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String username) {
        return dtoMapper.map(userDao.readByUserCred(username), UserDto.class);
    }

    private UserCred userCredCreateOperation(CreateUserDto createUserDto) {
        final UserCred userCred = dtoMapper.map(createUserDto.getUserCred(), UserCred.class);
        userCred.setEnumRole(EnumRole.ROLE_COMMON);
        return userCred;
    }

    private User userCreateOperation(CreateUserDto createUserDto, UserCred userCred) {
        final User user = dtoMapper.map(createUserDto.getUser(), User.class);
        user.setRegistrationDate(LocalDate.now());
        user.setId(userCred.getId());
        user.setUserCred(userCred);
        return user;
    }

    private void checkPermissionUpdateOperation(User user, UserDto object) {
        if (!user.getId().equals(object.getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION_EXCEPTION.getMessage());
        }
    }
}
