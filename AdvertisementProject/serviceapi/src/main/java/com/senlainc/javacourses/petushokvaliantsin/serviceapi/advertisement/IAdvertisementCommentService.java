package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;

import java.util.List;

public interface IAdvertisementCommentService {

    boolean create(String username, Long advertisementIndex, AdvertisementCommentDto object);

    List<AdvertisementCommentDto> getAdvertisementComments(Long index, int page, int numberElements, String direction, String sortField);

    Long getSize();
}
