package com.senlainc.javacourses.petushokvaliantsin.repository.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User, Long> implements IUserDao {
}
