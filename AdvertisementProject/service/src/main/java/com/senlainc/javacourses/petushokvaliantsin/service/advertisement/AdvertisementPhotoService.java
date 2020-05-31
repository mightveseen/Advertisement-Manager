package com.senlainc.javacourses.petushokvaliantsin.service.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementPhotoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementPhotoService extends AbstractService<AdvertisementPhoto, Long> implements IAdvertisementPhotoService {

    @Override
    public boolean create(AdvertisementPhoto object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(AdvertisementPhoto object) {
        return false;
    }

    @Override
    public AdvertisementPhoto read(Long index) {
        return null;
    }

    @Override
    public List<AdvertisementPhoto> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public List<AdvertisementPhoto> readAll(int firstElement, int maxResult, String direction, String sortField) {
        return null;
    }
}