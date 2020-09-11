package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.AdvertisementCategoryDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement.AdvertisementCategoryService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertisementCategoryServiceImpl extends AbstractService implements AdvertisementCategoryService {

    private static final Logger LOGGER = LogManager.getLogger(AdvertisementCategoryServiceImpl.class);
    private final AdvertisementCategoryDao advertisementCategoryDao;

    @Autowired
    public AdvertisementCategoryServiceImpl(AdvertisementCategoryDao advertisementCategoryDao) {
        this.advertisementCategoryDao = advertisementCategoryDao;
    }

    @Override
    @Transactional
    public boolean create(AdvertisementCategoryDto object) {
        advertisementCategoryDao.save(dtoMapper.map(object, AdvertisementCategory.class));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        if (!advertisementCategoryDao.existsById(index)) {
            throw new EntityNotExistException(entityNotExistMessage(4, 0, index));
        }
        advertisementCategoryDao.deleteById(index);
        LOGGER.info(EnumLogger.SUCCESSFUL_DELETE.getText());
        return true;
    }
}
