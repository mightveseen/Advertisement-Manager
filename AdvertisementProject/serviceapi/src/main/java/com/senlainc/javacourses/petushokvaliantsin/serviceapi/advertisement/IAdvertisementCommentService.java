package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

import java.util.List;

public interface IAdvertisementCommentService extends IGenericService<AdvertisementComment, Long> {

    boolean create(Long advertisementIndex, AdvertisementComment object);

    boolean delete(Long index);

    boolean update(AdvertisementComment object);

    List<AdvertisementComment> readAll(Long index, int firstElement, int maxResult, String direction, String sortField);
}
