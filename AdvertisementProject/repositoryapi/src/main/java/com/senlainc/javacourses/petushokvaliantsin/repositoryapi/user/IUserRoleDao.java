package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRole;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;

public interface IUserRoleDao extends IGenericDao<UserRole, Long> {

    UserRole readByDescription(String description);
}
