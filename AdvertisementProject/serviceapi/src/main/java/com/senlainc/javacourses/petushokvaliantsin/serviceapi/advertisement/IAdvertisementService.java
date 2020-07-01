package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;

import java.util.List;

public interface IAdvertisementService {

    boolean create(AdvertisementDto object);

    boolean delete(Long index);

    boolean updateByUser(AdvertisementDto object);

    boolean updateByModerator(Long advertisementIndex, StateDto state);

    AdvertisementDto getAdvertisementByUser(Long index);

    AdvertisementDto getAdvertisementByModerator(Long index);

    List<AdvertisementDto> getUserAdvertisements(Long index, int page, int numberElements, EnumState state);

    List<AdvertisementDto> getAdvertisements(int page, int numberElements, String direction, String sortField, String search,
                                             String category, double minPrice, double maxPrice, EnumState advertisementState);

    Long getSize(EnumState state);
}
