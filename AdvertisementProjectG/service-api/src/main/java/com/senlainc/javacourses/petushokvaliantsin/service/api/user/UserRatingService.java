package com.senlainc.javacourses.petushokvaliantsin.service.api.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;

public interface UserRatingService {

    boolean create(String username, Long ratedUserIndex, UserRatingDto object);
}
