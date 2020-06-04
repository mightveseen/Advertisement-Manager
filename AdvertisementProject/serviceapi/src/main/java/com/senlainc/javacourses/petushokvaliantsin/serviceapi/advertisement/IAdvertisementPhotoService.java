package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

public interface IAdvertisementPhotoService extends IGenericService<AdvertisementPhoto, Long> {

    boolean delete(Long index);

    boolean create(AdvertisementPhoto object);
}
