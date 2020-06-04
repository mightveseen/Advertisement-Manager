package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCommentDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvertisementCommentService extends AbstractService<AdvertisementComment, Long> implements IAdvertisementCommentService {

    private final IAdvertisementCommentDao advertisementCommentDao;
    private final IAdvertisementService advertisementService;

    @Autowired
    public AdvertisementCommentService(IAdvertisementCommentDao advertisementCommentDao, IAdvertisementService advertisementService) {
        this.advertisementCommentDao = advertisementCommentDao;
        this.advertisementService = advertisementService;
    }

    @Override
    public boolean create(AdvertisementComment object) {
        advertisementCommentDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        advertisementCommentDao.delete(advertisementCommentDao.read(index));
        return true;
    }

    @Override
    public boolean update(AdvertisementComment object) {
        advertisementCommentDao.update(object);
        return true;
    }

    @Override
    public AdvertisementComment read(Long index) {
        return advertisementCommentDao.read(index);
    }

    @Override
    public List<AdvertisementComment> readAll(int firstElement, int maxResult) {
        return advertisementCommentDao.readAll(PageParameter.of(firstElement, maxResult));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdvertisementComment> readAllComments(Long index, int firstElement, int maxResult, String direction, String sortField) {
        if (sortField.equals("default")) {
            return advertisementCommentDao.readAll(PageParameter.of(firstElement, maxResult, direction),
                    AdvertisementComment_.advertisement, advertisementService.read(index));
        }
        return advertisementCommentDao.readAll(PageParameter.of(firstElement, maxResult, direction, singularMapper.getSingularAttribute(sortField)),
                AdvertisementComment_.advertisement, advertisementService.read(index));
    }
}
