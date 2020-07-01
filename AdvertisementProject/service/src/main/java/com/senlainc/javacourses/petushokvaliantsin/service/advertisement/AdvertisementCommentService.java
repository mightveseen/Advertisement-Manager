package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCommentDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@SingularClass
public class AdvertisementCommentService extends AbstractService<AdvertisementComment, Long> implements IAdvertisementCommentService {

    private static final String SORT_FIELD = "advertisementcomment-";
    private final IAdvertisementCommentDao advertisementCommentDao;
    private final IAdvertisementDao advertisementDao;

    @Autowired
    public AdvertisementCommentService(IAdvertisementCommentDao advertisementCommentDao, IAdvertisementDao advertisementDao) {
        this.advertisementCommentDao = advertisementCommentDao;
        this.advertisementDao = advertisementDao;
    }

    @Override
    @Transactional
    public boolean create(Long advertisementIndex, AdvertisementCommentDto object) {
        final AdvertisementComment advertisementComment = dtoMapper.map(object, AdvertisementComment.class);
        advertisementComment.setDateTime(LocalDateTime.now());
        advertisementComment.setAdvertisement(advertisementDao.read(advertisementIndex));
        advertisementCommentDao.create(advertisementComment);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = AdvertisementComment_.class)
    public List<AdvertisementCommentDto> getAdvertisementComments(Long index, int page, int numberElements, String direction, String sortField) {
        final IPageParameter pageParameter = PageParameter.of(page, numberElements, direction, singularMapper.getAttribute(SORT_FIELD + sortField.toLowerCase()));
        return dtoMapper.mapAll(advertisementCommentDao.readAll(pageParameter, AdvertisementComment_.advertisement, advertisementDao.read(index)),
                AdvertisementCommentDto.class);
    }

    @Override
    public Long getSize() {
        return advertisementCommentDao.readCount();
    }
}
