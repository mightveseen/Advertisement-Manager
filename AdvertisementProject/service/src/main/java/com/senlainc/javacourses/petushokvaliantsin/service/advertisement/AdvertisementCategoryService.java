package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementCategoryService extends AbstractService<AdvertisementCategory, Long> implements IAdvertisementCategoryService {

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
    public List<AdvertisementCategory> readAll(int firstElement, int maxResult, String direction, String sortField) {
        return null;
    }
}
