package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import org.springframework.data.repository.CrudRepository;

public interface IUserRatingDao extends CrudRepository<UserRating, Long> {

}
