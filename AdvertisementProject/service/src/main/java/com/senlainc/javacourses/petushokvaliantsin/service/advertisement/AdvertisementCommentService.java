package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCommentDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        advertisementComment.setUser(userDao.readByUserCred(username));
        advertisementComment.setDateTime(LocalDateTime.now());
        advertisementComment.setAdvertisement(advertisementDao.read(index));
        advertisementCommentDao.create(advertisementComment);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = AdvertisementComment_.class)
    public List<AdvertisementCommentDto> readAll(Long index, int page, int numberElements, String direction, String sortField) {
        final IPageParameter pageParameter = PageParameter.of(page, numberElements, direction, singularMapper.getAttribute(SORT_FIELD + sortField.toLowerCase()));
        final List<AdvertisementCommentDto> result = dtoMapper.mapAll(advertisementCommentDao.readAll(pageParameter, AdvertisementComment_.advertisement, advertisementDao.read(index)),
                AdvertisementCommentDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long readSize() {
        return advertisementCommentDao.readCount();
    }
}
