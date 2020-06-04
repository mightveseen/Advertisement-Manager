package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementPhotoDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementPhotoService;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdvertisementPhotoService extends AbstractService<AdvertisementPhoto, Long> implements IAdvertisementPhotoService {

    private final IAdvertisementPhotoDao advertisementPhotoDao;

    @Autowired
    public AdvertisementPhotoService(IAdvertisementPhotoDao advertisementPhotoDao) {
        this.advertisementPhotoDao = advertisementPhotoDao;
    }

    @Override
    public boolean create(AdvertisementPhoto object) {
        advertisementPhotoDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        advertisementPhotoDao.delete(advertisementPhotoDao.read(index));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementPhoto read(Long index) {
        return advertisementPhotoDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdvertisementPhoto> readAll(int firstElement, int maxResult) {
        return advertisementPhotoDao.readAll(PageParameter.of(firstElement, maxResult));
    }
}
