package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.IAdvertisementPhotoDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementPhotoDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement.IAdvertisementPhotoService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
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
        advertisementPhotoDao.save(dtoMapper.map(object, AdvertisementPhoto.class));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        if (!advertisementPhotoDao.existsById(index)) {
            throw new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(),
                    "Photo", "index", index));
        }
        advertisementPhotoDao.deleteById(index);
        LOGGER.info(EnumLogger.SUCCESSFUL_DELETE.getText());
        return true;
    }
}
