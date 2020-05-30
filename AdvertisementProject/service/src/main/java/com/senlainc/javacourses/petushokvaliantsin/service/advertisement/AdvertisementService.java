package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Service
public class AdvertisementService implements IAdvertisementService {

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
    public <C> List<Advertisement> readAll(int firstElement, int maxResult, SingularAttribute<Advertisement, C> sortField) {
        return null;
    }
}
