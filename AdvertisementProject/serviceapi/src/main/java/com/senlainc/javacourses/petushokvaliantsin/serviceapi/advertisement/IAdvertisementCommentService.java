package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;

import java.util.List;

public interface IAdvertisementCommentService {

    boolean create(Long advertisementIndex, AdvertisementCommentDto object);

    boolean update(AdvertisementComment object);

    List<AdvertisementCommentDto> getAdvertisementComments(Long index, int page, int numberElements, String direction, String sortField);
}
