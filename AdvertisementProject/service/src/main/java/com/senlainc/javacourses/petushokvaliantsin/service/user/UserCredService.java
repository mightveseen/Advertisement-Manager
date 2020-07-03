package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserCredService extends AbstractService implements UserDetailsService {

    private static final String USER_CRED_FIELD = "username";
    private final IUserCredDao userCredDao;

    @Autowired
    public UserCredService(IUserCredDao userCredDao) {
        this.userCredDao = userCredDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        try {
            final UserCred user = userCredDao.readByUsername(username);
            return new User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getEnumRole().name())));
        } catch (ReadQueryException exc) {
            throw new EntityNotExistException(String.format(EnumException.USER_WITH_FIELD_NOT_EXIST.getMessage(), USER_CRED_FIELD, username));
        }
    }
}
