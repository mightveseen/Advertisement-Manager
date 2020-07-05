package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;

import java.util.List;

public interface IAdvertisementService {

    boolean create(String username, AdvertisementDto object);

    boolean delete(String username, Long index);

    boolean updateByUser(String username, AdvertisementDto object);

    boolean updateStateByModerator(Long index, StateDto state);

    AdvertisementDto readByUser(Long index);

    AdvertisementDto readByModerator(Long index);

    List<AdvertisementDto> readAllWithUser(Long userIndex, int page, int numberElements, EnumState state);

    List<AdvertisementDto> readAll(int page, int numberElements, String direction, String sortField, String search,
                                   String category, double minPrice, double maxPrice, EnumState advertisementState);

    Long readSize();

    Long readSize(EnumState state);
}
