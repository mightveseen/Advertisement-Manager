package com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;

public interface AdvertisementCategoryService {

    boolean create(AdvertisementCategoryDto object);

    boolean delete(Long index);
}
