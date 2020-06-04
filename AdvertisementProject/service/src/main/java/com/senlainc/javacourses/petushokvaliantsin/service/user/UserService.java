package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService extends AbstractService<User, Long> implements IUserService {

    private final IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean create(User object) {
        userDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        userDao.create(userDao.read(index));
        return true;
    }

    @Override
    public boolean update(User object) {
        userDao.update(object);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public User read(Long index) {
        return userDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readAll(int firstElement, int maxResult) {
        return userDao.readAll(PageParameter.of(firstElement, maxResult));
    }
}
