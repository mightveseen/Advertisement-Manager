package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCommentDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementCommentService extends AbstractService<AdvertisementComment, Long> implements IAdvertisementCommentService {

    private final IAdvertisementCommentDao advertisementCommentDao;

    @Autowired
    public AdvertisementCommentService(IAdvertisementCommentDao advertisementCommentDao) {
        this.advertisementCommentDao = advertisementCommentDao;
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
    public List<AdvertisementComment> readAll(int firstElement, int maxResult, String direction) {
        return advertisementCommentDao.readAll(PageParameter.of(firstElement, maxResult, direction));
    }
}
