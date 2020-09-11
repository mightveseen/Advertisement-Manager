package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserCredServiceImpl extends AbstractService implements UserDetailsService {

    private final UserCredDao userCredDao;

    @Autowired
    public UserCredServiceImpl(UserCredDao userCredDao) {
        this.userCredDao = userCredDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        final UserCred user = userCredDao.readByUsername(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username)));
        return new User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
