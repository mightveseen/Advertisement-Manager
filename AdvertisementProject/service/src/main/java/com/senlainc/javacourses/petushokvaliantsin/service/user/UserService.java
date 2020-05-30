package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<User, Long> implements IUserService {
    @Override
    public boolean create(User object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public User read(Long index) {
        return null;
    }

    @Override
    public List<User> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public List<User> readAll(int firstElement, int maxResult, String sortField) {
        return null;
    }
}
