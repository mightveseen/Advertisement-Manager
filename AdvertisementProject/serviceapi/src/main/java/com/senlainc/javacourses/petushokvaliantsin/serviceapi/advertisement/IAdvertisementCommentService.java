package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

import java.util.List;

public interface IAdvertisementCommentService extends IGenericService<AdvertisementComment, Long> {

    List<AdvertisementComment> readAllComments(Long index, int firstElement, int maxResult, String direction, String sortField);
}
