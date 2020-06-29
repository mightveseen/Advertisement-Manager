package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
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
    public boolean delete(Long index) {
        userDao.create(userDao.read(index));
        return true;
    }

    @Override
    @Transactional
    public boolean update(UserDto object) {
        userDao.read(object.getId());
        object.setRating(userDao.read(object.getId()).getRating());
        userDao.update(dtoMapper.map(object, User.class));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long index) {
        return dtoMapper.map(userDao.read(index), UserDto.class);
    }
}
