package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends AbstractService<User, Long> implements IUserService {

    private final IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(User object) {
        userDao.create(object);
        return true;
    }

    @Override
    @Transactional
    public boolean update(String username, UserDto object) {
        if (!userDao.readByUserCred(username).getId().equals(object.getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION_EXCEPTION.getMessage());
        }
        object.setRating(userDao.read(object.getId()).getRating());
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
}
