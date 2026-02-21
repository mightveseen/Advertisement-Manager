package com.senlainc.javacourses.petushokvaliantsin.service.user;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserRatingDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.UserRatingService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.WrongEnteredDataException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRatingServiceImpl extends AbstractService implements UserRatingService {

    private static final Logger LOGGER = LogManager.getLogger(UserRatingServiceImpl.class);
    private final UserRatingDao userRatingDao;
    private final UserDao userDao;

    @Override
    @Transactional
    public boolean create(String username, Long ratedUserIndex, UserRatingDto object) {
        final User activeUser = userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username)));
        checkYourself(activeUser, ratedUserIndex);
        checkRateExist(activeUser, ratedUserIndex);
        final User ratedUser = userDao.readById(ratedUserIndex).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 0, ratedUserIndex)));
        userRatingDao.save(createUserRating(object, activeUser, ratedUser));
        ratedUser.setRating(updateUserRatingValue(ratedUser, object.value()));
        userDao.save(ratedUser);
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
