package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.IUserRatingDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.IUserRatingService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.WrongEnteredDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRatingService extends AbstractService implements IUserRatingService {

    private static final Logger LOGGER = LogManager.getLogger(UserRatingService.class);
    private final IUserRatingDao userRatingDao;
    private final IUserDao userDao;

    @Autowired
    public UserRatingService(IUserRatingDao userRatingDao, IUserDao userDao) {
        this.userRatingDao = userRatingDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(String username, Long ratedUserIndex, UserRatingDto object) {
        final User activeUser = userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username)));
        checkYourself(activeUser, ratedUserIndex);
        checkRateExist(activeUser, ratedUserIndex);
        final User ratedUser = userDao.read(ratedUserIndex, GraphProperty.User.USER_CRED_AND_RATE).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[0], ratedUserIndex)));
        userRatingDao.save(createUserRating(object, activeUser, ratedUser));
        ratedUser.setRating(updateUserRatingValue(ratedUser, object.getValue()));
        userDao.update(ratedUser);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    private UserRating createUserRating(UserRatingDto userRatingDto, User activeUser, User ratedUser) {
        final UserRating userRating = dtoMapper.map(userRatingDto, UserRating.class);
        userRating.setRateOwnerUser(activeUser);
        userRating.setRatedUser(ratedUser);
        return userRating;
    }

    private void checkYourself(User activeUser, Long ratedUserIndex) {
        if (activeUser.getId().equals(ratedUserIndex)) {
            throw new WrongEnteredDataException(EnumException.RATE_YOURSELF.getMessage());
        }
    }

    private void checkRateExist(User activeUser, Long ratedUserIndex) {
        if (activeUser.getRateOwnerUserRatings().stream().anyMatch(i -> i.getRatedUser().getId().equals(ratedUserIndex))) {
            throw new EntityAlreadyExistException(EnumException.RATE_EXIST.getMessage());
        }
    }

    private float updateUserRatingValue(User ratedUser, float rating) {
        return (ratedUser.getRating() * ratedUser.getRatedUserRatings().size() + rating) / (ratedUser.getRatedUserRatings().size() + 1);
    }
}