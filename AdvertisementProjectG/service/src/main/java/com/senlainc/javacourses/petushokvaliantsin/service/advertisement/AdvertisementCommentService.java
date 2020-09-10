package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.IAdvertisementCommentDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment_;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@SingularClass
public class AdvertisementCommentService extends AbstractService implements IAdvertisementCommentService {

    private static final String SORT_FIELD = "advertisementcomment-";
    private static final Logger LOGGER = LogManager.getLogger(AdvertisementCommentService.class);
    private final IAdvertisementCommentDao advertisementCommentDao;
    private final IAdvertisementDao advertisementDao;
    private final IUserDao userDao;

    @Autowired
    public AdvertisementCommentService(IAdvertisementCommentDao advertisementCommentDao, IAdvertisementDao advertisementDao, IUserDao userDao) {
        this.advertisementCommentDao = advertisementCommentDao;
        this.advertisementDao = advertisementDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(String username, Long index, AdvertisementCommentDto object) {
        final AdvertisementComment advertisementComment = dtoMapper.map(object, AdvertisementComment.class);
        advertisementComment.setUser(userDao.readByUserCred(username, GraphProperty.User.DEFAULT).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), "User", "username", username))));
        advertisementComment.setDateTime(LocalDateTime.now());
        advertisementComment.setAdvertisement(advertisementDao.read(index).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), "Advertisement", "id", index))));
        advertisementCommentDao.save(advertisementComment);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = AdvertisementComment_.class)
    public List<AdvertisementCommentDto> readAll(Long index, int page, int numberElements, String direction, String sortField) {
        final String tmpSortField = singularMapper.getAttribute(SORT_FIELD + sortField.toLowerCase()).getName();
        final Sort.Direction tmpDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        final Pageable pageParameter = PageRequest.of(page, numberElements, tmpDirection, tmpSortField);
        final List<AdvertisementCommentDto> result = dtoMapper.mapAll(advertisementCommentDao.readAllByAdvertisement(pageParameter,
                advertisementDao.read(index, GraphProperty.Advertisement.DEFAULT).orElseThrow(() ->
                        new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), "Advertisement", "id", index)))),
                AdvertisementCommentDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long readSize(Long index) {
        return advertisementCommentDao.readSize(advertisementDao.read(index).orElseThrow());
    }
}
