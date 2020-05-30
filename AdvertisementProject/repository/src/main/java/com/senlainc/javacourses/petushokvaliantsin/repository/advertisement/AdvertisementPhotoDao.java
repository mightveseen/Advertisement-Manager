package com.senlainc.javacourses.petushokvaliantsin.repository.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementPhotoDao;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementPhotoDao extends AbstractDao<AdvertisementPhoto, Long> implements IAdvertisementPhotoDao {
}
