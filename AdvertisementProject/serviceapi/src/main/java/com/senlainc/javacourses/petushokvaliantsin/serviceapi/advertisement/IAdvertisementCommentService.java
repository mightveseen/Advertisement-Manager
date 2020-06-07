package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;

import java.util.List;

public interface IAdvertisementCommentService {

    boolean create(Long advertisementIndex, AdvertisementComment object);

    boolean delete(Long index);

    boolean update(AdvertisementComment object);

    List<AdvertisementComment> readAll(Long index, int page, int numberElements, String direction, String sortField);
}
