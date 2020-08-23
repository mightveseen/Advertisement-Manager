package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementPhoto;
import org.springframework.data.repository.CrudRepository;

public interface IAdvertisementPhotoDao extends CrudRepository<AdvertisementPhoto, Long> {
}
