package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementPhotoDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementPhotoService;
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public boolean update(AdvertisementPhoto object) {
        advertisementPhotoDao.update(object);
        return true;
    }

    @Override
    public AdvertisementPhoto read(Long index) {
        return advertisementPhotoDao.read(index);
    }

    @Override
    public List<AdvertisementPhoto> readAll(int firstElement, int maxResult, String direction) {
        return advertisementPhotoDao.readAll(PageParameter.of(firstElement, maxResult, direction));
    }
}
