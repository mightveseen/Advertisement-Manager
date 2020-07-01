package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserRoleDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserCredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserCredService extends AbstractService<UserCred, Long> implements UserDetailsService, IUserCredService {

    private final IUserCredDao userCredDao;
    private final IUserRoleDao userRoleDao;

    @Autowired
    public UserCredService(IUserCredDao userCredDao, IUserRoleDao userRoleDao) {
        this.userCredDao = userCredDao;
        this.userRoleDao = userRoleDao;
    }

    @Override
    @Transactional
    public boolean create(UserCredDto userCredDto) {
        final UserCred userCred = dtoMapper.map(userCredDto, UserCred.class);
        userCred.setUserRole(userRoleDao.readByDescription(EnumRole.COMMON.name()));
        userCredDao.create(userCred);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        final UserCred user = userCredDao.readByUsername(username);
        return new User(user.getLogin(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getUserRole().getDescription())));
    }
}
