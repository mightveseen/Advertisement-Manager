package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserRatingDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserRatingService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.WrongEnteredDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRatingService extends AbstractService implements IUserRatingService {

    private final IUserRatingDao userRatingDao;
    private final IUserDao userDao;

    @Autowired
    public UserRatingService(IUserRatingDao userRatingDao, IUserDao userDao) {
        this.userRatingDao = userRatingDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(String username, Long ratedUserIndex, UserRatingDto userRatingDto) {
        final User activeUser = userDao.readByUserCred(username);
        checkYourselfCreateOperation(activeUser, ratedUserIndex);
        checkRateExistCreateOperation(activeUser, ratedUserIndex);
        final User ratedUser = userDao.read(ratedUserIndex);
        userRatingDao.create(userRatingCreateOperation(userRatingDto, activeUser, ratedUser));
        ratedUser.setRating((ratedUser.getRating() * ratedUser.getRatedUserRatings().size() + userRatingDto.getValue()) / (ratedUser.getRatedUserRatings().size() + 1));
        userDao.update(ratedUser);
        return true;
    }

    private UserRating userRatingCreateOperation(UserRatingDto userRatingDto, User activeUser, User ratedUser) {
        final UserRating userRating = dtoMapper.map(userRatingDto, UserRating.class);
        userRating.setRateOwnerUser(activeUser);
        userRating.setRatedUser(ratedUser);
        return userRating;
    }

    private void checkYourselfCreateOperation(User activeUser, Long ratedUserIndex) {
        if (activeUser.getId().equals(ratedUserIndex)) {
            throw new WrongEnteredDataException(EnumException.RATE_YOURSELF.getMessage());
        }
    }

    private void checkRateExistCreateOperation(User activeUser, Long ratedUserIndex) {
        if (activeUser.getRateOwnerUserRatings().stream().anyMatch(i -> i.getRatedUser().getId().equals(ratedUserIndex))) {
            throw new EntityAlreadyExistException(EnumException.RATE_EXIST.getMessage());
        }
    }
}
