package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.combination.AccountDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService extends AbstractService implements IUserService {

    private static final String[] USER_FIELDS = {"username", "email"};
    private final IUserDao userDao;
    private final IUserCredDao userCredDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserDao userDao, IUserCredDao userCredDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userCredDao = userCredDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean create(AccountDto accountDto) {
        checkUsernameCreateOperation(accountDto.getUserCred().getUsername());
        checkEmailCreateOperation(accountDto.getUser().getEmail());
        final UserCred userCred = userCredCreateOperation(accountDto);
        userCred.setPassword(passwordEncoder.encode(userCred.getPassword()));
        userCredDao.create(userCred);
        final User user = userCreateOperation(accountDto, userCred);
        userDao.create(user);
        return true;
    }

    @Override
    @Transactional
    public boolean update(String username, UserDto userDto) {
        checkEmailUpdateOperation(userDto.getEmail(), userDto);
        final User user = userDao.readByUserCred(username);
        checkPermissionUpdateOperation(user, userDto);
        userDto.setRating(user.getRating());
        userDao.update(dtoMapper.map(userDto, User.class));
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

    private UserCred userCredCreateOperation(AccountDto accountDto) {
        final UserCred userCred = dtoMapper.map(accountDto.getUserCred(), UserCred.class);
        userCred.setEnumRole(EnumRole.ROLE_COMMON);
        return userCred;
    }

    private User userCreateOperation(AccountDto accountDto, UserCred userCred) {
        final User user = dtoMapper.map(accountDto.getUser(), User.class);
        user.setRegistrationDate(LocalDate.now());
        user.setId(userCred.getId());
        user.setUserCred(userCred);
        return user;
    }

    private void checkPermissionUpdateOperation(User user, UserDto userDto) {
        if (!user.getId().equals(userDto.getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }

    private void checkEmailCreateOperation(String email) {
        try {
            if (userDao.readByEmail(email) != null) {
                throw new EntityAlreadyExistException(String.format(EnumException.USER_WITH_FIELD_EXIST.getMessage(), USER_FIELDS[1], email));
            }
        } catch (ReadQueryException ignored) {
        }
    }

    private void checkEmailUpdateOperation(String email, UserDto userDto) {
        try {
            final User user = userDao.readByEmail(email);
            if (user != null && !user.getId().equals(userDto.getId())) {
                throw new EntityAlreadyExistException(String.format(EnumException.USER_WITH_FIELD_EXIST.getMessage(), USER_FIELDS[1], email));
            }
        } catch (ReadQueryException ignored) {
        }
    }

    private void checkUsernameCreateOperation(String username) {
        if (userCredDao.readByUsername(username) != null) {
            throw new EntityAlreadyExistException(String.format(EnumException.USER_WITH_FIELD_EXIST.getMessage(), USER_FIELDS[0], username));
        }
    }
}
