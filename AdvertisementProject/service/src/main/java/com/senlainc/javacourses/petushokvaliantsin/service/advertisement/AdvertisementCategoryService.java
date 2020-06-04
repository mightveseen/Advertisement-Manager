package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCategoryDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCategoryService;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public AdvertisementCategory read(Long index) {
        return advertisementCategoryDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdvertisementCategory> readAll(int firstElement, int maxResult) {
        return advertisementCategoryDao.readAll(PageParameter.of(firstElement, maxResult));
    }
}
