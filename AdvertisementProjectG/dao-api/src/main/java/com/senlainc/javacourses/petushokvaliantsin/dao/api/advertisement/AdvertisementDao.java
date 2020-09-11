package com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.GenericDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.AdvertisementDaoCustomQuery;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface AdvertisementDao extends AdvertisementDaoCustomQuery, GenericDao<Advertisement, Long> {

    @EntityGraph(value = GraphProperty.Advertisement.DEFAULT)
    Optional<Advertisement> readById(Long id);
}
