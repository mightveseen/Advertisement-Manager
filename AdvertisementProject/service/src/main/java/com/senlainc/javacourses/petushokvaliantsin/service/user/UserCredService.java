package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserCredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserCredService implements UserDetailsService, IUserCredService {

    private final IUserCredDao userCredDao;

    @Autowired
    public UserCredService(IUserCredDao userCredDao) {
        this.userCredDao = userCredDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
