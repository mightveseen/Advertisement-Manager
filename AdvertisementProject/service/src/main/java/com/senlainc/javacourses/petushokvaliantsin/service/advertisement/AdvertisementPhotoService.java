package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementPhotoDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementPhotoDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementPhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvertisementPhotoService extends AbstractService implements IAdvertisementPhotoService {

    private static final Logger LOGGER = LogManager.getLogger(AdvertisementPhotoService.class);
    private final IAdvertisementPhotoDao advertisementPhotoDao;

    @Autowired
    public AdvertisementPhotoService(IAdvertisementPhotoDao advertisementPhotoDao) {
        this.advertisementPhotoDao = advertisementPhotoDao;
    }

    @Override
    @Transactional
    public boolean create(AdvertisementPhotoDto object) {
        final AdvertisementPhoto advertisementPhoto = dtoMapper.map(object, AdvertisementPhoto.class);
        advertisementPhotoDao.create(advertisementPhoto);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        advertisementPhotoDao.delete(advertisementPhotoDao.read(index));
        LOGGER.info(EnumLogger.SUCCESSFUL_DELETE.getText());
        return true;
    }
}
