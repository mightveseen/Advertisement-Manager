package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;

public interface IAdvertisementPhotoService {

    boolean delete(Long index);

    boolean create(AdvertisementPhoto object);
}
