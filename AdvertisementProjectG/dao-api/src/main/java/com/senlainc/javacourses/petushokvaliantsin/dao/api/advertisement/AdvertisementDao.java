package com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.GenericDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.AdvertisementDaoChild;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface AdvertisementDao extends AdvertisementDaoChild, GenericDao<Advertisement, Long> {

    @EntityGraph(value = GraphProperty.Advertisement.DEFAULT)
    Optional<Advertisement> readById(Long id);

    @EntityGraph(value = GraphProperty.Advertisement.DEFAULT)
    List<Advertisement> readAllByUserAndState(User user, State state, Pageable pageable);

    Long countAdvertisementByUserAndState(User user, State state);
}
