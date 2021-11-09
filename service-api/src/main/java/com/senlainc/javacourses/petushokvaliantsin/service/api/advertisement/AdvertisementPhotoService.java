package com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementPhotoDto;

public interface AdvertisementPhotoService {

    boolean delete(Long index);

    boolean create(AdvertisementPhotoDto object);
}
