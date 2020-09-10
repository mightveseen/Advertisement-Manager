package com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import org.springframework.data.repository.CrudRepository;

public interface IAdvertisementCategoryDao extends CrudRepository<AdvertisementCategory, Long> {
}
