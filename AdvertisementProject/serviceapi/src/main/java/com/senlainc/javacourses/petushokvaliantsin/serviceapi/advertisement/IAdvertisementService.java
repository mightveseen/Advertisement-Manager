package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;

import java.util.List;

public interface IAdvertisementService {

    boolean create(Advertisement object);

    boolean create(AdvertisementDto object);

    boolean delete(Long index);

    boolean update(Advertisement object);

    boolean update(AdvertisementDto object);

    Advertisement read(Long index);

    AdvertisementDto getAdvertisement(Long index);

    List<AdvertisementDto> getAdvertisements(int firstElement, int maxResult, String direction, String sortField, String category, String search);
}
