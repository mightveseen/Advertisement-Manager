package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

import java.util.List;

public interface IAdvertisementService extends IGenericService<Advertisement, Long> {

    boolean create(Advertisement object);

    boolean delete(Long index);

    boolean update(Advertisement object);

    List<Advertisement> readAll(int firstElement, int maxResult, String direction, String sortField, String category, String search);
}
