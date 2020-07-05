package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementPhotoDto;

public interface IAdvertisementPhotoService {

    boolean delete(Long index);

    boolean create(AdvertisementPhotoDto object);
}
