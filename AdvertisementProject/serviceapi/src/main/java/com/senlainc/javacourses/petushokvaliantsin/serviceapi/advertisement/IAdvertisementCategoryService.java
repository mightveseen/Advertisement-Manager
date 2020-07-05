package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;

public interface IAdvertisementCategoryService {

    boolean create(AdvertisementCategoryDto object);

    boolean delete(Long index);
}
