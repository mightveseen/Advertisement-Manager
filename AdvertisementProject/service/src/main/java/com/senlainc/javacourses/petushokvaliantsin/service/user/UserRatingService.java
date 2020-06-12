package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserRatingDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserRatingService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.WrongEnteredDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRatingService extends AbstractService<UserRating, Long> implements IUserRatingService {

    private final IUserRatingDao userRatingDao;
    private final IUserService userService;

    @Autowired
    public UserRatingService(IUserRatingDao userRatingDao, IUserService userService) {
        this.userRatingDao = userRatingDao;
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean create(UserRatingDto userRatingDto) {
        final UserRating userRating = dtoMapper.map(userRatingDto, UserRating.class);
        if (userRating.getRatedUser().getId().equals(userRating.getRateOwnerUser().getId())) {
            throw new WrongEnteredDataException("Yoy can't rate yourself");
        }
        if (userRatingDao.readCount(userRating.getRateOwnerUser(), userRating.getRatedUser()) > 0) {
            throw new EntityAlreadyExistException("You already rated this user");
        }
        userRatingDao.create(userRating);
        final User user = userService.read(userRating.getRatedUser().getId());
        user.setRating((user.getRating() * user.getRatedUserRatings().size() + userRating.getValue()) / user.getRatedUserRatings().size() + 1);
        userService.update(user);
        return true;
    }
}
