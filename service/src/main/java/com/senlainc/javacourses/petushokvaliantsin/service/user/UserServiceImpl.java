package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.AccountDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.UserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractService implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private static final String[] USER_FIELDS = {"username", "email", "phone"};
    private final UserDao userDao;
    private final UserCredDao userCredDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean create(AccountDto accountDto) {
        checkUsername(accountDto.userCred().username());
        checkEmail(accountDto.user().email());
        checkPhone(accountDto.user().phone());
        final UserCred userCred = createUserCred(accountDto);
        userCred.setPassword(passwordEncoder.encode(userCred.getPassword()));
        userCredDao.save(userCred);
        final User user = createUser(accountDto, userCred);
        userDao.save(user);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean update(String username, UserDto userDto) {
        final User user = userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username)));
        checkPermission(user, userDto);
        final UserDto updatedDto = new UserDto(userDto.id(), userDto.firstName(), userDto.lastName(),
                userDto.email(), userDto.phone(), userDto.registrationDate(), user.getRating());
        userDao.save(dtoMapper.map(updatedDto, User.class));
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto read(Long userIndex) {
        final UserDto result = dtoMapper.map(userDao.readById(userIndex).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 0, userIndex))), UserDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto readByUsername(String username) {
        final UserDto result = dtoMapper.map(userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username))), UserDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    private UserCred createUserCred(AccountDto accountDto) {
        final UserCred userCred = dtoMapper.map(accountDto.userCred(), UserCred.class);
        userCred.setRole(EnumRole.ROLE_COMMON);
        return userCred;
    }

    private User createUser(AccountDto accountDto, UserCred userCred) {
        final User user = dtoMapper.map(accountDto.user(), User.class);
        user.setRegistrationDate(LocalDate.now());
        user.setId(userCred.getId());
        user.setUserCred(userCred);
        return user;
    }

    private void checkPermission(User user, UserDto userDto) {
        if (!user.getId().equals(userDto.id())) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }

    private void checkEmail(String email) {
        if (userDao.readByEmail(email).isPresent()) {
            throw new EntityAlreadyExistException(userExistMessage(1, email));
        }
    }

    private void checkPhone(Integer phone) {
        if (userDao.readByPhone(phone).isPresent()) {
            throw new EntityAlreadyExistException(userExistMessage(2, phone));
        }
    }

    private void checkUsername(String username) {
        if (userCredDao.readByUsername(username).isPresent()) {
            throw new EntityAlreadyExistException(userExistMessage(0, username));
        }
    }

    private String userExistMessage(int fieldId, Object field) {
        return String.format(EnumException.USER_WITH_FIELD_EXIST.getMessage(), USER_FIELDS[fieldId], field);
    }
}
