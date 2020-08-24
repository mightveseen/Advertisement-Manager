package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.combination.AccountDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService extends AbstractService implements IUserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private static final String[] USER_FIELDS = {"username", "email", "phone"};
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
        checkUsername(accountDto.getUserCred().getUsername());
        checkEmail(accountDto.getUser().getEmail());
        checkPhone(accountDto.getUser().getPhone());
        final UserCred userCred = createUserCred(accountDto);
        userCred.setPassword(passwordEncoder.encode(userCred.getPassword()));
        userCredDao.save(userCred);
        final User user = createUser(accountDto, userCred);
        userDao.create(user);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean update(String username, UserDto userDto) {
        final User user = userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username)));
        checkPermission(user, userDto);
        userDto.setRating(user.getRating());
        userDao.update(dtoMapper.map(userDto, User.class));
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto read(Long userIndex) {
        final UserDto result = dtoMapper.map(userDao.read(userIndex, GraphProperty.User.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[0], userIndex))), UserDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto readByUsername(String username) {
        final UserDto result = dtoMapper.map(userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username))), UserDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    private UserCred createUserCred(AccountDto accountDto) {
        final UserCred userCred = dtoMapper.map(accountDto.getUserCred(), UserCred.class);
        userCred.setRole(EnumRole.ROLE_COMMON);
        return userCred;
    }

    private User createUser(AccountDto accountDto, UserCred userCred) {
        final User user = dtoMapper.map(accountDto.getUser(), User.class);
        user.setRegistrationDate(LocalDate.now());
        user.setId(userCred.getId());
        user.setUserCred(userCred);
        return user;
    }

    private void checkPermission(User user, UserDto userDto) {
        if (!user.getId().equals(userDto.getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }

    private void checkEmail(String email) {
        if (userDao.readByEmail(email).isPresent()) {
            throw new EntityAlreadyExistException(String.format(EnumException.USER_WITH_FIELD_EXIST.getMessage(), USER_FIELDS[1], email));
        }
    }

    private void checkPhone(Integer phone) {
        if (userDao.readByPhone(phone).isPresent()) {
            throw new EntityAlreadyExistException(String.format(EnumException.USER_WITH_FIELD_EXIST.getMessage(), USER_FIELDS[2], phone));
        }
    }

    private void checkUsername(String username) {
        if (userCredDao.readByUsername(username).isPresent()) {
            throw new EntityAlreadyExistException(String.format(EnumException.USER_WITH_FIELD_EXIST.getMessage(), USER_FIELDS[0], username));
        }
    }
}
