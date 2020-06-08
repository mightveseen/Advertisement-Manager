package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCommentDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@SingularClass
public class AdvertisementCommentService extends AbstractService<AdvertisementComment, Long> implements IAdvertisementCommentService {

    private final IAdvertisementCommentDao advertisementCommentDao;
    private final IAdvertisementService advertisementService;

    @Autowired
    public AdvertisementCommentService(IAdvertisementCommentDao advertisementCommentDao, IAdvertisementService advertisementService) {
        this.advertisementCommentDao = advertisementCommentDao;
        this.advertisementService = advertisementService;
    }

    @Override
    @Transactional
    public boolean create(Long advertisementIndex, AdvertisementCommentDto object) {
        final AdvertisementComment advertisementComment = dtoMapper.map(object, AdvertisementComment.class);
        advertisementComment.setDateTime(LocalDateTime.now());
        advertisementCommentDao.create(advertisementComment);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        advertisementCommentDao.delete(advertisementCommentDao.read(index));
        return true;
    }

    @Override
    @Transactional
    public boolean update(AdvertisementComment object) {
        advertisementCommentDao.update(object);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = AdvertisementComment_.class)
    public List<AdvertisementCommentDto> getAdvertisementComments(Long index, int page, int numberElements, String direction, String sortField) {
        return dtoMapper.mapAll(advertisementCommentDao.readAll(PageParameter.of(page, numberElements, direction,
                singularMapper.getSingularAttribute("AdvertisementComment-" + sortField.toLowerCase())),
                AdvertisementComment_.advertisement, advertisementService.read(index)),
                AdvertisementCommentDto.class);
    }
}
