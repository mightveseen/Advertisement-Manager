package com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdvertisementCommentDao extends CrudRepository<AdvertisementComment, Long> {

    @EntityGraph(value = GraphProperty.AdvertisementComment.USER)
    List<AdvertisementComment> readAllByAdvertisement(Pageable pageable, Advertisement advertisement);

    @Query(value = "select count(ac) from AdvertisementComment ac where ac.advertisement = ?1")
    Long readSize(Advertisement advertisement);
}
