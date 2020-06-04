package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

public interface IAdvertisementCategoryService extends IGenericService<AdvertisementCategory, Long> {

    boolean create(AdvertisementCategory object);

    boolean delete(Long index);
}
