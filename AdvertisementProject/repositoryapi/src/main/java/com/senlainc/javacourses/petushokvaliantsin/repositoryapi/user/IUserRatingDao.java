package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;

public interface IUserRatingDao extends IGenericDao<UserRating, Long> {

    Long readCount(User rateOwnerUser, User ratedUser);
}
