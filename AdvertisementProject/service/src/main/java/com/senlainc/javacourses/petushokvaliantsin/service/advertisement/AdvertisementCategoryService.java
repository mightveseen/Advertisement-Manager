package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCategoryDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvertisementCategoryService extends AbstractService<AdvertisementCategory, Long> implements IAdvertisementCategoryService {

    private final IAdvertisementCategoryDao advertisementCategoryDao;

    @Autowired
    public AdvertisementCategoryService(IAdvertisementCategoryDao advertisementCategoryDao) {
        this.advertisementCategoryDao = advertisementCategoryDao;
    }

    @Override
    public boolean create(AdvertisementCategory object) {
        advertisementCategoryDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        advertisementCategoryDao.delete(advertisementCategoryDao.read(index));
        return true;
    }
}
