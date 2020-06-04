package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IStateService;
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
    private final IStateService stateService;

    @Autowired
    public AdvertisementService(IAdvertisementDao advertisementDao, IStateService stateService) {
        this.advertisementDao = advertisementDao;
        this.stateService = stateService;
    }

    @Override
    @Transactional
    public boolean create(Advertisement object) {
        advertisementDao.create(object);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        final Advertisement advertisement = advertisementDao.read(index);
        advertisement.setState(stateService.read("DISABLED"));
        advertisementDao.update(advertisement);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Advertisement object) {
        advertisementDao.update(object);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Advertisement read(Long index) {
        return advertisementDao.read(index);
    }

    @Override
    public List<Advertisement> readAll(int firstElement, int maxResult) {
        return advertisementDao.readAll(PageParameter.of(firstElement, maxResult));
    }

    @Override
    @Transactional(readOnly = true)
    @SingularModel(metamodels = Advertisement_.class)
    public List<Advertisement> readAll(int firstElement, int maxResult, String direction, String sortField) {
        if (sortField.equals("default")) {
            return advertisementDao.readAll(PageParameter.of(firstElement, maxResult, direction));
        }
        return advertisementDao.readAll(PageParameter.of(firstElement, maxResult, direction, singularMapper.getSingularAttribute(sortField)));
    }
}
