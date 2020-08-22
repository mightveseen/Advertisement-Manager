package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;

import java.util.List;

public interface IAdvertisementCommentService {

    boolean create(String username, Long index, AdvertisementCommentDto object);

    List<AdvertisementCommentDto> readAll(Long index, int page, int numberElements, String direction, String sortField);

    Long readSize(Long index);
}
