package com.senlainc.javacourses.petushokvaliantsin.repository.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserRatingDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserRatingDao extends AbstractDao<UserRating, Long> implements IUserRatingDao {

}
