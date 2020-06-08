package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementPhotoDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvertisementPhotoService extends AbstractService<AdvertisementPhoto, Long> implements IAdvertisementPhotoService {

    private final IAdvertisementPhotoDao advertisementPhotoDao;

    @Autowired
    public AdvertisementPhotoService(IAdvertisementPhotoDao advertisementPhotoDao) {
        this.advertisementPhotoDao = advertisementPhotoDao;
    }

    @Override
    @Transactional
    public boolean create(AdvertisementPhoto object) {
        advertisementPhotoDao.create(object);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        advertisementPhotoDao.delete(advertisementPhotoDao.read(index));
        return true;
    }
}
