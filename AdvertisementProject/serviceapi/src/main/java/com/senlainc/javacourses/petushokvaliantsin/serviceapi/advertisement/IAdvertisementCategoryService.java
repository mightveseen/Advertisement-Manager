package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;

public interface IAdvertisementCategoryService {

    boolean create(AdvertisementCategory object);

    boolean delete(Long index);
}
