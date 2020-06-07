package com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;

import java.util.List;

public interface IAdvertisementService {

    boolean create(Advertisement object);

    boolean delete(Long index);

    boolean update(Advertisement object);

    Advertisement read(Long index);

    List<Advertisement> readAll(int firstElement, int maxResult, String direction, String sortField, String category, String search);
}
