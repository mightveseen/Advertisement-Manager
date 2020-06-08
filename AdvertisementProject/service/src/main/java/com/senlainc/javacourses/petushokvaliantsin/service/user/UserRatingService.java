package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserRatingDao;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRatingService implements IUserRatingService {

    private final IUserRatingDao userRatingDao;

    @Autowired
    public UserRatingService(IUserRatingDao userRatingDao) {
        this.userRatingDao = userRatingDao;
    }
}
