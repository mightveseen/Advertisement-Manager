package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvertisementService extends AbstractService<Advertisement, Long> implements IAdvertisementService {

    private final IAdvertisementDao advertisementDao;

    @Autowired
    public AdvertisementService(IAdvertisementDao advertisementDao) {
        this.advertisementDao = advertisementDao;
    }

    @Override
    public boolean create(Advertisement object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(Advertisement object) {
        return false;
    }

    @Override
    public Advertisement read(Long index) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Advertisement> readAll(int firstElement, int maxResult) {
        return advertisementDao.readAll(firstElement, maxResult);
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = Advertisement_.class)
    public List<Advertisement> readAll(int firstElement, int maxResult, String sortField) {
        singularMapper.setClass(this.getClass());
        return advertisementDao.readAll(firstElement, maxResult, singularMapper.getSingularAttribute(sortField));
    }
}
