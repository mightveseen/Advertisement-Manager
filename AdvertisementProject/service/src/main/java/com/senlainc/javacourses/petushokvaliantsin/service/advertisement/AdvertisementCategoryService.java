package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCategoryService;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Service
public class AdvertisementCategoryService implements IAdvertisementCategoryService {

    @Override
    public boolean create(AdvertisementCategory object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(AdvertisementCategory object) {
        return false;
    }

    @Override
    public AdvertisementCategory read(Long index) {
        return null;
    }

    @Override
    public List<AdvertisementCategory> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public <C> List<AdvertisementCategory> readAll(int firstElement, int maxResult, SingularAttribute<AdvertisementCategory, C> sortField) {
        return null;
    }
}
