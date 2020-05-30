package com.senlainc.javacourses.petushokvaliantsin.repository.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementCategoryDao;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementCategoryDao extends AbstractDao<AdvertisementCategory, Long> implements IAdvertisementCategoryDao {
}
