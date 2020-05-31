package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularClass;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.annotation.SingularModel;
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SingularClass
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
    public boolean delete(Long index) {
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
    public List<Advertisement> readAll(int firstElement, int maxResult, String direction) {
        return advertisementDao.readAll(PageParameter.of(firstElement, maxResult, direction));
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = Advertisement_.class)
    public List<Advertisement> readAll(int firstElement, int maxResult, String direction, String sortField) {
        return advertisementDao.readAll(PageParameter.of(firstElement, maxResult, direction, singularMapper.getSingularAttribute(sortField)));
    }
}
