package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCategoryDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCategoryService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertisementCategoryService extends AbstractService implements IAdvertisementCategoryService {

    private static final Logger LOGGER = LogManager.getLogger(AdvertisementCategoryService.class);
    private final IAdvertisementCategoryDao advertisementCategoryDao;

    @Autowired
    public AdvertisementCategoryService(IAdvertisementCategoryDao advertisementCategoryDao) {
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
            throw new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(),
                    "Category", "id", index));
        }
        advertisementCategoryDao.deleteById(index);
        LOGGER.info(EnumLogger.SUCCESSFUL_DELETE.getText());
        return true;
    }
}
